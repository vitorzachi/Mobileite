package br.edu.unochapeco.mobileite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends ActionBarActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText txtCodigo, txtCpf;


    public void ligarSuporte(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4999188882"));
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

        fazLogin();
    }

    private void vaiParaHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void fazLogin() {
        // implementar

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
}
