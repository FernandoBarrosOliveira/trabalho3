package com.example.fernando.trabalho3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fernando.trabalho3.model.Filme;
import com.squareup.picasso.Picasso;

public class FilmeDetalherActivity extends AppCompatActivity {
    private ImageView imgPoster;
    private TextView txtNomeFilme, txtSinopse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detalher);

        imgPoster = (ImageView) findViewById(R.id.filme_detalhe_imgposter);
        txtNomeFilme = (TextView) findViewById(R.id.filme_detalhe_txtnome);
        txtSinopse = (TextView) findViewById(R.id.filme_detalhe_sinopse);
        Intent intent = getIntent();
        Filme filme = (Filme) intent.getSerializableExtra("FILME");
        montaExibicao(filme);

    }

    private void montaExibicao(Filme filme){
        txtNomeFilme.setText(filme.getNome());
        txtNomeFilme.setText(filme.getSinopse());
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/" +filme.getImagem())
                .placeholder(R.mipmap.ic_launcher)
                .into(imgPoster);
    }
}
