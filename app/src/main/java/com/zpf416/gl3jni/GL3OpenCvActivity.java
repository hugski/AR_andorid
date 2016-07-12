package com.zpf416.gl3jni;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zpf416.ar_android.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;


public class GL3OpenCvActivity extends AppCompatActivity implements
        SensorEventListener, CvCameraViewListener2 {

    private RelativeLayout liner_layout;
    private CameraBridgeViewBase mOpenCvCameraView;
    private SensorManager mSensorManager;

    private boolean gl3_loaded = false;
    private GL3JNIView mView = null;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("OpencvDemo", "OpenCv loaded successfully");
                    System.loadLibrary("gl3jni");
                    gl3_loaded = true;

                    mView = new GL3JNIView(getApplication());
                    liner_layout.addView(mView);
                    setContentView(liner_layout);
                    mOpenCvCameraView.enableView();
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl3_open_cv);

        liner_layout = (RelativeLayout) findViewById(R.id.linearLayout);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.opencv_camera_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setMaxFrameSize(1280, 720);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.disableView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0,
                this, mLoaderCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

        if(mView != null){
            mView.onPause();
        }
        if (mOpenCvCameraView != null){
            mOpenCvCameraView.disableView();
        }
        gl3_loaded = false;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat input = inputFrame.rgba();
        if(gl3_loaded){
            GL3JNILib.setImage(input.nativeObj);
        }
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
