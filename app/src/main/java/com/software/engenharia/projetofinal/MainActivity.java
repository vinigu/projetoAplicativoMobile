package com.software.engenharia.projetofinal;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mudarPortugues(View view) {
        setLocale("pt");
    }

    public void mudarIngles(View view) { setLocale("en"); }

    public void mudarEspanhol(View view) { setLocale("es"); }

    public void setLocale(String linguagem){
        Locale meulocal = new Locale(linguagem);
        Resources res = getResources(); //Pega todos os recursos do celular
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = meulocal;

        Intent intent = new Intent(this, ActivitySensores.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
