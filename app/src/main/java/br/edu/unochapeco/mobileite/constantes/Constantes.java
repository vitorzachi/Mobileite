package br.edu.unochapeco.mobileite.constantes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vitor on 01/06/15.
 */
public class Constantes {

    public static final String URL_LOGIN = "http://www.camtwo.com.br/mobileite/login.php";
    public static final String URL_AMOSTRAS = "http://www.camtwo.com.br/mobileite/amostras.php";
    public static final String URL_LOGIN_PATTERN = URL_LOGIN + "?codigo=%s&cpf=%s";
    public static final String URL_ANALISES_PATTERN = URL_AMOSTRAS + "?codigo=%s&cpf=%s";
    public static final String TEL_SUPORTE = "tel:4999188882";
    private static final Gson gson = new GsonBuilder().create();

    private Constantes() {
    }


    public static URL getUrlLogin(String cpf, String codigo) {
        try {
            return new URL(String.format(URL_LOGIN_PATTERN, codigo, cpf));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static URL getUrlAnalises(String cpf, String codigo) {
        try {
            return new URL(String.format(URL_ANALISES_PATTERN, codigo, cpf));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static Uri getTelefoneAjuda() {
        return Uri.parse(TEL_SUPORTE);
    }

    public static boolean isConectado(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            // Se não existe nenhum tipo de conexão retorna false
            if (netInfo == null) {
                return false;
            }

            int netType = netInfo.getType();

            // Verifica se a conexão é do tipo WiFi ou Mobile e
            // retorna true se estiver conectado ou false em
            // caso contrário
            if (netType == ConnectivityManager.TYPE_WIFI ||
                    netType == ConnectivityManager.TYPE_MOBILE) {
                return netInfo.isConnected();

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static <T> T getFromJSON(Reader json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }
}
