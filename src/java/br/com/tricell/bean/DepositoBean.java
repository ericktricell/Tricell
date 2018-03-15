/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.ContaJpaController;
import br.com.tricell.dao.DepositoJpaController;
import br.com.tricell.dao.exceptions.NonexistentEntityException;
import br.com.tricell.model.Conta;
import br.com.tricell.model.Deposito;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eu
 */
@ManagedBean
@SessionScoped
public class DepositoBean implements Serializable{
    
    private Deposito dep = new Deposito();
    private List<Deposito> lsitDep;
    private List<Conta> lsConta;
    private EntityManagerFactory emf;

    public DepositoBean() {
//        emf = new FabricaSessao().getEmf();
    }

    public void salvaCaralho() throws IOException{
        new DepositoJpaController(emf).create(dep);
    }
    
    public void pegaDepositos(){
        lsitDep = new DepositoJpaController(emf).findDepositoEntities();
        lsConta = new ContaJpaController(emf).findContaEntities();
        
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Conta> getLsConta() {
        return lsConta;
    }

    public void setLsConta(List<Conta> lsConta) {
        this.lsConta = lsConta;
    }
    
    public Deposito getDep() {
        return dep;
    }

    public void setDep(Deposito dep) {
        this.dep = dep;
    }

    public List<Deposito> getLsitDep() {
        return lsitDep;
    }

    public void setLsitDep(List<Deposito> lsitDep) {
        this.lsitDep = lsitDep;
    }
    
    
}
