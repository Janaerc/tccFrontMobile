package model;

import java.io.Serializable;

public class LoginDTO implements Serializable {

    private String cpf;

    private String senha;

    public LoginDTO(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public LoginDTO() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
