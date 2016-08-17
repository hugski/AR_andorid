LOCAL_PATH:= $(call my-dir)

#build the OpenGL + OpenCV code in JNI
include $(CLEAR_VARS)

LOCAL_MODULE    := libgl3jni
LOCAL_CFLAGS    := -Werror
LOCAL_SRC_FILES := gl3jni.cpp Sensor.cpp
LOCAL_LDLIBS    += -llog -lGLESv3

include $(BUILD_SHARED_LIBRARY)

