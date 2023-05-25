package edu.kpi.android.labs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private TextView xAccelTextView, yAccelTextView, zAccelTextView;
    private TextView xMagnetTextView, yMagnetTextView, zMagnetTextView;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        Toolbar toolbar = findViewById(R.id.sensors_toolbar);
        setSupportActionBar(toolbar);

        xAccelTextView = findViewById(R.id.x_accel_text);
        yAccelTextView = findViewById(R.id.y_accel_text);
        zAccelTextView = findViewById(R.id.z_accel_text);
        xMagnetTextView = findViewById(R.id.x_magnet_text);
        yMagnetTextView = findViewById(R.id.y_magnet_text);
        zMagnetTextView = findViewById(R.id.z_magnet_text);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        System.out.println(Arrays.toString(sensorManager.getSensorList(Sensor.TYPE_ALL).stream().map(Sensor::getName).toArray()));

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == accelerometer) {
            float xAccel = event.values[0];
            float yAccel = event.values[1];
            float zAccel = event.values[2];

            xAccelTextView.setText(String.format("X: %s", xAccel));
            yAccelTextView.setText(String.format("Y: %s", yAccel));
            zAccelTextView.setText(String.format("Z: %s", zAccel));
        } else if (event.sensor == magnetometer) {
            float xMagnet = event.values[0];
            float yMagnet = event.values[1];
            float zMagnet = event.values[2];

            xMagnetTextView.setText(String.format("X: %s", xMagnet));
            yMagnetTextView.setText(String.format("Y: %s", yMagnet));
            zMagnetTextView.setText(String.format("Z: %s", zMagnet));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}