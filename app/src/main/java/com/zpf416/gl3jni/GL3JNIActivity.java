package com.zpf416.gl3jni;

import android.app.Activity;
import android.os.Bundle;

public class GL3JNIActivity extends Activity {
    GL3JNIView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new GL3JNIView(getApplication());
        setContentView(mView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected  void onResume() {
        super.onResume();
        mView.onResume();
    }
}