package model;

import java.io.Serializable;


public class PredioDTO implements Serializable{
    private Integer id;
    private String nome;
    private CampusDTO campusId;

    public PredioDTO() {
    }

    public PredioDTO(Integer id, String nome, CampusDTO campusId) {
        this.id = id;
        this.nome = nome;
        this.campusId = campusId;
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

    public CampusDTO getCampusId() {
        return campusId;
    }

    public void setCampusId(CampusDTO campusId) {
        this.campusId = campusId;
    }


}
