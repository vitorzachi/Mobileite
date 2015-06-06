package br.edu.unochapeco.mobileite;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unochapeco.mobileite.constantes.Constantes;
import br.edu.unochapeco.mobileite.service.LoginService;


public class LoginActivity extends ActionBarActivity  {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText txtCodigo, txtCpf;
    private Button button;


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
        button = (Button) findViewById(R.id.btnEntrar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        String codigo = preferences.getString("codigo", "");
        String cpf = preferences.getString("cpf", "");

        fazLogin(codigo, cpf);
    }

    private void fazLogin(String codigo, String cpf) {
        new LoginTask(cpf, codigo).execute();
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



    public void entrar(View view) {
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtCpf = (EditText) findViewById(R.id.txtCpf);

        fazLogin(txtCodigo.getText().toString(), txtCpf.getText().toString());
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean>{
        private final String cpf, codigo;
        public LoginTask( String cpf, String codigo) {
            this.cpf = cpf;
            this.codigo = codigo;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return new LoginService(cpf, codigo).validaLogin();
        }

        @Override
        protected void onPreExecute() {
            button.setText(getString(R.string.aguarde));
            button.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean){
                editor.putString("cpf", cpf);
                editor.putString("codigo", codigo);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }else{
                button.setText(getString(R.string.entrar));
                button.setEnabled(true);
                boolean contains = preferences.contains("cpf");
                if (contains)
                   Toast.makeText(LoginActivity.this, R.string.login_erro, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(LoginActivity.this, R.string.login_invalido, Toast.LENGTH_LONG).show();
            }
        }
    }

}
