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

    public GL3JNIView(Context context){
        super(context);

        //RGB8 color 16bit depth, no stencil
        setZOrderOnTop(true);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setEGLContextClientVersion(3);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

    }


}
