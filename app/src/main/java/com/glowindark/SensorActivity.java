package com.glowindark;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.util.Log;

import com.glowindark.R;

/**
 * Created by vinod on 6/11/14.
 */
public class SensorActivity extends MainActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
