package bean;

public class Predio {
    private int id;
    private String nome;
    private int campusId;

    public Predio(int id, String nome, int campusId) {
        this.id = id;
        this.nome = nome;
        this.campusId = campusId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCampusId() {
        return campusId;
    }

    public Predio() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    @Override
    public String toString(){
        return nome;
    }
}
