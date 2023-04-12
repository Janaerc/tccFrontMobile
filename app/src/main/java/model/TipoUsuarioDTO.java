package model;

import java.io.Serializable;


public class TipoUsuarioDTO implements Serializable {
    private Integer id;
    private String nome;

    public TipoUsuarioDTO() {
    }

    public TipoUsuarioDTO(Integer id, String nome) {
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
        return String.valueOf(id);
    }
}
