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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vitor on 01/06/15.
 */
public class Constantes {

    public static final String URL_LOGIN = "http://www.camtwo.com.br/mobileite/login.php";
    public static final String date_pattern = "dd/MM/yyyy HH:mm";
    public static final String URL_AMOSTRAS = "http://www.camtwo.com.br/mobileite/amostras.php";
    public static final String URL_LOGIN_PATTERN = URL_LOGIN + "?codigo=%s&cpf=%s";
    public static final String URL_ANALISES_PATTERN = URL_AMOSTRAS + "?codigo=%s&cpf=%s";
    public static final String TEL_SUPORTE = "tel:4999188882";
    private static final Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy-HH:mm").create();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Constantes() {
    }


    public static Date dataFromString(String string) {
        try {
            return DATE_FORMAT.parse(string);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String stringFromData(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String stringFromData_pt_br(Date date) {
        return new SimpleDateFormat(date_pattern).format(date);
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

/*
    public static String amostras() {
        Analise analise = new Analise();
        analise.setCbt(150);
        analise.setCcs(120);
        analise.setDataAnalise(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        analise.setDataEnvio(calendar.getTime());
        analise.setGordura(3.5f);
        analise.setNumero(1);
        analise.setProteina(2.9f);
        analise.setLatitude(-24f);
        analise.setLongitude(-57f);

        Analise analise2 = new Analise();
        analise2.setCbt(150);
        analise2.setCcs(120);
        analise2.setDataAnalise(new Date());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, -2);
        analise2.setDataEnvio(calendar2.getTime());
        analise2.setGordura(3.5f);
        analise2.setNumero(1);
        analise2.setProteina(2.9f);
        analise2.setLatitude(-24f);
        analise2.setLongitude(-57f);

        List<Analise> analises = new ArrayList<>();
        analises.add(analise);
        analises.add(analise2);


       return gson.toJson(analises);
    }*/
}
