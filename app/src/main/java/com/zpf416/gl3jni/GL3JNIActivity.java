package com.zpf416.gl3jni;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

//android sensor Manager allow us to read and parse sensor data
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
public class GL3JNIActivity extends Activity  implements SensorEventListener{
    private GL3JNIView mView;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyro;
    private Sensor mMag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mMag = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mView = new GL3JNIView(getApplication());
        setContentView(mView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
        //unregister sensors
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.unregisterListener(this, mGyro);
        mSensorManager.unregisterListener(this, mMag);
    }

    @Override
    protected  void onResume() {
        super.onResume();
        mView.onResume();
        //register and active sensors
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMag, SensorManager.SENSOR_DELAY_GAME);
    }


    //the two inteface capture changes detected and SensorEvent variable holds
    //the information about sensor type timestap accuracy and so on;
    @Override
    public void onSensorChanged(SensorEvent event) {
        //handle the accelerometer data (m/s^2)
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float ax, ay, az;
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];
            GL3JNILib.addAccelData(ax, ay, az);
        }
        //values are in radians/second and mesure the rate of
        //rotation around the device's local XYZ axes;
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            float gx, gy, gz;
            gx = event.values[0];
            gy = event.values[1];
            gz = event.values[2];
            GL3JNILib.addGyroData(gx, gy, gz);
        }

        //all values are in micro-Tesla(uT)
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            float mx, my, mz;
            mx = event.values[0];
            my = event.values[1];
            mz = event.values[2];
            GL3JNILib.addMagData(mx, my, mz);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}