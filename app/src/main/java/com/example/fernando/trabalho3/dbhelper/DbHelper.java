package com.example.fernando.trabalho3.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fernando on 11/12/16.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "filmes";

    public static final String TABLE_FILMES = "filmes";
    public static final String FILMES_COLUMN_ID = "id";
    public static final String FILMES_COLUMN_NOME = "nome";
    public static final String FILMES_COLUMN_SINOPSE = "sinopse";
    public static final String FILMES_COLUMN_URLIMAGEM = "url_imagem";
    public static final String FILMES_COLUMN_QTD_ACESSO = "qtd_acesso";



    public DbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILMES = "CREATE TABLE " + TABLE_FILMES +
                "(" + FILMES_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                FILMES_COLUMN_NOME + " TEXT NOT NULL, "+
                FILMES_COLUMN_SINOPSE + " TEXT NOT NULL, "+
                FILMES_COLUMN_URLIMAGEM + " TEXT NOT NULL, "+
                FILMES_COLUMN_QTD_ACESSO + " INTEGER );";
        db.execSQL(CREATE_TABLE_FILMES);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMES);
        this.onCreate(db);
    }
}
