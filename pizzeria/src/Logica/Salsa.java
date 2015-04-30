/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fersanq
 */
@Entity
@Table(catalog = "pizzeria", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salsa.findAll", query = "SELECT s FROM Salsa s"),
    @NamedQuery(name = "Salsa.findByIngredienteId", query = "SELECT s FROM Salsa s WHERE s.ingredienteId = :ingredienteId"),
    @NamedQuery(name = "Salsa.findByNombre", query = "SELECT s FROM Salsa s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Salsa.findByPrecioporcion", query = "SELECT s FROM Salsa s WHERE s.precioporcion = :precioporcion"),
    @NamedQuery(name = "Salsa.findByCantidad", query = "SELECT s FROM Salsa s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "Salsa.findByTipo", query = "SELECT s FROM Salsa s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Salsa.findBySalsaId", query = "SELECT s FROM Salsa s WHERE s.salsaId = :salsaId"),
    @NamedQuery(name = "Salsa.findByAzucares", query = "SELECT s FROM Salsa s WHERE s.azucares = :azucares"),
    @NamedQuery(name = "Salsa.findByCarbohidratos", query = "SELECT s FROM Salsa s WHERE s.carbohidratos = :carbohidratos"),
    @NamedQuery(name = "Salsa.findByGrasa", query = "SELECT s FROM Salsa s WHERE s.grasa = :grasa")})
public class Salsa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ingrediente_id", nullable = false)
    private int ingredienteId;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "salsa_id", nullable = false)
    private Integer salsaId;
    private Integer azucares;
    private Integer carbohidratos;
    private Integer grasa;

    public Salsa() {
    }

    public Salsa(Integer salsaId) {
        this.salsaId = salsaId;
    }

    public Salsa(String nombre, int precioporcion, int cantidad, String tipo) {
        this.nombre = nombre;
        this.precioporcion = precioporcion;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }    
    
    
    public Salsa(Integer salsaId, int ingredienteId, String nombre, int precioporcion, int cantidad, String tipo) {
        this.salsaId = salsaId;
        this.ingredienteId = ingredienteId;
        this.nombre = nombre;
        this.precioporcion = precioporcion;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
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

    public Integer getSalsaId() {
        return salsaId;
    }

    public void setSalsaId(Integer salsaId) {
        this.salsaId = salsaId;
    }

    public Integer getAzucares() {
        return azucares;
    }

    public void setAzucares(Integer azucares) {
        this.azucares = azucares;
    }

    public Integer getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Integer carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Integer getGrasa() {
        return grasa;
    }

    public void setGrasa(Integer grasa) {
        this.grasa = grasa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salsaId != null ? salsaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salsa)) {
            return false;
        }
        Salsa other = (Salsa) object;
        if ((this.salsaId == null && other.salsaId != null) || (this.salsaId != null && !this.salsaId.equals(other.salsaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Salsa[ salsaId=" + salsaId + " ]";
    }
    
}
