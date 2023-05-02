package com.example.tccfrontmobileusuario;

public class Campus {
    private int id;
    private String nome;

    public Campus(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Campus() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome;
    }
}

