package com.software.engenharia.projetofinal;

import android.location.Location;
import android.util.Log;

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

    public void EnviaWebService(){
        //Método que enviará os dados para o webservice
        //TESTE
        Log.i("Mensagem", "TESTANDO");
        Log.i("Mensagem", "temperatura " + temperatura + "ºC");
        Log.i("Mensagem", "UMIDADE " + umidade + "%");
        Log.i("Mensagem", "LUMINOSIDADE " + luminosidade + " lumens");
        Log.i("Mensagem", "PROXIMIDADE " + proximidade);
        Log.i("Mensagem", "LOCALIZAÇÃO: latitude " + location.getLatitude() + " Longitude: " + location.getLongitude());


    }
}
