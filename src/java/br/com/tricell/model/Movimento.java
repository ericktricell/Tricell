/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eu
 */
@Entity
@Table(name = "movimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimento.findAll", query = "SELECT m FROM Movimento m")
    , @NamedQuery(name = "Movimento.findByIdmovimento", query = "SELECT m FROM Movimento m WHERE m.idmovimento = :idmovimento")
    , @NamedQuery(name = "Movimento.findByTipo", query = "SELECT m FROM Movimento m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Movimento.findByDescricao", query = "SELECT m FROM Movimento m WHERE m.descricao = :descricao")
    , @NamedQuery(name = "Movimento.findByQnt", query = "SELECT m FROM Movimento m WHERE m.qnt = :qnt")
    , @NamedQuery(name = "Movimento.findByVlr", query = "SELECT m FROM Movimento m WHERE m.vlr = :vlr")
    , @NamedQuery(name = "Movimento.findByFormapagamento", query = "SELECT m FROM Movimento m WHERE m.formapagamento = :formapagamento")
    , @NamedQuery(name = "Movimento.findByNparcelas", query = "SELECT m FROM Movimento m WHERE m.nparcelas = :nparcelas")
    , @NamedQuery(name = "Movimento.findByVlrparc", query = "SELECT m FROM Movimento m WHERE m.vlrparc = :vlrparc")
    , @NamedQuery(name = "Movimento.findByCategoria", query = "SELECT m FROM Movimento m WHERE m.categoria = :categoria")})
public class Movimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimento")
    private Long idmovimento;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qnt")
    private Double qnt;
    @Column(name = "vlr")
    private Double vlr;
    @Column(name = "formapagamento")
    private String formapagamento;
    @Column(name = "nparcelas")
    private Integer nparcelas;
    @Column(name = "vlrparc")
    private Double vlrparc;
    @Column(name = "categoria")
    private String categoria;
    @Temporal(TemporalType.DATE)
    private Date dataReg;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idConta", referencedColumnName = "idConta")
    @ManyToOne(optional = false)
    private Conta idConta;
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
    @ManyToOne
    private Pessoa idPessoa;

    public Movimento() {
    }

    public Date getDataReg() {
        return dataReg;
    }

    public void setDataReg(Date dataReg) {
        this.dataReg = dataReg;
    }

    public Movimento(Long idmovimento) {
        this.idmovimento = idmovimento;
    }

    public Long getIdmovimento() {
        return idmovimento;
    }

    public void setIdmovimento(Long idmovimento) {
        this.idmovimento = idmovimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQnt() {
        return qnt;
    }

    public void setQnt(Double qnt) {
        this.qnt = qnt;
    }

    public Double getVlr() {
        return vlr;
    }

    public void setVlr(Double vlr) {
        this.vlr = vlr;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Integer getNparcelas() {
        return nparcelas;
    }

    public void setNparcelas(Integer nparcelas) {
        this.nparcelas = nparcelas;
    }

    public Double getVlrparc() {
        return vlrparc;
    }

    public void setVlrparc(Double vlrparc) {
        this.vlrparc = vlrparc;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Conta getIdConta() {
        return idConta;
    }

    public void setIdConta(Conta idConta) {
        this.idConta = idConta;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimento != null ? idmovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimento)) {
            return false;
        }
        Movimento other = (Movimento) object;
        if ((this.idmovimento == null && other.idmovimento != null) || (this.idmovimento != null && !this.idmovimento.equals(other.idmovimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.tricell.model.Movimento[ idmovimento=" + idmovimento + " ]";
    }
    
}
