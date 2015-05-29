package br.edu.unochapeco.mobileite.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by vitor on 28/05/15.
 */
public class LoginService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // buscar login
    }
}
