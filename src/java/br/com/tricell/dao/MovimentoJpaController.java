/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.dao;

import br.com.tricell.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.tricell.model.Usuario;
import br.com.tricell.model.Conta;
import br.com.tricell.model.Movimento;
import br.com.tricell.model.Pessoa;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eu
 */
public class MovimentoJpaController implements Serializable {

    public MovimentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimento movimento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            em.persist(movimento);
            
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimento> findMovimentoEntities() {
        return findMovimentoEntities(true, -1, -1);
    }

    public List<Movimento> findMovimentoDays(){
        EntityManager em = getEntityManager();
        try{
            Query q = em.createNamedQuery("Movimento.findByData");
            q.setParameter("data", new Date());
            return q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            em.close();
        }
    }
    
    public List<Movimento> findMovimentoEntities(int maxResults, int firstResult) {
        return findMovimentoEntities(false, maxResults, firstResult);
    }

    private List<Movimento> findMovimentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movimento findMovimento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimento.class, id);
        } finally {
            em.close();
        }
    }

}
