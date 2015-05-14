/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import Logica.Factura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Itempedidopizza;
import Logica.Itempedidoingrediente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itempedidopizza itempedidopizza = factura.getItempedidopizza();
            if (itempedidopizza != null) {
                itempedidopizza = em.getReference(itempedidopizza.getClass(), itempedidopizza.getFacturaId());
                factura.setItempedidopizza(itempedidopizza);
            }
            Itempedidoingrediente itempedidoingrediente = factura.getItempedidoingrediente();
            if (itempedidoingrediente != null) {
                itempedidoingrediente = em.getReference(itempedidoingrediente.getClass(), itempedidoingrediente.getFacturaId());
                factura.setItempedidoingrediente(itempedidoingrediente);
            }
            em.persist(factura);
            if (itempedidopizza != null) {
                Factura oldFacturaOfItempedidopizza = itempedidopizza.getFactura();
                if (oldFacturaOfItempedidopizza != null) {
                    oldFacturaOfItempedidopizza.setItempedidopizza(null);
                    oldFacturaOfItempedidopizza = em.merge(oldFacturaOfItempedidopizza);
                }
                itempedidopizza.setFactura(factura);
                itempedidopizza = em.merge(itempedidopizza);
            }
            if (itempedidoingrediente != null) {
                Factura oldFacturaOfItempedidoingrediente = itempedidoingrediente.getFactura();
                if (oldFacturaOfItempedidoingrediente != null) {
                    oldFacturaOfItempedidoingrediente.setItempedidoingrediente(null);
                    oldFacturaOfItempedidoingrediente = em.merge(oldFacturaOfItempedidoingrediente);
                }
                itempedidoingrediente.setFactura(factura);
                itempedidoingrediente = em.merge(itempedidoingrediente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getFacturaId());
            Itempedidopizza itempedidopizzaOld = persistentFactura.getItempedidopizza();
            Itempedidopizza itempedidopizzaNew = factura.getItempedidopizza();
            Itempedidoingrediente itempedidoingredienteOld = persistentFactura.getItempedidoingrediente();
            Itempedidoingrediente itempedidoingredienteNew = factura.getItempedidoingrediente();
            List<String> illegalOrphanMessages = null;
            if (itempedidopizzaOld != null && !itempedidopizzaOld.equals(itempedidopizzaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Itempedidopizza " + itempedidopizzaOld + " since its factura field is not nullable.");
            }
            if (itempedidoingredienteOld != null && !itempedidoingredienteOld.equals(itempedidoingredienteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Itempedidoingrediente " + itempedidoingredienteOld + " since its factura field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (itempedidopizzaNew != null) {
                itempedidopizzaNew = em.getReference(itempedidopizzaNew.getClass(), itempedidopizzaNew.getFacturaId());
                factura.setItempedidopizza(itempedidopizzaNew);
            }
            if (itempedidoingredienteNew != null) {
                itempedidoingredienteNew = em.getReference(itempedidoingredienteNew.getClass(), itempedidoingredienteNew.getFacturaId());
                factura.setItempedidoingrediente(itempedidoingredienteNew);
            }
            factura = em.merge(factura);
            if (itempedidopizzaNew != null && !itempedidopizzaNew.equals(itempedidopizzaOld)) {
                Factura oldFacturaOfItempedidopizza = itempedidopizzaNew.getFactura();
                if (oldFacturaOfItempedidopizza != null) {
                    oldFacturaOfItempedidopizza.setItempedidopizza(null);
                    oldFacturaOfItempedidopizza = em.merge(oldFacturaOfItempedidopizza);
                }
                itempedidopizzaNew.setFactura(factura);
                itempedidopizzaNew = em.merge(itempedidopizzaNew);
            }
            if (itempedidoingredienteNew != null && !itempedidoingredienteNew.equals(itempedidoingredienteOld)) {
                Factura oldFacturaOfItempedidoingrediente = itempedidoingredienteNew.getFactura();
                if (oldFacturaOfItempedidoingrediente != null) {
                    oldFacturaOfItempedidoingrediente.setItempedidoingrediente(null);
                    oldFacturaOfItempedidoingrediente = em.merge(oldFacturaOfItempedidoingrediente);
                }
                itempedidoingredienteNew.setFactura(factura);
                itempedidoingredienteNew = em.merge(itempedidoingredienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getFacturaId();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getFacturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Itempedidopizza itempedidopizzaOrphanCheck = factura.getItempedidopizza();
            if (itempedidopizzaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Itempedidopizza " + itempedidopizzaOrphanCheck + " in its itempedidopizza field has a non-nullable factura field.");
            }
            Itempedidoingrediente itempedidoingredienteOrphanCheck = factura.getItempedidoingrediente();
            if (itempedidoingredienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Itempedidoingrediente " + itempedidoingredienteOrphanCheck + " in its itempedidoingrediente field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     
      
    public int  getNext () 
    {
        EntityManager em = getEntityManager();
        int proximo = 0;
        try 
        {
            Query consulta = em.createNativeQuery("select last_value from sec_factura");
            proximo = Integer.parseInt(consulta.getSingleResult().toString());
        } catch (Exception e) {
            System.err.println("excepcion ocurrida");
        }
        finally {
        em.close();
                } 
        return proximo+1;
       
    }
    
}
