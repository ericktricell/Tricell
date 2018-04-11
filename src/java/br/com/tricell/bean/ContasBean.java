/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.ContaJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Conta;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author Eu
 */
@ManagedBean(name = "contasBean")
@SessionScoped
public class ContasBean implements Serializable{
    
    private Conta c = new Conta();
    private List<Conta> lisC = new ArrayList<>();
    private EntityManagerFactory emf;

    public ContasBean() {
       // emf = new FabricaSessao().getEmf();
    }

    public void create() throws IOException{
        new ContaJpaController(emf).create(c);
        c = new Conta();
        c.setIdConta(new Long("0"));
        new Menu().contas();
    }
   
    public void contas(){
        lisC = new ContaJpaController(emf).findContaEntities();
    }
    
    public Conta getC() {
        return c;
    }

    public void setC(Conta c) {
        this.c = c;
    }

    public List<Conta> getLisC() {
        return lisC;
    }

    public void setLisC(List<Conta> lisC) {
        this.lisC = lisC;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
}
