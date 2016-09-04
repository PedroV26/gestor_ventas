/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minigestorventas.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "operadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operadores.findAll", query = "SELECT o FROM Operadores o"),
    @NamedQuery(name = "Operadores.findByNombre", query = "SELECT o FROM Operadores o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Operadores.findByApellido", query = "SELECT o FROM Operadores o WHERE o.apellido = :apellido"),
    @NamedQuery(name = "Operadores.findByCategoria", query = "SELECT o FROM Operadores o WHERE o.categoria = :categoria"),
    @NamedQuery(name = "Operadores.findByPassword", query = "SELECT o FROM Operadores o WHERE o.password = :password"),
    @NamedQuery(name = "Operadores.findByCodOperador", query = "SELECT o FROM Operadores o WHERE o.codOperador = :codOperador")})
public class Operadores implements Serializable {

    @OneToMany(mappedBy = "idOperador")
    private Collection<Ventas> ventasCollection;

    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 10)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 10)
    @Column(name = "categoria")
    private String categoria;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_operador")
    private Integer codOperador;

    public Operadores() {
    }

    public Operadores(Integer codOperador) {
        this.codOperador = codOperador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCodOperador() {
        return codOperador;
    }

    public void setCodOperador(Integer codOperador) {
        this.codOperador = codOperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codOperador != null ? codOperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operadores)) {
            return false;
        }
        Operadores other = (Operadores) object;
        if ((this.codOperador == null && other.codOperador != null) || (this.codOperador != null && !this.codOperador.equals(other.codOperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "minigestorventas.entities.Operadores[ codOperador=" + codOperador + " ]";
    }

    @XmlTransient
    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }
    
}
