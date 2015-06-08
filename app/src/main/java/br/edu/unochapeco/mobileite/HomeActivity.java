package br.edu.unochapeco.mobileite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class HomeActivity extends ActionBarActivity {
    private static final String campos[] = {"_id", "dataanalise"};
    ListView listView;
    BaseDao helper;
    private SQLiteDatabase database;
    private CursorAdapter dataSource;
    private String numeroAnalise1;

    /**
     * Chamado quando a Activity é exeutada pela primeira vez.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.listView1);

        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new BaseDao(this);

        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();
        InserindoDados();
        Carregadados();

    }

    private void InserindoDados() {
        //insere dados no banco de dados
        database.execSQL("INSERT INTO analise (dataenvio, dataanalise, cbr, ccs, proteina, gordura) VALUES " +
                "('19/02/2015', '19/02/2015', '30%',  '25%', '40', '55')");
        database.execSQL("INSERT INTO analise (dataenvio, dataanalise, cbr, ccs, proteina, gordura) VALUES " +
                "('19/03/2015', '19/03/2015', '30%',  '25%', '40', '55')");
        database.execSQL("INSERT INTO analise (dataenvio, dataanalise, cbr, ccs, proteina, gordura) VALUES " +
                "('19/04/2015', '19/04/2015', '30%',  '25%', '40', '55')");
        database.execSQL("INSERT INTO analise (dataenvio, dataanalise, cbr, ccs, proteina, gordura) VALUES " +
                "('19/05/2015', '19/05/2015', '30%',  '25%', '40', '55')");
        database.execSQL("INSERT INTO analise (dataenvio, dataanalise, cbr, ccs, proteina, gordura) VALUES " +
                "('19/06/2015', '19/06/2015', '30%',  '25%', '40', '55')");
        database.execSQL("INSERT INTO analise (dataenvio, dataanalise, cbr, ccs, proteina, gordura) VALUES " +
                "('19/07/2015', '19/07/2015', '30%',  '25%', '40', '55')");
    }

    private void Carregadados() {
        //executa consulta geral de todos os registros cadastrados no banco de dados
        Cursor analise = database.query("analise", campos, null, null, null, null, null);

        if (analise.getCount() > 0) {
            //cria cursor que será exibido na tela, nele serão exibidos
            //todas as analises cadastrados
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
        database.execSQL("DELETE FROM ANALISE");

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

    public void visualizarDados(View view) {
        Intent intent = new Intent(this, AnaliseDados.class);
        startActivity(intent);
    }
}
