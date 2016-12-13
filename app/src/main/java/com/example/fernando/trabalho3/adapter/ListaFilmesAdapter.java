package com.example.fernando.trabalho3.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fernando.trabalho3.R;
import com.example.fernando.trabalho3.interfaces.OnItemFilmeClickListener;
import com.example.fernando.trabalho3.model.Filme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 11/12/16.
 */

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.ListaFilmeViewHolder> {
    private List<Filme> listaFilmes = new ArrayList<>();
    private OnItemFilmeClickListener onItemFilmeClickListener;

    @Override
    public ListaFilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_filmes, parent, false);
        return new ListaFilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaFilmeViewHolder holder, int position) {
        final Filme filme = listaFilmes.get(position);
        View.OnClickListener listener = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onItemFilmeClickListener.OnItemClick(filme);
            }
        };

        holder.txtFilme.setOnClickListener(listener);
        holder.imgFilme.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public static class ListaFilmeViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFilme;
        private TextView txtFilme;
        public ListaFilmeViewHolder(View itemView) {
            super(itemView);
            imgFilme = (ImageView) itemView.findViewById(R.id.item_img_filme);
            txtFilme = (TextView) itemView.findViewById(R.id.item_txt_filme);
        }
    }

    public OnItemFilmeClickListener getOnItemFilmeClickListener() {
        return onItemFilmeClickListener;
    }

    public void setOnItemFilmeClickListener(OnItemFilmeClickListener onItemFilmeClickListener) {
        this.onItemFilmeClickListener = onItemFilmeClickListener;
    }
}
