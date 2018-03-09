/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.ContaJpaController;
import br.com.tricell.dao.MovimentoJpaController;
import br.com.tricell.dao.PessoaJpaController;
import br.com.tricell.model.Conta;
import br.com.tricell.model.Movimento;
import br.com.tricell.model.Pessoa;
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
public class MovimentoBean implements Serializable{
   
    private EntityManagerFactory emf;
    private Movimento mov = new Movimento();
    private List<Movimento> lsMov;
    private List<Conta> lsConta;
    private List<Pessoa> lsPes;

    public void pegaDados(){
        lsConta = new ContaJpaController(emf).findContaEntities();
        lsPes = new PessoaJpaController(emf).findPessoaEntities();
        lsMov = new MovimentoJpaController(emf).findMovimentoEntities();
    }
    
    public List<Conta> getLsConta() {
        return lsConta;
    }

    public void setLsConta(List<Conta> lsConta) {
        this.lsConta = lsConta;
    }

    public List<Pessoa> getLsPes() {
        return lsPes;
    }

    public void setLsPes(List<Pessoa> lsPes) {
        this.lsPes = lsPes;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Movimento getMov() {
        return mov;
    }

    public void setMov(Movimento mov) {
        this.mov = mov;
    }

    public List<Movimento> getLsMov() {
        return lsMov;
    }

    public void setLsMov(List<Movimento> lsMov) {
        this.lsMov = lsMov;
    }
    
    
}
