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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fersanq
 */
@Entity
@Table(catalog = "fersanq", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itempedidoingrediente.findAll", query = "SELECT i FROM Itempedidoingrediente i"),
    @NamedQuery(name = "Itempedidoingrediente.findByFacturaId", query = "SELECT i FROM Itempedidoingrediente i WHERE i.facturaId = :facturaId")})
public class Itempedidoingrediente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "factura_id", nullable = false)
    private Integer facturaId;
    @JoinColumn(name = "ingrediente_id", referencedColumnName = "ingrediente_id", nullable = false)
    @ManyToOne(optional = false)
    private Ingredienteadicional ingredienteId;
    @JoinColumn(name = "factura_id", referencedColumnName = "factura_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Factura factura;

    public Itempedidoingrediente() {
    }

    public Itempedidoingrediente(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Ingredienteadicional getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Ingredienteadicional ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaId != null ? facturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itempedidoingrediente)) {
            return false;
        }
        Itempedidoingrediente other = (Itempedidoingrediente) object;
        if ((this.facturaId == null && other.facturaId != null) || (this.facturaId != null && !this.facturaId.equals(other.facturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Itempedidoingrediente[ facturaId=" + facturaId + " ]";
    }
    
}
