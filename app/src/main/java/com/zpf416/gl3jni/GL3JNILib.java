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

    public static native void step ();

    public static native void addAccelData(float ax, float ay, float az);

    public static native void addGyroData(float gx, float gy, float gz);

    public static native void addMagData(float mx, float my, float mz);
}
