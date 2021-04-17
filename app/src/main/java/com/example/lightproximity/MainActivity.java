package com.example.lightproximity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    TextView proxval;
    TextView lightval;
    String pval,lval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proxval = (TextView) findViewById(R.id.proxval);
        lightval = (TextView) findViewById(R.id.lightval);
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            float l = (float) sensorEvent.values[0];
            lval = "<b> "+l+" </b>";
            proxval.setText(Html.fromHtml(lval));
        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
            float p = sensorEvent.values[0];
            pval = "<b> "+p+" </b>";
            proxval.setText(Html.fromHtml(pval));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}