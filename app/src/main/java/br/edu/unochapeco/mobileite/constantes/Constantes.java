package br.edu.unochapeco.mobileite.constantes;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by vitor on 01/06/15.
 */
public class Constantes {

    public static final String URL_LOGIN = "http://www.camtwo.com.br/mobileite/login.php";
    public static final String URL_LOGIN_PATTERN = URL_LOGIN + "?codigo=%s&cpf=%s";
    public static final String TEL_SUPORTE = "tel:4999188882";


    private Constantes() {
    }


    public static URL getUrlLogin(String cpf, String codigo){
        try {
            return new URL(String.format(URL_LOGIN_PATTERN, codigo, cpf));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static Uri getTelefoneAjuda(){
        return Uri.parse(TEL_SUPORTE);
    }
}
