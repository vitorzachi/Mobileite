package br.edu.unochapeco.mobileite;

import java.util.Date;

/**
 * Created by vitor on 28/05/15.
 */
public class Analise {
    private long numero;
    private Date dataEnvio;
    private Date dataAnalise;
    private int cbt;
    private int ccs;
    private float proteina;
    private float gordura;

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
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
