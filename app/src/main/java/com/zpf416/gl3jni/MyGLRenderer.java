package com.zpf416.gl3jni;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zpff on 2016/7/11.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer{
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GL3JNILib.init(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL3JNILib.step();
    }
}
