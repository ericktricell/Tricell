/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.dao;

import br.com.tricell.dao.exceptions.IllegalOrphanException;
import br.com.tricell.dao.exceptions.NonexistentEntityException;
import br.com.tricell.model.Conta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eu
 */
public class ContaJpaController implements Serializable {

    public ContaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conta conta) {
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            em.persist(conta);
            
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conta conta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            conta = em.merge(conta);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = conta.getIdConta();
                if (findConta(id) == null) {
                    throw new NonexistentEntityException("The conta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta conta;
            try {
                conta = em.getReference(Conta.class, id);
                conta.getIdConta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conta with id " + id + " no longer exists.", enfe);
            }
            em.remove(conta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conta> findContaEntities() {
        return findContaEntities(true, -1, -1);
    }

    public List<Conta> findContaEntities(int maxResults, int firstResult) {
        return findContaEntities(false, maxResults, firstResult);
    }

    private List<Conta> findContaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conta.class));
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

    public Conta findConta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conta.class, id);
        } finally {
            em.close();
        }
    }

    public Conta findContaNum(String string) {
        EntityManager em = getEntityManager();
        try{
            Query q = em.createNamedQuery("Conta.findByNumconta");
            q.setParameter("numconta", string);
            return (Conta) q.getSingleResult();
        } catch(Exception e){
            return null;
        }finally{
            em.close();
        }
    }
    
}
