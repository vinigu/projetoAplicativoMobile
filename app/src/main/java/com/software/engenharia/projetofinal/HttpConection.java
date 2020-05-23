package com.software.engenharia.projetofinal;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
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


public class HttpConection extends AppCompatActivity {
    EntidadeSensores dadosSensores;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dadosSensores = new EntidadeSensores();

        TextView tv = new TextView(this);
        setContentView(tv);
        Object[] obj =  dadosSensores.EnviaWebService();
        Log.i("teste", String.valueOf(obj));
        try {
            URL url = new URL("https://app-sensores.000webhostapp.com/postJson.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.disconnect();

            tv.setText("Hello!");
        }
        catch (MalformedURLException ex) {
            Log.e("httptest",Log.getStackTraceString(ex));
        }
        catch (IOException ex) {
            Log.e("httptest",Log.getStackTraceString(ex));
        }
    }

}
