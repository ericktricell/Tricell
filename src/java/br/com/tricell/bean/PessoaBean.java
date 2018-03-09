/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.PessoaJpaController;
import br.com.tricell.model.Pessoa;
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
public class PessoaBean {
    
    private Pessoa pes = new Pessoa();
    private List<Pessoa> lsPes;
    private EntityManagerFactory emf;

    public PessoaBean() {
//       emf = new FabricaSessao().getEmf();
    }

    public void salvar(){
        new PessoaJpaController(emf).create(pes);
    }
    
    public void pegaDados(){
        lsPes = new PessoaJpaController(emf).findPessoaEntities();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Pessoa getPes() {
        return pes;
    }

    public void setPes(Pessoa pes) {
        this.pes = pes;
    }

    public List<Pessoa> getLsPes() {
        return lsPes;
    }

    public void setLsPes(List<Pessoa> lsPes) {
        this.lsPes = lsPes;
    }
    
}
