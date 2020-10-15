/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marvin Moran
 */
@Entity
@Table(name = "doctores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctores.findAll", query = "SELECT d FROM Doctores d"),
    @NamedQuery(name = "Doctores.findById", query = "SELECT d FROM Doctores d WHERE d.id = :id"),
    @NamedQuery(name = "Doctores.findByNombres", query = "SELECT d FROM Doctores d WHERE d.nombres = :nombres"),
    @NamedQuery(name = "Doctores.findByApellidos", query = "SELECT d FROM Doctores d WHERE d.apellidos = :apellidos"),
    @NamedQuery(name = "Doctores.findByDireccion", query = "SELECT d FROM Doctores d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Doctores.findByTelefono", query = "SELECT d FROM Doctores d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "Doctores.findByEspecialidad", query = "SELECT d FROM Doctores d WHERE d.especialidad = :especialidad")})
public class Doctores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 60)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 60)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 8)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "Especialidad")
    private Integer especialidad;

    public Doctores() {
    }

    public Doctores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Integer especialidad) {
        this.especialidad = especialidad;
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
        if (!(object instanceof Doctores)) {
            return false;
        }
        Doctores other = (Doctores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Doctores[ id=" + id + " ]";
    }
    
}
