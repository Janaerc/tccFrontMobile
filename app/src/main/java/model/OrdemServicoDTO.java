package model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


public class OrdemServicoDTO implements Serializable {
    private Integer id;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "pt-BR", timezone = "GMT")
    private Date dataAbertura;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "pt-BR", timezone = "GMT")
    private Date dataFinalizacao;

    private String descricaoProblema;

    private String numeroOS;
    private EspecialidadeDTO especialidadeId;
    private ChamadoDTO chamado;

    private UsuarioDTO usuarioOperarioId;

    private PredioDTO predioId;
    public OrdemServicoDTO() {
    }

    public OrdemServicoDTO(Integer id, String descricao, Date dataAbertura, Date dataFinalizacao, String descricaoProblema, String numeroOS, EspecialidadeDTO especialidadeId, ChamadoDTO chamado, UsuarioDTO usuarioOperarioId, PredioDTO predioId) {
        this.id = id;
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.dataFinalizacao = dataFinalizacao;
        this.descricaoProblema = descricaoProblema;
        this.numeroOS = numeroOS;
        this.especialidadeId = especialidadeId;
        this.chamado = chamado;
        this.usuarioOperarioId = usuarioOperarioId;
        this.predioId = predioId;
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

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(String numeroOS) {
        this.numeroOS = numeroOS;
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

    public UsuarioDTO getUsuarioOperarioId() {
        return usuarioOperarioId;
    }

    public void setUsuarioOperarioId(UsuarioDTO usuarioOperarioId) {
        this.usuarioOperarioId = usuarioOperarioId;
    }

    public PredioDTO getPredioId() {
        return predioId;
    }

    public void setPredioId(PredioDTO predioId) {
        this.predioId = predioId;
    }

    @Override
    public String toString() {
        return "OrdemServicoDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataAbertura=" + dataAbertura +
                ", dataFinalizacao=" + dataFinalizacao +
                ", descricaoProblema='" + descricaoProblema + '\'' +
                ", numeroOS='" + numeroOS + '\'' +
                ", especialidadeId=" + especialidadeId +
                ", chamado=" + chamado +
                ", usuarioOperarioId=" + usuarioOperarioId +
                ", predioId=" + predioId +
                '}';
    }

}

