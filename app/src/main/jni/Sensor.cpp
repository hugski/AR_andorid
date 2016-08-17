//
// Created by zpf on 16/8/17.
//

#include "Sensor.h"

Sensor::Sensor() {
    //default size
    init(256);
}

Sensor::Sensor(unsigned int size) {
    init(size);
}

Sensor::~Sensor() {
    free_all();
}

void Sensor::init(unsigned int size) {
    buffer_size = size;
    free_all();
    createBuffers(size);
    setNormalizedAxis(x_axis, size, -1.0f, 1.0f);
    abs_max_acc = 0;
    abs_max_gyro = 0;
    abs_max_mag = 0;
}

//allocate memory for sensor data buffers
void Sensor::createBuffers(unsigned int size) {
    accel_data = (GLfloat **) malloc(3 * sizeof(GLfloat*));
    gyro_data = (GLfloat **) malloc(3 * sizeof(GLfloat*));
    mag_data = (GLfloat **) malloc(3 * sizeof(GLfloat*));

    accel_data[0] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));
    accel_data[1] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));
    accel_data[2] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));

    gyro_data[0] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));
    gyro_data[1] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));
    gyro_data[2] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));

    mag_data[0] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));
    mag_data[1] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));
    mag_data[2] = (GLfloat* ) calloc(buffer_size, sizeof(GLfloat));

    x_axis = (GLfloat*) calloc(buffer_size, sizeof(GLfloat));
}

void Sensor::free_all() {
    if(accel_data){
        free(accel_data[0]);
        free(accel_data[1]);
        free(accel_data[2]);
        free(accel_data);
    }

    if(gyro_data){
        free(gyro_data[0]);
        free(gyro_data[1]);
        free(gyro_data[2]);
        free(gyro_data);
    }

    if(mag_data){
        free(mag_data[0]);
        free(mag_data[1]);
        free(mag_data[2]);
        free(mag_data);
    }

    if(x_axis){
        free(x_axis);
    }
}

//append acceleration data to the buffer
void Sensor::appendAccelData(GLfloat x, GLfloat y, GLfloat z) {
    abs_max_acc = 0;
    float data[3] = {x, y, z};
    for(int i = 0; i < 3; i++){
        appendData(accel_data[i], data[i]);
        findAbsMax(accel_data[i], &abs_max_acc);
    }
}

void Sensor::appendGyroData(GLfloat x, GLfloat y, GLfloat z) {
    abs_max_gyro = 0;
    float data[3] = {x, y, z};
    for(int i = 0; i < 3; i++){
        appendData(gyro_data[i], data[i]);
        findAbsMax(gyro_data[i], &abs_max_gyro);
    }
}

void Sensor::appendMagData(GLfloat x, GLfloat y, GLfloat z){
    abs_max_mag = 0;
    float data[3] = {x, y, z};
    for(int i = 0; i < 3; i++){
        appendData(mag_data[i], data[i]);
        findAbsMax(mag_data[i], &abs_max_mag);
    }
}

void Sensor::appendData(GLfloat *src, GLfloat data) {
    int i;
    for(i = 0; i < buffer_size-1; i++){
        src[i] = src[i+1];
    }
    src[buffer_size - 1] = data;
}

//returning the pointer to the memory buffer
GLfloat *Sensor::getAxisPtr() {
    return x_axis;
}
//get the acceleration data buffer
GLfloat* Sensor::getAccelDataPtr(int channel) {
    return accel_data[channel];
}

//get the Gyroscope data buffer
GLfloat * Sensor::getGyroDataPtr(int channel) {
    return gyro_data[channel];
}

//get the Magnetic field data buffer
GLfloat* Sensor::getMagDataPtr(int channel) {
    return mag_data[channel];
}
unsigned int Sensor::getBufferSize() {
    return buffer_size;
}
GLfloat Sensor::getAccScale() {
    return abs_max_acc;
}

GLfloat Sensor::getGyroScale() {
    return abs_max_gyro;
}

GLfloat Sensor::getMagScale() {
    return abs_max_mag;
}

void Sensor::setNormalizedAxis(GLfloat *data, unsigned int size, float min, float max) {
    float step_size = (max - min) /(float) size;
    for (int i = 0; i < size; ++i) {
        data[i] = min + step_size*i;
    }
}

void Sensor::findAbsMax(GLfloat *src, GLfloat *max) {
    for (int i = 0; i < buffer_size; ++i) {
        if(*max < fabs(src[i])) {
            *max = fabs(src[i]);
        }
    }
}




