package br.edu.unochapeco.mobileite;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.edu.unochapeco.mobileite.constantes.Constantes;
import br.edu.unochapeco.mobileite.service.LoginService;


public class LoginActivity extends ActionBarActivity implements ServiceConnection {
    private LoginService loginService;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText txtCodigo, txtCpf;


    public void ligarSuporte(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Constantes.getTelefoneAjuda());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtCpf = (EditText) findViewById(R.id.txtCpf);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        String codigo = preferences.getString("codigo", "");
        String cpf = preferences.getString("cpf", "");

        fazLogin(codigo, cpf);
    }

    private void vaiParaHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void fazLogin(String codigo, String cpf) {
        Intent intent = new Intent(this, LoginService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
        boolean loginSucesso = loginService.doLogin(cpf, codigo);

        if(loginSucesso){
            vaiParaHome();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.loginService = ((LoginService.LoginBinder) service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public void entrar(View view) {
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtCpf = (EditText) findViewById(R.id.txtCpf);

        fazLogin(txtCodigo.getText().toString(), txtCpf.getText().toString());
    }
}
