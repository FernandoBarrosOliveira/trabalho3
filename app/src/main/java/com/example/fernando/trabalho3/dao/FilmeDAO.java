package com.example.fernando.trabalho3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernando.trabalho3.dbhelper.DbHelper;
import com.example.fernando.trabalho3.model.Filme;

/**
 * Created by fernando on 11/12/16.
 */

public class FilmeDAO {
    private Context context;

    public FilmeDAO(Context context){
        this.context = context;
    }

    public void insertFilme(Filme filme){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(dbHelper.FILMES_COLUMN_NOME, filme.getNome() );
        contentValues.put(dbHelper.FILMES_COLUMN_SINOPSE, filme.getSinopse() );
        contentValues.put(dbHelper.FILMES_COLUMN_URLIMAGEM, filme.getImagem());
        contentValues.put(dbHelper.FILMES_COLUMN_QTD_ACESSO, filme.getQuantidadeAcesso());

        db.insert(dbHelper.TABLE_FILMES, null, contentValues);
        db.close();
    }



}
