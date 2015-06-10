package br.edu.unochapeco.mobileite;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

import br.edu.unochapeco.mobileite.constantes.Constantes;


public class AnaliseDados extends ActionBarActivity {
    private ProgressBar barCbt, barCcs, barProteina, barGordura;
    private TextView nrAnalise, dataAnalise, dataEnvio;
    private TextView numCbt, numCcs, numProteina, numGordura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisedados);

        barCbt = (ProgressBar) findViewById(R.id.progressCBT);
        barCcs = (ProgressBar) findViewById(R.id.progressCCS);
        barProteina = (ProgressBar) findViewById(R.id.progressProteina);
        barGordura = (ProgressBar) findViewById(R.id.progressGordura);

        nrAnalise = (TextView) findViewById(R.id.NumeroAnalise);
        dataAnalise = (TextView) findViewById(R.id.DataAnalise);
        dataEnvio = (TextView) findViewById(R.id.envioAnalise);

        numCbt = (TextView) findViewById(R.id.numCbt);
        numCcs = (TextView) findViewById(R.id.numCcs);
        numProteina = (TextView) findViewById(R.id.numProteina);
        numGordura = (TextView) findViewById(R.id.numGordura);

        Analise analise = (Analise) getIntent().getSerializableExtra("analise");

        barCbt.setProgress(analise.getPercentagemCbt());
        barCcs.setProgress(analise.getPercentagemCcs());
        barProteina.setProgress(analise.getPercentagemProteina());
        barGordura.setProgress(analise.getPercentagemGordura());

        nrAnalise.setText(analise.getNumero().toString());
        dataAnalise.setText(Constantes.stringFromData_pt_br(analise.getDataAnalise()));
        dataEnvio.setText(Constantes.stringFromData_pt_br(analise.getDataEnvio()));

        numCbt.setText(String.valueOf(analise.getCbt()));
        numCcs .setText(String.valueOf(analise.getCcs()));
        numProteina.setText(analise.getProteina().toString());
        numGordura.setText(analise.getGordura().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_analisedados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AbrirMapa(View view) {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);

    }
}
