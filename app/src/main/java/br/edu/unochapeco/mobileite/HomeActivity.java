package br.edu.unochapeco.mobileite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.unochapeco.mobileite.service.BuscaAmostrasService;


public class HomeActivity extends ActionBarActivity {
    private static final String campos[] = {"_id", "dataanalise"};
    private ListView listView;
    private BaseDao helper;
    private SQLiteDatabase database;
    private CursorAdapter dataSource;
    private TextView textViewCarregando;
    private String numeroAnalise1;
    private BroadcastReceiver novaAnaliseReceiver;
//
    /**
     * Chamado quando a Activity é exeutada pela primeira vez.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         novaAnaliseReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                HomeActivity.this.Carregadados();
            }
        };

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("__analises_recebidas");
        bManager.registerReceiver(novaAnaliseReceiver, intentFilter);

        textViewCarregando = (TextView) findViewById(R.id.txtAguarde);

        listView = (ListView) findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("amostra", String.valueOf(id));
                //Log.e("amostra", String.valueOf(position));

                Analise analise = helper.getAnalisePorId(Long.valueOf(id).intValue());
                Intent intent = new Intent(HomeActivity.this, AnaliseDados.class);
                intent.putExtra("analise", analise);
                startActivity(intent);

            }
        });

        Intent intent = new Intent(this, BuscaAmostrasService.class);
        startService(intent);

        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new BaseDao(this);

        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();

        Carregadados();

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        bManager.unregisterReceiver(novaAnaliseReceiver);
        super.onPause();
    }

    private void Carregadados() {
        //executa consulta geral de todos os registros cadastrados no banco de dados
        Cursor analise = database.query("analise", campos, null, null, null, null, null);

        if (analise.getCount() > 0) {
            //cria cursor que será exibido na tela, nele serão exibidos
            //todas as analises cadastrados
            textViewCarregando.setVisibility(View.GONE);
            dataSource = new SimpleCursorAdapter(this, R.layout.activity_row, analise,
                    campos, new int[]{R.id.NumeroAnalise, R.id.DataAnalise});
            //relaciona o dataSource ao próprio listview
            listView.setAdapter(dataSource);
        } else {
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectDados() {

    }

    //método executado quando o usuário clica no botão voltar do aparelho
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        //deleta registros inseridos, simplesmente para limpar essa base que é de teste
        //database.execSQL("DELETE FROM ANALISE");

        //fecha a conexão com o Banco de dados
        database.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.atualizar:
                Carregadados();
                break;
            case R.id.pesquisar:
                selectDados();
        }
        return super.onOptionsItemSelected(item);

    }

}
