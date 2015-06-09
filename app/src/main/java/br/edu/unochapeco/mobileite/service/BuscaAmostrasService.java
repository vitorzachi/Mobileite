package br.edu.unochapeco.mobileite.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import br.edu.unochapeco.mobileite.Analise;
import br.edu.unochapeco.mobileite.BaseDao;
import br.edu.unochapeco.mobileite.R;
import br.edu.unochapeco.mobileite.constantes.Constantes;

/**
 * Created by vitor on 01/06/15.
 */
public class BuscaAmostrasService extends IntentService {

    public BuscaAmostrasService() {
        super("BuscaAmostrasService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BuscaAmostrasService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        BaseDao baseDao = new BaseDao(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        URL url = Constantes.
                getUrlAnalises(sharedPreferences.getString("cpf", ""), sharedPreferences.getString("codigo", ""));
        URLConnection conection = null;
        try {
            conection = url.openConnection();
            conection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conection.getInputStream()));

            List<Analise> analises = Constantes.getFromJSON(reader, new TypeToken<List<Analise>>(){});
            Log.e("amostras", "foi buscar amostras");
            Log.e("amostras", analises.toString());

            for (Analise analise:analises){
                baseDao.upsert(analise);
            }
        } catch (IOException e) {
            Toast.makeText(this, R.string.buscar_amostras_erro, Toast.LENGTH_LONG).show();
            Log.e("amostras", "erro ao buscar");
        }
    }


}
