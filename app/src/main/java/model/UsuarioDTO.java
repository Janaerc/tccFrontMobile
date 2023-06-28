package model;

import java.io.Serializable;

public class UsuarioDTO implements Serializable{

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private Boolean bloqueio;
    private EspecialidadeDTO especialidadeId;
    private TipoUsuarioDTO tipoUsuarioId;

    private String salt;

    public UsuarioDTO(Integer id, String nome, String cpf, String telefone, String email, String senha, Boolean bloqueio, EspecialidadeDTO especialidadeId, TipoUsuarioDTO tipoUsuarioId, String salt) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.bloqueio = bloqueio;
        this.especialidadeId = especialidadeId;
        this.tipoUsuarioId = tipoUsuarioId;
        this.salt = salt;
    }

    public UsuarioDTO() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    public EspecialidadeDTO getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(EspecialidadeDTO especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public TipoUsuarioDTO getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(TipoUsuarioDTO tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", bloqueio=" + bloqueio +
                ", especialidadeId=" + especialidadeId +
                ", tipoUsuarioId=" + tipoUsuarioId +
                ", salt=" + salt +
                '}';
    }

}
