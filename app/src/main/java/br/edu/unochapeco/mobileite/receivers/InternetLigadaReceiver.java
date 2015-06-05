package br.edu.unochapeco.mobileite.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.edu.unochapeco.mobileite.constantes.Constantes;
import br.edu.unochapeco.mobileite.service.BuscaAmostrasService;

/**
 * Created by vitor on 02/06/15.
 */
public class InternetLigadaReceiver extends BroadcastReceiver {
    private Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isConectado(context)) {
            this.intent = new Intent(context, BuscaAmostrasService.class);
            context.startService(this.intent);
        } else if (this.intent != null) {
            context.stopService(this.intent);
        }
    }

    private boolean isConectado(Context context) {
        return Constantes.isConectado(context);
    }
}
