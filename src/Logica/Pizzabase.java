/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fersanq
 */
@Entity
@Table(catalog = "fersanq", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pizzabase.findAll", query = "SELECT p FROM Pizzabase p"),
    @NamedQuery(name = "Pizzabase.findByPizzaId", query = "SELECT p FROM Pizzabase p WHERE p.pizzaId = :pizzaId"),
    @NamedQuery(name = "Pizzabase.findByNombre", query = "SELECT p FROM Pizzabase p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pizzabase.findByTamano", query = "SELECT p FROM Pizzabase p WHERE p.tamano = :tamano"),
    @NamedQuery(name = "Pizzabase.findByPresentacion", query = "SELECT p FROM Pizzabase p WHERE p.presentacion = :presentacion"),
    @NamedQuery(name = "Pizzabase.findByPrecio", query = "SELECT p FROM Pizzabase p WHERE p.precio = :precio")})
public class Pizzabase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pizza_id", nullable = false)
    private Integer pizzaId;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String tamano;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String presentacion;
    @Basic(optional = false)
    @Column(nullable = false)
    private int precio;
    @Lob
    private byte[] foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaId")
    private Collection<Itempedidopizza> itempedidopizzaCollection;

    public Pizzabase() {
    }

    public Pizzabase(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }
    
    public Pizzabase(Integer pizzaId, String nombre, String tamano, String presentacion, int precio
    ) {
        this.pizzaId = pizzaId;
        this.nombre = nombre;
        this.tamano = tamano;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public Pizzabase(String nombre, String tamano, String presentacion, int precio) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Itempedidopizza> getItempedidopizzaCollection() {
        return itempedidopizzaCollection;
    }

    public void setItempedidopizzaCollection(Collection<Itempedidopizza> itempedidopizzaCollection) {
        this.itempedidopizzaCollection = itempedidopizzaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pizzaId != null ? pizzaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pizzabase)) {
            return false;
        }
        Pizzabase other = (Pizzabase) object;
        if ((this.pizzaId == null && other.pizzaId != null) || (this.pizzaId != null && !this.pizzaId.equals(other.pizzaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Pizzabase[ pizzaId=" + pizzaId + " ]";
    }
    
}
