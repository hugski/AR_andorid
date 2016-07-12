package com.zpf416.gl3jni;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * Created by zpff on 2016/7/11.
 */
public class GL3JNIView extends GLSurfaceView{
    private final MyGLRenderer mRenderer;

    ScaleGestureDetector mScaleDetector;
    public GL3JNIView(Context context){
        super(context);

        //RGB8 color 16bit depth, no stencil
        setZOrderOnTop(true);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setEGLContextClientVersion(3);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        mScaleDetector = new ScaleGestureDetector(context,
                new MySimpleOnScaleGestureListener());
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        mScaleDetector.onTouchEvent(event);
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                GL3JNILib.resetRotDataOffset();
                break;

        }
        return true;
    }
    private class MySimpleOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mScaleFactor = 1.f;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //scaling factor
            mScaleFactor *= detector.getScaleFactor();
            // Don't let the object get too small or too large
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            invalidate();
            GL3JNILib.setScale(mScaleFactor);
            return true;
        }
    }
}
