package model;
import java.io.Serializable;


public class CampusDTO implements Serializable {
    private Integer id;
    private String nome;

    public CampusDTO() {
    }

    public CampusDTO(Integer id, String nome) {
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

}
