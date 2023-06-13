package bean;

public class Predio {
    private int id;
    private String nome;
    private int campusId;
    private boolean status;

    public Predio(int id, String nome, int campusId, boolean status) {
        this.id = id;
        this.nome = nome;
        this.campusId = campusId;
        this.status = status;
    }

    public Predio() {
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

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return nome;
    }
}
