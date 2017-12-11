package com.example.rcksuporte05.recycleractionmode.Model;

/**
 * Created by RCKSUPORTE05 on 11/12/2017.
 */

public class ObjetoLista {
    private int id;
    private String nome;
    private String descricao;
    private String outraDescricao;

    public ObjetoLista(int id, String nome, String descricao, String outraDescricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.outraDescricao = outraDescricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOutraDescricao() {
        return outraDescricao;
    }

    public void setOutraDescricao(String outraDescricao) {
        this.outraDescricao = outraDescricao;
    }
}
