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
import Logica.Ingredienteadicional;
import Logica.Factura;
import Logica.Itempedidoingrediente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ItempedidoingredienteJpaController implements Serializable {

    public ItempedidoingredienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itempedidoingrediente itempedidoingrediente) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Factura facturaOrphanCheck = itempedidoingrediente.getFactura();
        if (facturaOrphanCheck != null) {
            Itempedidoingrediente oldItempedidoingredienteOfFactura = facturaOrphanCheck.getItempedidoingrediente();
            if (oldItempedidoingredienteOfFactura != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Factura " + facturaOrphanCheck + " already has an item of type Itempedidoingrediente whose factura column cannot be null. Please make another selection for the factura field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredienteadicional ingredienteId = itempedidoingrediente.getIngredienteId();
            if (ingredienteId != null) {
                ingredienteId = em.getReference(ingredienteId.getClass(), ingredienteId.getIngredienteId());
                itempedidoingrediente.setIngredienteId(ingredienteId);
            }
            Factura factura = itempedidoingrediente.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getFacturaId());
                itempedidoingrediente.setFactura(factura);
            }
            em.persist(itempedidoingrediente);
            if (ingredienteId != null) {
                ingredienteId.getItempedidoingredienteCollection().add(itempedidoingrediente);
                ingredienteId = em.merge(ingredienteId);
            }
            if (factura != null) {
                factura.setItempedidoingrediente(itempedidoingrediente);
                factura = em.merge(factura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItempedidoingrediente(itempedidoingrediente.getFacturaId()) != null) {
                throw new PreexistingEntityException("Itempedidoingrediente " + itempedidoingrediente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itempedidoingrediente itempedidoingrediente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itempedidoingrediente persistentItempedidoingrediente = em.find(Itempedidoingrediente.class, itempedidoingrediente.getFacturaId());
            Ingredienteadicional ingredienteIdOld = persistentItempedidoingrediente.getIngredienteId();
            Ingredienteadicional ingredienteIdNew = itempedidoingrediente.getIngredienteId();
            Factura facturaOld = persistentItempedidoingrediente.getFactura();
            Factura facturaNew = itempedidoingrediente.getFactura();
            List<String> illegalOrphanMessages = null;
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                Itempedidoingrediente oldItempedidoingredienteOfFactura = facturaNew.getItempedidoingrediente();
                if (oldItempedidoingredienteOfFactura != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Factura " + facturaNew + " already has an item of type Itempedidoingrediente whose factura column cannot be null. Please make another selection for the factura field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ingredienteIdNew != null) {
                ingredienteIdNew = em.getReference(ingredienteIdNew.getClass(), ingredienteIdNew.getIngredienteId());
                itempedidoingrediente.setIngredienteId(ingredienteIdNew);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getFacturaId());
                itempedidoingrediente.setFactura(facturaNew);
            }
            itempedidoingrediente = em.merge(itempedidoingrediente);
            if (ingredienteIdOld != null && !ingredienteIdOld.equals(ingredienteIdNew)) {
                ingredienteIdOld.getItempedidoingredienteCollection().remove(itempedidoingrediente);
                ingredienteIdOld = em.merge(ingredienteIdOld);
            }
            if (ingredienteIdNew != null && !ingredienteIdNew.equals(ingredienteIdOld)) {
                ingredienteIdNew.getItempedidoingredienteCollection().add(itempedidoingrediente);
                ingredienteIdNew = em.merge(ingredienteIdNew);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.setItempedidoingrediente(null);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.setItempedidoingrediente(itempedidoingrediente);
                facturaNew = em.merge(facturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itempedidoingrediente.getFacturaId();
                if (findItempedidoingrediente(id) == null) {
                    throw new NonexistentEntityException("The itempedidoingrediente with id " + id + " no longer exists.");
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
            Itempedidoingrediente itempedidoingrediente;
            try {
                itempedidoingrediente = em.getReference(Itempedidoingrediente.class, id);
                itempedidoingrediente.getFacturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itempedidoingrediente with id " + id + " no longer exists.", enfe);
            }
            Ingredienteadicional ingredienteId = itempedidoingrediente.getIngredienteId();
            if (ingredienteId != null) {
                ingredienteId.getItempedidoingredienteCollection().remove(itempedidoingrediente);
                ingredienteId = em.merge(ingredienteId);
            }
            Factura factura = itempedidoingrediente.getFactura();
            if (factura != null) {
                factura.setItempedidoingrediente(null);
                factura = em.merge(factura);
            }
            em.remove(itempedidoingrediente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itempedidoingrediente> findItempedidoingredienteEntities() {
        return findItempedidoingredienteEntities(true, -1, -1);
    }

    public List<Itempedidoingrediente> findItempedidoingredienteEntities(int maxResults, int firstResult) {
        return findItempedidoingredienteEntities(false, maxResults, firstResult);
    }

    private List<Itempedidoingrediente> findItempedidoingredienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itempedidoingrediente.class));
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

    public Itempedidoingrediente findItempedidoingrediente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itempedidoingrediente.class, id);
        } finally {
            em.close();
        }
    }

    public int getItempedidoingredienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itempedidoingrediente> rt = cq.from(Itempedidoingrediente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
