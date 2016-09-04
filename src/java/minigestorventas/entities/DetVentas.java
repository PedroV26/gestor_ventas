/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minigestorventas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "det_ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetVentas.findAll", query = "SELECT d FROM DetVentas d"),
    @NamedQuery(name = "DetVentas.findById", query = "SELECT d FROM DetVentas d WHERE d.id = :id"),
    @NamedQuery(name = "DetVentas.findByCantidad", query = "SELECT d FROM DetVentas d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetVentas.findByTotalProducto", query = "SELECT d FROM DetVentas d WHERE d.totalProducto = :totalProducto")})
public class DetVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "total_producto")
    private Integer totalProducto;
    @JoinColumn(name = "id_producto", referencedColumnName = "cod_producto")
    @ManyToOne
    private Productos idProducto;
    @JoinColumn(name = "id_ventas", referencedColumnName = "id")
    @ManyToOne
    private Ventas idVentas;

    public DetVentas() {
    }

    public DetVentas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(Integer totalProducto) {
        this.totalProducto = totalProducto;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Ventas getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Ventas idVentas) {
        this.idVentas = idVentas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetVentas)) {
            return false;
        }
        DetVentas other = (DetVentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "minigestorventas.entities.DetVentas[ id=" + id + " ]";
    }
    
}
