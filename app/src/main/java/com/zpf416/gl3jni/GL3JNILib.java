package com.zpf416.gl3jni;

/**
 * Created by zpff on 2016/7/11.
 */

public class GL3JNILib {

    static {
        System.loadLibrary("gl3jni");
    }

    /**
     * @param width the current view width
     * @param height the current view height
     */
    public static native void init(int width, int height);
    public static native void step();

    public static native void setScale(float scale);
    public static native void resetRotDataOffset();
    public static native void setRotMatrix(float [] rotMatrix);

    public static native void setImage(long ImageRGBA);
}
