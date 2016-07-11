package com.zpf416.gl3jni;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by zpff on 2016/7/11.
 */
public class GL3JNIView extends GLSurfaceView{
    private final MyGLRenderer mRenderer;

    public GL3JNIView(Context context){
        super(context);

        setEGLContextClientVersion(3);

        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
    }
}
