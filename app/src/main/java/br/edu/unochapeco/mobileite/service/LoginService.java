package br.edu.unochapeco.mobileite.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import br.edu.unochapeco.mobileite.R;
import br.edu.unochapeco.mobileite.constantes.Constantes;

/**
 * Created by vitor on 28/05/15.
 */
public class LoginService extends Service {
    private Intent intent;

    @Override
    public IBinder onBind(Intent intent) {
        this.intent = intent;
        return new LoginBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public boolean doLogin(String cpf, String codigo) {
        URL url = Constantes.getUrlLogin(cpf, codigo);
        URLConnection conection = null;
        try {
            conection = url.openConnection();
            conection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conection.getInputStream()));

            return Boolean.valueOf(reader.readLine());
        } catch (IOException e) {
            Toast.makeText(this, R.string.login_erro, Toast.LENGTH_LONG).show();
            return false;
        }
    }

   public class LoginBinder extends Binder {

        public LoginService getService() {
            return LoginService.this;
        }
    }
}
