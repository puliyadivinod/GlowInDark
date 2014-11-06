package com.glowindark;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.security.Policy;


public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManger;
    private Sensor mPressure;
    private Camera camera;
    private Parameters p;
    private static final String TAG = "SensorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManger = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManger.getDefaultSensor(Sensor.TYPE_LIGHT);

        camera = Camera.open();
        p = camera.getParameters();
        camera.startPreview();

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){
        // do something here
    }

    @Override
    public final void onSensorChanged(SensorEvent event){
        float value_of_light = event.values[0];
        Log.v(TAG, "index=" + value_of_light);

        Context context = this;
        PackageManager pm = context.getPackageManager();

        p.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(p);

        if(pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Log.v(TAG, "index=" + "has flash");

            if(value_of_light == 1.0){
                Log.v(TAG, "equals to 1.0=" + value_of_light);

                p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManger.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManger.unregisterListener(this);
        camera.stopPreview();
        camera.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
