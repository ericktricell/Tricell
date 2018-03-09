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
import javax.persistence.criteria.Root;
import br.com.tricell.model.Deposito;
import java.util.ArrayList;
import java.util.List;
import br.com.tricell.model.Movimento;
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
        if (conta.getDepositoList() == null) {
            conta.setDepositoList(new ArrayList<Deposito>());
        }
        if (conta.getMovimentoList() == null) {
            conta.setMovimentoList(new ArrayList<Movimento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Deposito> attachedDepositoList = new ArrayList<Deposito>();
            for (Deposito depositoListDepositoToAttach : conta.getDepositoList()) {
                depositoListDepositoToAttach = em.getReference(depositoListDepositoToAttach.getClass(), depositoListDepositoToAttach.getIddeposito());
                attachedDepositoList.add(depositoListDepositoToAttach);
            }
            conta.setDepositoList(attachedDepositoList);
            List<Movimento> attachedMovimentoList = new ArrayList<Movimento>();
            for (Movimento movimentoListMovimentoToAttach : conta.getMovimentoList()) {
                movimentoListMovimentoToAttach = em.getReference(movimentoListMovimentoToAttach.getClass(), movimentoListMovimentoToAttach.getIdmovimento());
                attachedMovimentoList.add(movimentoListMovimentoToAttach);
            }
            conta.setMovimentoList(attachedMovimentoList);
            em.persist(conta);
            for (Deposito depositoListDeposito : conta.getDepositoList()) {
                Conta oldIdContaOfDepositoListDeposito = depositoListDeposito.getIdConta();
                depositoListDeposito.setIdConta(conta);
                depositoListDeposito = em.merge(depositoListDeposito);
                if (oldIdContaOfDepositoListDeposito != null) {
                    oldIdContaOfDepositoListDeposito.getDepositoList().remove(depositoListDeposito);
                    oldIdContaOfDepositoListDeposito = em.merge(oldIdContaOfDepositoListDeposito);
                }
            }
            for (Movimento movimentoListMovimento : conta.getMovimentoList()) {
                Conta oldIdContaOfMovimentoListMovimento = movimentoListMovimento.getIdConta();
                movimentoListMovimento.setIdConta(conta);
                movimentoListMovimento = em.merge(movimentoListMovimento);
                if (oldIdContaOfMovimentoListMovimento != null) {
                    oldIdContaOfMovimentoListMovimento.getMovimentoList().remove(movimentoListMovimento);
                    oldIdContaOfMovimentoListMovimento = em.merge(oldIdContaOfMovimentoListMovimento);
                }
            }
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
            Conta persistentConta = em.find(Conta.class, conta.getIdConta());
            List<Deposito> depositoListOld = persistentConta.getDepositoList();
            List<Deposito> depositoListNew = conta.getDepositoList();
            List<Movimento> movimentoListOld = persistentConta.getMovimentoList();
            List<Movimento> movimentoListNew = conta.getMovimentoList();
            List<String> illegalOrphanMessages = null;
            for (Deposito depositoListOldDeposito : depositoListOld) {
                if (!depositoListNew.contains(depositoListOldDeposito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Deposito " + depositoListOldDeposito + " since its idConta field is not nullable.");
                }
            }
            for (Movimento movimentoListOldMovimento : movimentoListOld) {
                if (!movimentoListNew.contains(movimentoListOldMovimento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimento " + movimentoListOldMovimento + " since its idConta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Deposito> attachedDepositoListNew = new ArrayList<Deposito>();
            for (Deposito depositoListNewDepositoToAttach : depositoListNew) {
                depositoListNewDepositoToAttach = em.getReference(depositoListNewDepositoToAttach.getClass(), depositoListNewDepositoToAttach.getIddeposito());
                attachedDepositoListNew.add(depositoListNewDepositoToAttach);
            }
            depositoListNew = attachedDepositoListNew;
            conta.setDepositoList(depositoListNew);
            List<Movimento> attachedMovimentoListNew = new ArrayList<Movimento>();
            for (Movimento movimentoListNewMovimentoToAttach : movimentoListNew) {
                movimentoListNewMovimentoToAttach = em.getReference(movimentoListNewMovimentoToAttach.getClass(), movimentoListNewMovimentoToAttach.getIdmovimento());
                attachedMovimentoListNew.add(movimentoListNewMovimentoToAttach);
            }
            movimentoListNew = attachedMovimentoListNew;
            conta.setMovimentoList(movimentoListNew);
            conta = em.merge(conta);
            for (Deposito depositoListNewDeposito : depositoListNew) {
                if (!depositoListOld.contains(depositoListNewDeposito)) {
                    Conta oldIdContaOfDepositoListNewDeposito = depositoListNewDeposito.getIdConta();
                    depositoListNewDeposito.setIdConta(conta);
                    depositoListNewDeposito = em.merge(depositoListNewDeposito);
                    if (oldIdContaOfDepositoListNewDeposito != null && !oldIdContaOfDepositoListNewDeposito.equals(conta)) {
                        oldIdContaOfDepositoListNewDeposito.getDepositoList().remove(depositoListNewDeposito);
                        oldIdContaOfDepositoListNewDeposito = em.merge(oldIdContaOfDepositoListNewDeposito);
                    }
                }
            }
            for (Movimento movimentoListNewMovimento : movimentoListNew) {
                if (!movimentoListOld.contains(movimentoListNewMovimento)) {
                    Conta oldIdContaOfMovimentoListNewMovimento = movimentoListNewMovimento.getIdConta();
                    movimentoListNewMovimento.setIdConta(conta);
                    movimentoListNewMovimento = em.merge(movimentoListNewMovimento);
                    if (oldIdContaOfMovimentoListNewMovimento != null && !oldIdContaOfMovimentoListNewMovimento.equals(conta)) {
                        oldIdContaOfMovimentoListNewMovimento.getMovimentoList().remove(movimentoListNewMovimento);
                        oldIdContaOfMovimentoListNewMovimento = em.merge(oldIdContaOfMovimentoListNewMovimento);
                    }
                }
            }
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
            List<String> illegalOrphanMessages = null;
            List<Deposito> depositoListOrphanCheck = conta.getDepositoList();
            for (Deposito depositoListOrphanCheckDeposito : depositoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Deposito " + depositoListOrphanCheckDeposito + " in its depositoList field has a non-nullable idConta field.");
            }
            List<Movimento> movimentoListOrphanCheck = conta.getMovimentoList();
            for (Movimento movimentoListOrphanCheckMovimento : movimentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Movimento " + movimentoListOrphanCheckMovimento + " in its movimentoList field has a non-nullable idConta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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

    public int getContaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conta> rt = cq.from(Conta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
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
