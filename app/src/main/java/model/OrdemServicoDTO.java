package model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


public class OrdemServicoDTO implements Serializable {
    private Integer id;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private Date dataAbertura;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private Date dataFinalizacao;
    private String nomeResponsavelDepartamento;
    private EspecialidadeDTO especialidadeId;
    private ChamadoDTO chamado;
    private UsuarioDTO usuario;

    private UsuarioDTO usuarioOperarioId;


    public OrdemServicoDTO() {
    }

    public OrdemServicoDTO(Integer id, String descricao, Date dataAbertura, Date dataFinalizacao, String nomeResponsavelDepartamento, EspecialidadeDTO especialidadeId, ChamadoDTO chamado, UsuarioDTO usuario, UsuarioDTO usuarioOperarioId) {
        this.id = id;
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.dataFinalizacao = dataFinalizacao;
        this.nomeResponsavelDepartamento = nomeResponsavelDepartamento;
        this.especialidadeId = especialidadeId;
        this.chamado = chamado;
        this.usuario = usuario;
        this.usuarioOperarioId = usuarioOperarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getNomeResponsavelDepartamento() {
        return nomeResponsavelDepartamento;
    }

    public void setNomeResponsavelDepartamento(String nomeResponsavelDepartamento) {
        this.nomeResponsavelDepartamento = nomeResponsavelDepartamento;
    }

    public EspecialidadeDTO getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(EspecialidadeDTO especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public ChamadoDTO getChamado() {
        return chamado;
    }

    public void setChamado(ChamadoDTO chamado) {
        this.chamado = chamado;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDTO getUsuarioOperarioId() {
        return usuarioOperarioId;
    }

    public void setUsuarioOperarioId(UsuarioDTO usuarioOperarioId) {
        this.usuarioOperarioId = usuarioOperarioId;
    }
}

