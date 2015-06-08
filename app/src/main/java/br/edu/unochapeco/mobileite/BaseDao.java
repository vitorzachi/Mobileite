package br.edu.unochapeco.mobileite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Junior on 01/06/2015.
 */
public class BaseDao extends SQLiteOpenHelper{

    public static final String TBL_Analise = "Analise";
    public static final String ANALISE_ID = "_id";
    public static final String ANALISE_DATAENVIO = "dataenvio";
    public static final String ANALISE_DATAANALISE = "dataanalise";
    public static final String ANALISE_CBR = "cbr";
    public static final String ANALISE_CCS = "ccs";
    public static final String ANALISE_PROTEINA = "proteina";
    public static final String ANALISE_GORDURA = "gordura";

    private static final String DATABASE_NAME = "mobileite.db";
    private static final int DATABASE_VERSION = 1;

    //Estrutura da tabela Agenda (sql statement)
    private static final String CREATE_AGENDA = "create table " +
            TBL_Analise + "( " + ANALISE_ID       + " integer primary key autoincrement, " +
            ANALISE_DATAENVIO     + " text not null, " +
            ANALISE_DATAANALISE + " text not null, " +
            ANALISE_CBR + " text not null, " +
            ANALISE_CCS     + " text not null, " +
            ANALISE_PROTEINA + " text not null, " +
            ANALISE_GORDURA + " text not null);";

    public BaseDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        //Criação da tabela
        database.execSQL(CREATE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso seja necessário mudar a estrutura da tabela
        //deverá primeiro excluir a tabela e depois recriá-la
        db.execSQL("DROP TABLE IF EXISTS " + TBL_Analise);
        onCreate(db);
    }
}
