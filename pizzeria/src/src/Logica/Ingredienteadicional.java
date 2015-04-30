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
@Table(catalog = "pizzeria", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredienteadicional.findAll", query = "SELECT i FROM Ingredienteadicional i"),
    @NamedQuery(name = "Ingredienteadicional.findByIngredienteId", query = "SELECT i FROM Ingredienteadicional i WHERE i.ingredienteId = :ingredienteId"),
    @NamedQuery(name = "Ingredienteadicional.findByNombre", query = "SELECT i FROM Ingredienteadicional i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Ingredienteadicional.findByPrecioporcion", query = "SELECT i FROM Ingredienteadicional i WHERE i.precioporcion = :precioporcion"),
    @NamedQuery(name = "Ingredienteadicional.findByCantidad", query = "SELECT i FROM Ingredienteadicional i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Ingredienteadicional.findByTipo", query = "SELECT i FROM Ingredienteadicional i WHERE i.tipo = :tipo")})
public class Ingredienteadicional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingrediente_id", nullable = false)
    private Integer ingredienteId;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false)
    private int precioporcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private int cantidad;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String tipo;
    @Lob
    private byte[] foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredienteId")
    private Collection<Itempedidoingrediente> itempedidoingredienteCollection;

    public Ingredienteadicional() {
    }

    public Ingredienteadicional(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public Ingredienteadicional(Integer ingredienteId, String nombre, int precioporcion, int cantidad, String tipo) {
        this.ingredienteId = ingredienteId;
        this.nombre = nombre;
        this.precioporcion = precioporcion;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioporcion() {
        return precioporcion;
    }

    public void setPrecioporcion(int precioporcion) {
        this.precioporcion = precioporcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Itempedidoingrediente> getItempedidoingredienteCollection() {
        return itempedidoingredienteCollection;
    }

    public void setItempedidoingredienteCollection(Collection<Itempedidoingrediente> itempedidoingredienteCollection) {
        this.itempedidoingredienteCollection = itempedidoingredienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredienteId != null ? ingredienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredienteadicional)) {
            return false;
        }
        Ingredienteadicional other = (Ingredienteadicional) object;
        if ((this.ingredienteId == null && other.ingredienteId != null) || (this.ingredienteId != null && !this.ingredienteId.equals(other.ingredienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Ingredienteadicional[ ingredienteId=" + ingredienteId + " ]";
    }
    
}
