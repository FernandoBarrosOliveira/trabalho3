package com.example.fernando.trabalho3.adapter;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernando.trabalho3.FilmeDetalherActivity;
import com.example.fernando.trabalho3.R;
import com.example.fernando.trabalho3.interfaces.OnItemFilmeClickListener;
import com.example.fernando.trabalho3.model.Filme;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 11/12/16.
 */

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.ListaFilmeViewHolder> {
    private List<Filme> listaFilmes = new ArrayList<>();

    private Activity mainActivity;

    public  ListaFilmesAdapter(List<Filme> listaFilmes, Activity activity){
        this.listaFilmes = listaFilmes;
        this.mainActivity = activity;
    }

    @Override
    public ListaFilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_filmes, parent, false);
        return new ListaFilmeViewHolder(view, listaFilmes);
    }

    @Override
    public void onBindViewHolder(ListaFilmeViewHolder holder, int position) {
        final Filme filme = listaFilmes.get(position);

        holder.txtFilme.setText(filme.getNome());
        Picasso.with(mainActivity)
            .load("http://image.tmdb.org/t/p/w500/" +filme.getImagem())
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imgFilme);

    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public static class ListaFilmeViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private ImageView imgFilme;
        private TextView txtFilme;
        private List<Filme> listaFilme;


        public ListaFilmeViewHolder(View itemView, List<Filme> listaFilme) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgFilme = (ImageView) itemView.findViewById(R.id.item_img_filme);
            txtFilme = (TextView) itemView.findViewById(R.id.item_txt_filme);
            this.listaFilme = listaFilme;
        }

        @Override
        public void onClick(View view) {
            Filme filme = this.listaFilme.get(getPosition());
            Intent intent = new Intent(view.getContext(), FilmeDetalherActivity.class);
            intent.putExtra("FILME", (Serializable) filme);
            view.getContext().startActivity(intent);

        }
    }

}
