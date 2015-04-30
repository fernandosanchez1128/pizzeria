/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Itempedidopizza;
import Logica.Pizzabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class PizzabaseJpaController implements Serializable {

    public PizzabaseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pizzabase pizzabase) {
        if (pizzabase.getItempedidopizzaCollection() == null) {
            pizzabase.setItempedidopizzaCollection(new ArrayList<Itempedidopizza>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itempedidopizza> attachedItempedidopizzaCollection = new ArrayList<Itempedidopizza>();
            for (Itempedidopizza itempedidopizzaCollectionItempedidopizzaToAttach : pizzabase.getItempedidopizzaCollection()) {
                itempedidopizzaCollectionItempedidopizzaToAttach = em.getReference(itempedidopizzaCollectionItempedidopizzaToAttach.getClass(), itempedidopizzaCollectionItempedidopizzaToAttach.getFacturaId());
                attachedItempedidopizzaCollection.add(itempedidopizzaCollectionItempedidopizzaToAttach);
            }
            pizzabase.setItempedidopizzaCollection(attachedItempedidopizzaCollection);
            em.persist(pizzabase);
            for (Itempedidopizza itempedidopizzaCollectionItempedidopizza : pizzabase.getItempedidopizzaCollection()) {
                Pizzabase oldPizzaIdOfItempedidopizzaCollectionItempedidopizza = itempedidopizzaCollectionItempedidopizza.getPizzaId();
                itempedidopizzaCollectionItempedidopizza.setPizzaId(pizzabase);
                itempedidopizzaCollectionItempedidopizza = em.merge(itempedidopizzaCollectionItempedidopizza);
                if (oldPizzaIdOfItempedidopizzaCollectionItempedidopizza != null) {
                    oldPizzaIdOfItempedidopizzaCollectionItempedidopizza.getItempedidopizzaCollection().remove(itempedidopizzaCollectionItempedidopizza);
                    oldPizzaIdOfItempedidopizzaCollectionItempedidopizza = em.merge(oldPizzaIdOfItempedidopizzaCollectionItempedidopizza);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pizzabase pizzabase) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pizzabase persistentPizzabase = em.find(Pizzabase.class, pizzabase.getPizzaId());
            Collection<Itempedidopizza> itempedidopizzaCollectionOld = persistentPizzabase.getItempedidopizzaCollection();
            Collection<Itempedidopizza> itempedidopizzaCollectionNew = pizzabase.getItempedidopizzaCollection();
            List<String> illegalOrphanMessages = null;
            for (Itempedidopizza itempedidopizzaCollectionOldItempedidopizza : itempedidopizzaCollectionOld) {
                if (!itempedidopizzaCollectionNew.contains(itempedidopizzaCollectionOldItempedidopizza)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itempedidopizza " + itempedidopizzaCollectionOldItempedidopizza + " since its pizzaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Itempedidopizza> attachedItempedidopizzaCollectionNew = new ArrayList<Itempedidopizza>();
            for (Itempedidopizza itempedidopizzaCollectionNewItempedidopizzaToAttach : itempedidopizzaCollectionNew) {
                itempedidopizzaCollectionNewItempedidopizzaToAttach = em.getReference(itempedidopizzaCollectionNewItempedidopizzaToAttach.getClass(), itempedidopizzaCollectionNewItempedidopizzaToAttach.getFacturaId());
                attachedItempedidopizzaCollectionNew.add(itempedidopizzaCollectionNewItempedidopizzaToAttach);
            }
            itempedidopizzaCollectionNew = attachedItempedidopizzaCollectionNew;
            pizzabase.setItempedidopizzaCollection(itempedidopizzaCollectionNew);
            pizzabase = em.merge(pizzabase);
            for (Itempedidopizza itempedidopizzaCollectionNewItempedidopizza : itempedidopizzaCollectionNew) {
                if (!itempedidopizzaCollectionOld.contains(itempedidopizzaCollectionNewItempedidopizza)) {
                    Pizzabase oldPizzaIdOfItempedidopizzaCollectionNewItempedidopizza = itempedidopizzaCollectionNewItempedidopizza.getPizzaId();
                    itempedidopizzaCollectionNewItempedidopizza.setPizzaId(pizzabase);
                    itempedidopizzaCollectionNewItempedidopizza = em.merge(itempedidopizzaCollectionNewItempedidopizza);
                    if (oldPizzaIdOfItempedidopizzaCollectionNewItempedidopizza != null && !oldPizzaIdOfItempedidopizzaCollectionNewItempedidopizza.equals(pizzabase)) {
                        oldPizzaIdOfItempedidopizzaCollectionNewItempedidopizza.getItempedidopizzaCollection().remove(itempedidopizzaCollectionNewItempedidopizza);
                        oldPizzaIdOfItempedidopizzaCollectionNewItempedidopizza = em.merge(oldPizzaIdOfItempedidopizzaCollectionNewItempedidopizza);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pizzabase.getPizzaId();
                if (findPizzabase(id) == null) {
                    throw new NonexistentEntityException("The pizzabase with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pizzabase pizzabase;
            try {
                pizzabase = em.getReference(Pizzabase.class, id);
                pizzabase.getPizzaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pizzabase with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itempedidopizza> itempedidopizzaCollectionOrphanCheck = pizzabase.getItempedidopizzaCollection();
            for (Itempedidopizza itempedidopizzaCollectionOrphanCheckItempedidopizza : itempedidopizzaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pizzabase (" + pizzabase + ") cannot be destroyed since the Itempedidopizza " + itempedidopizzaCollectionOrphanCheckItempedidopizza + " in its itempedidopizzaCollection field has a non-nullable pizzaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pizzabase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pizzabase> findPizzabaseEntities() {
        return findPizzabaseEntities(true, -1, -1);
    }

    public List<Pizzabase> findPizzabaseEntities(int maxResults, int firstResult) {
        return findPizzabaseEntities(false, maxResults, firstResult);
    }

    private List<Pizzabase> findPizzabaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pizzabase.class));
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

    public Pizzabase findPizzabase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pizzabase.class, id);
        } finally {
            em.close();
        }
    }
    
     public Pizzabase findPizzabaseName(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pizzabase.class, name);
        } finally {
            em.close();
        }
    }

    public int getPizzabaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pizzabase> rt = cq.from(Pizzabase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
