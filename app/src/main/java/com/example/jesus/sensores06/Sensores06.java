package com.example.jesus.sensores06;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sensores06 extends AppCompatActivity implements SensorEventListener{
SensorManager sensorManager;
Sensor sensorProximo;
int contador;
double distancia =0;
String proximidad="LEJOS";
TextView tvProximidad;
TextView tvDistancia;
TextView tvContador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores06);

        tvProximidad = (TextView) findViewById(R.id.proximidad);
        tvDistancia = (TextView) findViewById(R.id.orientacion);
        tvContador = (TextView) findViewById(R.id.numeroLecturas);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorProximo = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        runOnUiThread(new CambiaTexto());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorProximo,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        distancia=event.values[0];
        contador++;
        if(distancia<1)proximidad="CERCA";
        else proximidad="LEJOS";
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    class CambiaTexto implements Runnable{
        @Override
        public void run() {
            tvDistancia.setText(""+distancia);
            tvProximidad.setText(""+proximidad);
            tvContador.setText(""+contador);
        }
    }
}
