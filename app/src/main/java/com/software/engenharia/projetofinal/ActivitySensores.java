package com.software.engenharia.projetofinal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ActivitySensores extends AppCompatActivity {
    TextView txtLongitude, txtLatitude, txtUmidade, txtTemperatura, txtLuminos, txtProx;
    Button btIniciaSensores;
    SensorManager mSensorManager;
    Sensor mLuz, mProx, mUmidade, mTemperatura;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Location localizacao;
    private LocationManager locationManager;
    EntidadeSensores dadosSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        dadosSensores = new EntidadeSensores();

        btIniciaSensores = findViewById(R.id.btnIniciaSensor);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        txtLuminos = findViewById(R.id.txtLuminos);
        txtProx = findViewById(R.id.txtProx);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtUmidade = findViewById(R.id.txtUmidade);

        LinearLayout ll = findViewById(R.id.layout);
        //ll.setBackgroundColor(getResources().getColor(R.color.));

        pedirPermissao();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    public void LUMINOSIDADE() {
        mLuz = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(new LuzSensor(), mLuz, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void LOCALIZAR() {

        pedirPermissao();

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case 1: {
                if(grantResults.length > 0 &&
                        grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configurarServico();
                else
                    Toast.makeText(this, "Não vai funcionar!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void UMIDADETEMPERATURA() {
        mUmidade= mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mTemperatura = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mUmidade != null){
            mSensorManager.registerListener(new UmidadeSensor(), mUmidade, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Log.i("Sensores","Não possui sensor de Umidade, simulando sensor");
            simulaSensorUmidade();
        }

        if(mTemperatura != null){
            mSensorManager.registerListener(new TemperaturaSensor(), mTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Log.i("Sensores","Não possui sensor de Temperatura, simulando sensor");
            simulaSensorTemperatura();
        }
    }

    private void simulaSensorTemperatura() {
        Random aleatorio = new Random();
        float valor = aleatorio.nextInt((50 - (-30)) + 1) + (-30);
        dadosSensores.setTemperatura(valor);
        txtTemperatura.setText(valor+" (Simulando Sensor)");
    }

    private void simulaSensorUmidade() {
        Random aleatorio = new Random();
        float valor = aleatorio.nextInt(30) + 1;
        dadosSensores.setUmidade(valor);

        txtUmidade.setText(valor+" (Simulando Sensor)");
    }

    class UmidadeSensor implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];
            dadosSensores.setUmidade(vl);
            txtUmidade.setText(vl+"");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
    class TemperaturaSensor implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];
            dadosSensores.setTemperatura(vl);
            txtTemperatura.setText(vl+"");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    class LuzSensor implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];
            dadosSensores.setLuminosidade(vl);
            txtLuminos.setText(vl+"");
        }
    }

    public void PROXIMIDADE() {
        mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(new ProxSensor(), mProx, SensorManager.SENSOR_DELAY_FASTEST);
    }
    class ProxSensor implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];
            dadosSensores.setProximidade(vl);
            txtProx.setText(vl+"");
        }
    }

    private void pedirPermissao(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },1);
        }
        else
            configurarServico();
    }

    public void configurarServico(){
        try {
            final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    dadosSensores.setLocation(location);
                    localizacao = location;
                    if(location != null){
                        txtLatitude.setText(location.getLatitude()+"");
                        txtLongitude.setText(location.getLongitude()+"");

                        UMIDADETEMPERATURA();
                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {  }

                @Override
                public void onProviderDisabled(String provider) {  }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch(SecurityException ex){
        }
    }

    public void INICIASENSORES(View view) {
        if(localizacao!=null)
        {
            btIniciaSensores.setClickable(false);
            UMIDADETEMPERATURA();
            LUMINOSIDADE();
            PROXIMIDADE();
            LOCALIZAR();

            dadosSensores.EnviaWebService();

            new Thread(new Runnable() {
                public void run() {
                    while(true){

                        dadosSensores.EnviaWebService();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }else Toast.makeText(this, "Ainda carregando, tente novamente!!!", Toast.LENGTH_SHORT).show();
    }


}
