package br.edu.unochapeco.mobileite.service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import br.edu.unochapeco.mobileite.constantes.Constantes;

/**
 * Created by vitor on 28/05/15.
 */
public class LoginService  {
    private final String cpf, codigo;
    private boolean ok=false;

    public LoginService( String cpf, String codigo) {
        this.cpf = cpf;
        this.codigo = codigo;
    }


    public boolean validaLogin() {
        if("".equals(cpf)||"".equals(codigo)){
            return  false;
        }

        URL url = Constantes.getUrlLogin(cpf, codigo);
        URLConnection conection = null;

            try {
                conection = url.openConnection();
                conection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conection.getInputStream()));

                ok = Boolean.valueOf(reader.readLine());
            } catch (IOException e) {
                ok = false;
            }

       return ok;
    }

}
