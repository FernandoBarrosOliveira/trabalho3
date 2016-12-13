package com.example.fernando.trabalho3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernando.trabalho3.dbhelper.DbHelper;
import com.example.fernando.trabalho3.model.Filme;

import java.util.ArrayList;
import java.util.List;

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
        contentValues.put(dbHelper.FILMES_COLUMN_QTD_ACESSO, filme.getQuantidadeAcesso()+1);

        long id = db.insert(dbHelper.TABLE_FILMES, null, contentValues);
        db.close();
    }


    public boolean filmeExiste(Filme filme) {
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT count(*) FROM " + dbHelper.TABLE_FILMES +"  WHERE " + dbHelper.FILMES_COLUMN_NOME
                + " = \"" +filme.getNome() +"\"";
        long quantidadeFilmeCadastrado = DatabaseUtils.longForQuery(db, sql,null);
        return quantidadeFilmeCadastrado > 0;
    }


    public void atualizaQtdAcesso(Filme filme) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "UPDATE " + dbHelper.TABLE_FILMES + " SET " + dbHelper.FILMES_COLUMN_QTD_ACESSO +
                " = " + dbHelper.FILMES_COLUMN_QTD_ACESSO + " +1 WHERE " + dbHelper.FILMES_COLUMN_NOME + " = \"" + filme.getNome() +"\"";

        db.execSQL(sql);
        db.close();
    }

    public List<Filme> listaFilme() {

        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + dbHelper.TABLE_FILMES + " ORDER BY " +dbHelper.FILMES_COLUMN_QTD_ACESSO +" DESC";

        Cursor cursor = db.rawQuery(sql, null);

        List<Filme> filmes = new ArrayList<>();

        while (cursor.moveToNext()) {
            Filme filme = new Filme();
            filme.setNome(cursor.getString(cursor.getColumnIndex(dbHelper.FILMES_COLUMN_NOME)));
            filme.setSinopse(cursor.getString(cursor.getColumnIndex(dbHelper.FILMES_COLUMN_SINOPSE)));
            filme.setImagem(cursor.getString(cursor.getColumnIndex(dbHelper.FILMES_COLUMN_URLIMAGEM)));
            filme.setQuantidadeAcesso(Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.FILMES_COLUMN_QTD_ACESSO))));

            filmes.add(filme);
        }
        cursor.close();
        return filmes;

    }
}
