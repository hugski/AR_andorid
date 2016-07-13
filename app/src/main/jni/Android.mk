LOCAL_PATH:= $(call my-dir)

#build the OpenGL + OpenCV code in JNI
include $(CLEAR_VARS)

#include OpenCV SDK
include ../../../3rd_party/android/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk

LOCAL_MODULE    := gl3jni
LOCAL_CFLAGS    := -Werror
LOCAL_SRC_FILES := main.cpp Texture.cpp Shader.cpp VideoRenderer.cpp
LOCAL_LDLIBS    += -llog -lGLESv3 -lm 
LOCAL_C_INCLUDES +=  Texture.hpp Shader.hpp VideoRenderer.hpp

include $(BUILD_SHARED_LIBRARY)

