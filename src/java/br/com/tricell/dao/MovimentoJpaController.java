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
            Usuario idusuario = movimento.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                movimento.setIdusuario(idusuario);
            }
            Conta idConta = movimento.getIdConta();
            if (idConta != null) {
                idConta = em.getReference(idConta.getClass(), idConta.getIdConta());
                movimento.setIdConta(idConta);
            }
            Pessoa idPessoa = movimento.getIdPessoa();
            if (idPessoa != null) {
                idPessoa = em.getReference(idPessoa.getClass(), idPessoa.getIdPessoa());
                movimento.setIdPessoa(idPessoa);
            }
            em.persist(movimento);
            if (idusuario != null) {
                idusuario.getMovimentoList().add(movimento);
                idusuario = em.merge(idusuario);
            }
            if (idConta != null) {
                idConta.getMovimentoList().add(movimento);
                idConta = em.merge(idConta);
            }
            if (idPessoa != null) {
                idPessoa.getMovimentoList().add(movimento);
                idPessoa = em.merge(idPessoa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimento movimento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimento persistentMovimento = em.find(Movimento.class, movimento.getIdmovimento());
            Usuario idusuarioOld = persistentMovimento.getIdusuario();
            Usuario idusuarioNew = movimento.getIdusuario();
            Conta idContaOld = persistentMovimento.getIdConta();
            Conta idContaNew = movimento.getIdConta();
            Pessoa idPessoaOld = persistentMovimento.getIdPessoa();
            Pessoa idPessoaNew = movimento.getIdPessoa();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                movimento.setIdusuario(idusuarioNew);
            }
            if (idContaNew != null) {
                idContaNew = em.getReference(idContaNew.getClass(), idContaNew.getIdConta());
                movimento.setIdConta(idContaNew);
            }
            if (idPessoaNew != null) {
                idPessoaNew = em.getReference(idPessoaNew.getClass(), idPessoaNew.getIdPessoa());
                movimento.setIdPessoa(idPessoaNew);
            }
            movimento = em.merge(movimento);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getMovimentoList().remove(movimento);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getMovimentoList().add(movimento);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idContaOld != null && !idContaOld.equals(idContaNew)) {
                idContaOld.getMovimentoList().remove(movimento);
                idContaOld = em.merge(idContaOld);
            }
            if (idContaNew != null && !idContaNew.equals(idContaOld)) {
                idContaNew.getMovimentoList().add(movimento);
                idContaNew = em.merge(idContaNew);
            }
            if (idPessoaOld != null && !idPessoaOld.equals(idPessoaNew)) {
                idPessoaOld.getMovimentoList().remove(movimento);
                idPessoaOld = em.merge(idPessoaOld);
            }
            if (idPessoaNew != null && !idPessoaNew.equals(idPessoaOld)) {
                idPessoaNew.getMovimentoList().add(movimento);
                idPessoaNew = em.merge(idPessoaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = movimento.getIdmovimento();
                if (findMovimento(id) == null) {
                    throw new NonexistentEntityException("The movimento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimento movimento;
            try {
                movimento = em.getReference(Movimento.class, id);
                movimento.getIdmovimento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimento with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = movimento.getIdusuario();
            if (idusuario != null) {
                idusuario.getMovimentoList().remove(movimento);
                idusuario = em.merge(idusuario);
            }
            Conta idConta = movimento.getIdConta();
            if (idConta != null) {
                idConta.getMovimentoList().remove(movimento);
                idConta = em.merge(idConta);
            }
            Pessoa idPessoa = movimento.getIdPessoa();
            if (idPessoa != null) {
                idPessoa.getMovimentoList().remove(movimento);
                idPessoa = em.merge(idPessoa);
            }
            em.remove(movimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimento> findMovimentoEntities() {
        return findMovimentoEntities(true, -1, -1);
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

    public int getMovimentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimento> rt = cq.from(Movimento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
