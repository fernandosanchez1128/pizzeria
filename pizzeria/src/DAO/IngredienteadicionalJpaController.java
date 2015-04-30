/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import Logica.Ingredienteadicional;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Itempedidoingrediente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class IngredienteadicionalJpaController implements Serializable {

    public IngredienteadicionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ingredienteadicional ingredienteadicional) {
        if (ingredienteadicional.getItempedidoingredienteCollection() == null) {
            ingredienteadicional.setItempedidoingredienteCollection(new ArrayList<Itempedidoingrediente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itempedidoingrediente> attachedItempedidoingredienteCollection = new ArrayList<Itempedidoingrediente>();
            for (Itempedidoingrediente itempedidoingredienteCollectionItempedidoingredienteToAttach : ingredienteadicional.getItempedidoingredienteCollection()) {
                itempedidoingredienteCollectionItempedidoingredienteToAttach = em.getReference(itempedidoingredienteCollectionItempedidoingredienteToAttach.getClass(), itempedidoingredienteCollectionItempedidoingredienteToAttach.getFacturaId());
                attachedItempedidoingredienteCollection.add(itempedidoingredienteCollectionItempedidoingredienteToAttach);
            }
            ingredienteadicional.setItempedidoingredienteCollection(attachedItempedidoingredienteCollection);
            em.persist(ingredienteadicional);
            for (Itempedidoingrediente itempedidoingredienteCollectionItempedidoingrediente : ingredienteadicional.getItempedidoingredienteCollection()) {
                Ingredienteadicional oldIngredienteIdOfItempedidoingredienteCollectionItempedidoingrediente = itempedidoingredienteCollectionItempedidoingrediente.getIngredienteId();
                itempedidoingredienteCollectionItempedidoingrediente.setIngredienteId(ingredienteadicional);
                itempedidoingredienteCollectionItempedidoingrediente = em.merge(itempedidoingredienteCollectionItempedidoingrediente);
                if (oldIngredienteIdOfItempedidoingredienteCollectionItempedidoingrediente != null) {
                    oldIngredienteIdOfItempedidoingredienteCollectionItempedidoingrediente.getItempedidoingredienteCollection().remove(itempedidoingredienteCollectionItempedidoingrediente);
                    oldIngredienteIdOfItempedidoingredienteCollectionItempedidoingrediente = em.merge(oldIngredienteIdOfItempedidoingredienteCollectionItempedidoingrediente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingredienteadicional ingredienteadicional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredienteadicional persistentIngredienteadicional = em.find(Ingredienteadicional.class, ingredienteadicional.getIngredienteId());
            Collection<Itempedidoingrediente> itempedidoingredienteCollectionOld = persistentIngredienteadicional.getItempedidoingredienteCollection();
            Collection<Itempedidoingrediente> itempedidoingredienteCollectionNew = ingredienteadicional.getItempedidoingredienteCollection();
            List<String> illegalOrphanMessages = null;
            for (Itempedidoingrediente itempedidoingredienteCollectionOldItempedidoingrediente : itempedidoingredienteCollectionOld) {
                if (!itempedidoingredienteCollectionNew.contains(itempedidoingredienteCollectionOldItempedidoingrediente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itempedidoingrediente " + itempedidoingredienteCollectionOldItempedidoingrediente + " since its ingredienteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Itempedidoingrediente> attachedItempedidoingredienteCollectionNew = new ArrayList<Itempedidoingrediente>();
            for (Itempedidoingrediente itempedidoingredienteCollectionNewItempedidoingredienteToAttach : itempedidoingredienteCollectionNew) {
                itempedidoingredienteCollectionNewItempedidoingredienteToAttach = em.getReference(itempedidoingredienteCollectionNewItempedidoingredienteToAttach.getClass(), itempedidoingredienteCollectionNewItempedidoingredienteToAttach.getFacturaId());
                attachedItempedidoingredienteCollectionNew.add(itempedidoingredienteCollectionNewItempedidoingredienteToAttach);
            }
            itempedidoingredienteCollectionNew = attachedItempedidoingredienteCollectionNew;
            ingredienteadicional.setItempedidoingredienteCollection(itempedidoingredienteCollectionNew);
            ingredienteadicional = em.merge(ingredienteadicional);
            for (Itempedidoingrediente itempedidoingredienteCollectionNewItempedidoingrediente : itempedidoingredienteCollectionNew) {
                if (!itempedidoingredienteCollectionOld.contains(itempedidoingredienteCollectionNewItempedidoingrediente)) {
                    Ingredienteadicional oldIngredienteIdOfItempedidoingredienteCollectionNewItempedidoingrediente = itempedidoingredienteCollectionNewItempedidoingrediente.getIngredienteId();
                    itempedidoingredienteCollectionNewItempedidoingrediente.setIngredienteId(ingredienteadicional);
                    itempedidoingredienteCollectionNewItempedidoingrediente = em.merge(itempedidoingredienteCollectionNewItempedidoingrediente);
                    if (oldIngredienteIdOfItempedidoingredienteCollectionNewItempedidoingrediente != null && !oldIngredienteIdOfItempedidoingredienteCollectionNewItempedidoingrediente.equals(ingredienteadicional)) {
                        oldIngredienteIdOfItempedidoingredienteCollectionNewItempedidoingrediente.getItempedidoingredienteCollection().remove(itempedidoingredienteCollectionNewItempedidoingrediente);
                        oldIngredienteIdOfItempedidoingredienteCollectionNewItempedidoingrediente = em.merge(oldIngredienteIdOfItempedidoingredienteCollectionNewItempedidoingrediente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingredienteadicional.getIngredienteId();
                if (findIngredienteadicional(id) == null) {
                    throw new NonexistentEntityException("The ingredienteadicional with id " + id + " no longer exists.");
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
            Ingredienteadicional ingredienteadicional;
            try {
                ingredienteadicional = em.getReference(Ingredienteadicional.class, id);
                ingredienteadicional.getIngredienteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingredienteadicional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itempedidoingrediente> itempedidoingredienteCollectionOrphanCheck = ingredienteadicional.getItempedidoingredienteCollection();
            for (Itempedidoingrediente itempedidoingredienteCollectionOrphanCheckItempedidoingrediente : itempedidoingredienteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ingredienteadicional (" + ingredienteadicional + ") cannot be destroyed since the Itempedidoingrediente " + itempedidoingredienteCollectionOrphanCheckItempedidoingrediente + " in its itempedidoingredienteCollection field has a non-nullable ingredienteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ingredienteadicional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingredienteadicional> findIngredienteadicionalEntities() {
        return findIngredienteadicionalEntities(true, -1, -1);
    }

    public List<Ingredienteadicional> findIngredienteadicionalEntities(int maxResults, int firstResult) {
        return findIngredienteadicionalEntities(false, maxResults, firstResult);
    }

    private List<Ingredienteadicional> findIngredienteadicionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingredienteadicional.class));
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

    public Ingredienteadicional findIngredienteadicional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingredienteadicional.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredienteadicionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingredienteadicional> rt = cq.from(Ingredienteadicional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
