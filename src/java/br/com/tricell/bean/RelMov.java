/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.MovimentoJpaController;
import br.com.tricell.model.Movimento;
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
public class RelMov {
    private EntityManagerFactory emf;
    private Movimento mov = new Movimento();
    private List<Movimento> lsMov;

    public void pegaTodos(){
        lsMov = new MovimentoJpaController(emf).findMovimentoEntities();
    }
    
    public double somaReceitas(){
        double tot = 0.0;
        for (Movimento m : lsMov){
            if(m.getTipo().equals("Receita"))
                tot += m.getVlr() * m.getQnt();
        }
        return tot;
    }
    
    public double somaDespesas(){
        double tot = 0.0;
        for (Movimento m : lsMov){
            if(m.getTipo().equals("Despesa"))
                tot += m.getVlr() * m.getQnt();
        }
        return tot;
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
