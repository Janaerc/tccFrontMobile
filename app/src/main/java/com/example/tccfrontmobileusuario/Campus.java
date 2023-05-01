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
}

