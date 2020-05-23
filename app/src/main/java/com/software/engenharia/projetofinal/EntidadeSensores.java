package com.software.engenharia.projetofinal;

import android.location.Location;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EntidadeSensores {
    private Location location;
    private float umidade;
    private float temperatura;
    private float luminosidade;
    private float proximidade;


    public EntidadeSensores() {
        this.location = null;
        this.umidade = 0;
        this.temperatura = 0;
        this.luminosidade = 0;
        this.proximidade = 0;
    }

    public Double getLatitude() {
        return location.getLatitude();
    }
    public Double getLongitude(){
        return location.getLongitude();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getUmidade() {
        return umidade;
    }

    public void setUmidade(float umidade) {
        this.umidade = umidade;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(float luminosidade) {
        this.luminosidade = luminosidade;
    }

    public float getProximidade() {
        return proximidade;
    }

    public void setProximidade(float proximidade) {
        this.proximidade = proximidade;
    }

    public Object[] EnviaWebService(){
        //Método que enviará os dados para o webservice
        //TESTE
        Object[] object = new Object[6];
        object[0] = temperatura;
        object[1] = umidade;
        object[2] = luminosidade;
        object[3] = proximidade;
        object[4] = location.getLongitude();
        object[5] = location.getLongitude();

        //ArrayList<Float> itens = dadosTotais();
        Log.d("id", String.valueOf(object));

        return object;
    }



}
