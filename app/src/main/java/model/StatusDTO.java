package model;

import java.io.Serializable;


public class StatusDTO implements Serializable{
    private Integer id;
    private String nome;

    public StatusDTO() {
    }

    public StatusDTO(Integer id, String nome) {
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
        return "StatusDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
