/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eu
 */
@Entity
@Table(name = "conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c")
    , @NamedQuery(name = "Conta.findByIdConta", query = "SELECT c FROM Conta c WHERE c.idConta = :idConta")
    , @NamedQuery(name = "Conta.findByTipo", query = "SELECT c FROM Conta c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Conta.findByAgencia", query = "SELECT c FROM Conta c WHERE c.agencia = :agencia")
    , @NamedQuery(name = "Conta.findByNumconta", query = "SELECT c FROM Conta c WHERE c.numconta = :numconta")
    , @NamedQuery(name = "Conta.findByTitular", query = "SELECT c FROM Conta c WHERE c.titular = :titular")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConta")
    private Long idConta;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "agencia")
    private String agencia;
    @Column(name = "numconta")
    private String numconta;
    @Column(name = "titular")
    private String titular;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne(optional = false)
    private Empresa empresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConta")
    private List<Deposito> depositoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConta")
    private List<Movimento> movimentoList;

    public Conta() {
    }

    public Conta(Long idConta) {
        this.idConta = idConta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumconta() {
        return numconta;
    }

    public void setNumconta(String numconta) {
        this.numconta = numconta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    @XmlTransient
    public List<Deposito> getDepositoList() {
        return depositoList;
    }

    public void setDepositoList(List<Deposito> depositoList) {
        this.depositoList = depositoList;
    }

    @XmlTransient
    public List<Movimento> getMovimentoList() {
        return movimentoList;
    }

    public void setMovimentoList(List<Movimento> movimentoList) {
        this.movimentoList = movimentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConta != null ? idConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.idConta == null && other.idConta != null) || (this.idConta != null && !this.idConta.equals(other.idConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.tricell.model.Conta[ idConta=" + idConta + " ]";
    }
    
}
