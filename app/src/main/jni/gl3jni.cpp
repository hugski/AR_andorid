
#include <jni.h>
#include <android/log.h>
#include <pthread.h>

//header for the OpenGL ES3 library
#include <GLES3/gl3.h>

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <unistd.h>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/features2d/features2d.hpp>

#define  LOG_TAG    "libgl3jni"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
pthread_mutex_t count_mutex;

int width = 1280;
int height = 720;

//pre-set image size
const int IMAGE_WIDTH = 1280;
const int IMAGE_HEIGHT = 720;

float scale = 1.0f;
float aspect_ratio=1.0f;
float aspect_ratio_frame=1.0f;

bool enable_process = true;
cv::Mat frame;

JNIEXPORT void JNICALL
Java_com_zpf416_gl3jni_GL3JNILib_init(JNIEnv *env, jclass type, jint width, jint height) {

}

JNIEXPORT void JNICALL
Java_com_zpf416_gl3jni_GL3JNILib_step(JNIEnv *env, jclass type) {

    // TODO

}

JNIEXPORT void JNICALL
Java_com_zpf416_gl3jni_GL3JNILib_setScale(JNIEnv *env, jclass type, jfloat scale) {

    // TODO

}

JNIEXPORT void JNICALL
Java_com_zpf416_gl3jni_GL3JNILib_resetRotDataOffset(JNIEnv *env, jclass type) {

    // TODO

}

JNIEXPORT void JNICALL
Java_com_zpf416_gl3jni_GL3JNILib_setRotMatrix(JNIEnv *env, jclass type, jfloatArray rotMatrix_) {
    jfloat *rotMatrix = (*env)->GetFloatArrayElements(env, rotMatrix_, NULL);

    // TODO

    env->ReleaseFloatArrayElements(env, rotMatrix_, rotMatrix, 0);
}

JNIEXPORT void JNICALL
Java_com_zpf416_gl3jni_GL3JNILib_setImage(JNIEnv *env, jclass type, jlong imageRGBA) {

    // TODO
    cv::Mat* image = (cv::Mat*) imageRGBA;
    //use mutex lock to ensure the write/read operations are synced (to avoid corrupting the frame)
    pthread_mutex_lock(&count_mutex);
    frame = image->clone();
    pthread_mutex_unlock(&count_mutex);
}