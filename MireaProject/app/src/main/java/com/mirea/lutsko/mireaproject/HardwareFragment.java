package com.mirea.lutsko.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HardwareFragment extends Fragment implements SensorEventListener {

    private TextView humidityTextView;
    private TextView pressureTextView;
    private TextView lightTextView;
    private SensorManager sensorManager;
    private Sensor humiditySensor;
    private Sensor pressureSensor;
    private Sensor lightSensor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hardware, container, false);

        humidityTextView = view.findViewById(R.id.humidityTextView);
        pressureTextView = view.findViewById(R.id.pressureTextView);
        lightTextView = view.findViewById(R.id.lightTextView);

        if (getActivity() != null){
            sensorManager = (SensorManager) getActivity()
                    .getSystemService(Context.SENSOR_SERVICE);

            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            String newValue = sensorEvent.values[0] + " %";
            humidityTextView.setText(newValue);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE){
            String newValue = sensorEvent.values[0] + " мбар";
            pressureTextView.setText(newValue);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            String newValue = sensorEvent.values[0] + " град. по Цельсию";
            lightTextView.setText(newValue);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, humiditySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}