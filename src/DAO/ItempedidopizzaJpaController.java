/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Pizzabase;
import Logica.Factura;
import Logica.Itempedidopizza;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ItempedidopizzaJpaController implements Serializable {

    public ItempedidopizzaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itempedidopizza itempedidopizza) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Factura facturaOrphanCheck = itempedidopizza.getFactura();
        if (facturaOrphanCheck != null) {
            Itempedidopizza oldItempedidopizzaOfFactura = facturaOrphanCheck.getItempedidopizza();
            if (oldItempedidopizzaOfFactura != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Factura " + facturaOrphanCheck + " already has an item of type Itempedidopizza whose factura column cannot be null. Please make another selection for the factura field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pizzabase pizzaId = itempedidopizza.getPizzaId();
            if (pizzaId != null) {
                pizzaId = em.getReference(pizzaId.getClass(), pizzaId.getPizzaId());
                itempedidopizza.setPizzaId(pizzaId);
            }
            Factura factura = itempedidopizza.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getFacturaId());
                itempedidopizza.setFactura(factura);
            }
            em.persist(itempedidopizza);
            if (pizzaId != null) {
                pizzaId.getItempedidopizzaCollection().add(itempedidopizza);
                pizzaId = em.merge(pizzaId);
            }
            if (factura != null) {
                factura.setItempedidopizza(itempedidopizza);
                factura = em.merge(factura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItempedidopizza(itempedidopizza.getFacturaId()) != null) {
                throw new PreexistingEntityException("Itempedidopizza " + itempedidopizza + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itempedidopizza itempedidopizza) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itempedidopizza persistentItempedidopizza = em.find(Itempedidopizza.class, itempedidopizza.getFacturaId());
            Pizzabase pizzaIdOld = persistentItempedidopizza.getPizzaId();
            Pizzabase pizzaIdNew = itempedidopizza.getPizzaId();
            Factura facturaOld = persistentItempedidopizza.getFactura();
            Factura facturaNew = itempedidopizza.getFactura();
            List<String> illegalOrphanMessages = null;
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                Itempedidopizza oldItempedidopizzaOfFactura = facturaNew.getItempedidopizza();
                if (oldItempedidopizzaOfFactura != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Factura " + facturaNew + " already has an item of type Itempedidopizza whose factura column cannot be null. Please make another selection for the factura field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pizzaIdNew != null) {
                pizzaIdNew = em.getReference(pizzaIdNew.getClass(), pizzaIdNew.getPizzaId());
                itempedidopizza.setPizzaId(pizzaIdNew);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getFacturaId());
                itempedidopizza.setFactura(facturaNew);
            }
            itempedidopizza = em.merge(itempedidopizza);
            if (pizzaIdOld != null && !pizzaIdOld.equals(pizzaIdNew)) {
                pizzaIdOld.getItempedidopizzaCollection().remove(itempedidopizza);
                pizzaIdOld = em.merge(pizzaIdOld);
            }
            if (pizzaIdNew != null && !pizzaIdNew.equals(pizzaIdOld)) {
                pizzaIdNew.getItempedidopizzaCollection().add(itempedidopizza);
                pizzaIdNew = em.merge(pizzaIdNew);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.setItempedidopizza(null);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.setItempedidopizza(itempedidopizza);
                facturaNew = em.merge(facturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itempedidopizza.getFacturaId();
                if (findItempedidopizza(id) == null) {
                    throw new NonexistentEntityException("The itempedidopizza with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itempedidopizza itempedidopizza;
            try {
                itempedidopizza = em.getReference(Itempedidopizza.class, id);
                itempedidopizza.getFacturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itempedidopizza with id " + id + " no longer exists.", enfe);
            }
            Pizzabase pizzaId = itempedidopizza.getPizzaId();
            if (pizzaId != null) {
                pizzaId.getItempedidopizzaCollection().remove(itempedidopizza);
                pizzaId = em.merge(pizzaId);
            }
            Factura factura = itempedidopizza.getFactura();
            if (factura != null) {
                factura.setItempedidopizza(null);
                factura = em.merge(factura);
            }
            em.remove(itempedidopizza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itempedidopizza> findItempedidopizzaEntities() {
        return findItempedidopizzaEntities(true, -1, -1);
    }

    public List<Itempedidopizza> findItempedidopizzaEntities(int maxResults, int firstResult) {
        return findItempedidopizzaEntities(false, maxResults, firstResult);
    }

    private List<Itempedidopizza> findItempedidopizzaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itempedidopizza.class));
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

    public Itempedidopizza findItempedidopizza(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itempedidopizza.class, id);
        } finally {
            em.close();
        }
    }

    public int getItempedidopizzaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itempedidopizza> rt = cq.from(Itempedidopizza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
