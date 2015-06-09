package br.edu.unochapeco.mobileite;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.Date;

import br.edu.unochapeco.mobileite.constantes.Constantes;

/**
 * Created by vitor on 28/05/15.
 */
public class Analise implements Serializable {
    private Integer numero;
    private Date dataEnvio;
    private Date dataAnalise;
    private int cbt;
    private int ccs;
    private float proteina;
    private float gordura;

    private float latitude;
    private float longitude;

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("numero", numero);
        contentValues.put("dataenvio", Constantes.stringFromData(dataEnvio));
        contentValues.put("dataanalise", Constantes.stringFromData(dataAnalise));
        contentValues.put("cbt", cbt);
        contentValues.put("ccs", ccs);
        contentValues.put("proteina", proteina);
        contentValues.put("gordura", gordura);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);

        return  contentValues;
    }


    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(Date dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public int getCbt() {
        return cbt;
    }

    public void setCbt(int cbt) {
        this.cbt = cbt;
    }

    public int getCcs() {
        return ccs;
    }

    public void setCcs(int ccs) {
        this.ccs = ccs;
    }

    public float getProteina() {
        return proteina;
    }

    public void setProteina(float proteina) {
        this.proteina = proteina;
    }

    public float getGordura() {
        return gordura;
    }

    public void setGordura(float gordura) {
        this.gordura = gordura;
    }
}
