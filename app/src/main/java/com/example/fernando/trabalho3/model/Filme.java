package com.example.fernando.trabalho3.model;

import java.io.Serializable;

/**
 * Created by fernando on 11/12/16.
 */

public class Filme implements Serializable {
    private String nome;
    private String sinopse;
    private String imagem;
    private int quantidadeAcesso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getQuantidadeAcesso() {
        return quantidadeAcesso;
    }

    public void setQuantidadeAcesso(int quantidadeAcesso) {
        this.quantidadeAcesso = quantidadeAcesso;
    }
}
