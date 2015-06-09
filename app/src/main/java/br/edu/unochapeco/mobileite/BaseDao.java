package br.edu.unochapeco.mobileite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import br.edu.unochapeco.mobileite.constantes.Constantes;

/**
 * Created by Junior on 01/06/2015.
 */
public class BaseDao extends SQLiteOpenHelper {

    public static final String TBL_Analise = "Analise";
    public static final String ANALISE_ID = "_id";
    public static final String ANALISE_NUMERO = "numero";
    public static final String ANALISE_DATAENVIO = "dataenvio";
    public static final String ANALISE_DATAANALISE = "dataanalise";
    public static final String ANALISE_CBT = "cbt";
    public static final String ANALISE_CCS = "ccs";
    public static final String ANALISE_LATITUDE = "latitude";
    public static final String ANALISE_LONGITUDE = "longitude";
    public static final String ANALISE_PROTEINA = "proteina";
    public static final String ANALISE_GORDURA = "gordura";

    private static final String DATABASE_NAME = "mobileite.db";
    private static final int DATABASE_VERSION = 1;

    //Estrutura da tabela Agenda (sql statement)
    private static final String CREATE_AGENDA = "create table " +
            TBL_Analise + "( " + ANALISE_ID + " integer primary key autoincrement, " +
            ANALISE_NUMERO + " integer not null, " +
            ANALISE_DATAENVIO + " datetime not null, " +
            ANALISE_DATAANALISE + " datetime not null, " +
            ANALISE_CBT + " integer not null, " +
            ANALISE_CCS + " integer not null, " +
            ANALISE_LATITUDE + " double not null, " +
            ANALISE_LONGITUDE + " double not null, " +
            ANALISE_PROTEINA + " double not null, " +
            ANALISE_GORDURA + " double not null);";

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

    /**
     * Atualiza se existe, insere caso não exista.
     *
     * @param analise
     */
    public void upsert(Analise analise) {
        SQLiteDatabase database = getWritableDatabase();
        if(existe(analise)){
            database.update(TBL_Analise, analise.getContentValues(), ANALISE_NUMERO + " = ?", new String[]{analise.getNumero().toString()});
        }else {
             database.insert(TBL_Analise, null, analise.getContentValues());
        }
        database.close();
    }


    public boolean existe(Analise analise) {
        if(analise == null || analise.getNumero() == null){
            return false;
        }

        Cursor cursor = getReadableDatabase().query(TBL_Analise, new String[]{ANALISE_NUMERO},
                ANALISE_NUMERO + " = ?", new String[]{analise.getNumero().toString()}, null, null, null);
        return cursor.getCount() > 0;
    }

    public Analise getAnalisePorId(Integer id) {
        if(id == null ){
            return null;
        }

        Cursor cursor = getReadableDatabase().query(TBL_Analise, null,
                ANALISE_ID + " = ?", new String[]{id.toString()}, null, null, null);

        Analise analise = new Analise();
        while(cursor.moveToNext()) {
            analise.setCbt(cursor.getInt(cursor.getColumnIndex(ANALISE_CBT)));
            analise.setCcs(cursor.getInt(cursor.getColumnIndex(ANALISE_CCS)));
            analise.setDataAnalise(Constantes.dataFromString(cursor.getString(cursor.getColumnIndex(ANALISE_DATAANALISE))));
            analise.setDataEnvio(Constantes.dataFromString(cursor.getString(cursor.getColumnIndex(ANALISE_DATAENVIO))));
            analise.setGordura(cursor.getFloat(cursor.getColumnIndex(ANALISE_GORDURA)));
            analise.setNumero(cursor.getInt(cursor.getColumnIndex(ANALISE_NUMERO)));
            analise.setProteina(cursor.getFloat(cursor.getColumnIndex(ANALISE_PROTEINA)));
            analise.setLatitude(cursor.getFloat(cursor.getColumnIndex(ANALISE_LATITUDE)));
            analise.setLongitude(cursor.getFloat(cursor.getColumnIndex(ANALISE_LONGITUDE)));
        }

        return analise;
    }


}
