package com.hackathon.model;

public class Agente {
    private Integer id;
    private String nome;

    public Agente(String nome) {
        this.nome = nome;
    }

    public Agente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome;
    }
}
