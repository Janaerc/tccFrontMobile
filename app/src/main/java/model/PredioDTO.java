package model;

import java.io.Serializable;


public class PredioDTO implements Serializable{
    private Integer id;
    private String nome;
    private CampusDTO campusId;

    private boolean status;

    public PredioDTO() {
    }

    public PredioDTO(Integer id, String nome, CampusDTO campusId, boolean status) {
        this.id = id;
        this.nome = nome;
        this.campusId = campusId;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PredioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", campusId=" + campusId +
                ", status=" + status +
                '}';
    }



}
