/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marvin Moran
 */
@Entity
@Table(name = "reservaciones_consultas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservacionesConsultas.findAll", query = "SELECT r FROM ReservacionesConsultas r"),
    @NamedQuery(name = "ReservacionesConsultas.findById", query = "SELECT r FROM ReservacionesConsultas r WHERE r.id = :id"),
    @NamedQuery(name = "ReservacionesConsultas.findByIdEmpleado", query = "SELECT r FROM ReservacionesConsultas r WHERE r.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "ReservacionesConsultas.findByIdDoctor", query = "SELECT r FROM ReservacionesConsultas r WHERE r.idDoctor = :idDoctor"),
    @NamedQuery(name = "ReservacionesConsultas.findByFecha", query = "SELECT r FROM ReservacionesConsultas r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReservacionesConsultas.findByHora", query = "SELECT r FROM ReservacionesConsultas r WHERE r.hora = :hora"),
    @NamedQuery(name = "ReservacionesConsultas.findByEstado", query = "SELECT r FROM ReservacionesConsultas r WHERE r.estado = :estado")})
public class ReservacionesConsultas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "idEmpleado")
    private Integer idEmpleado;
    @Column(name = "idDoctor")
    private Integer idDoctor;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "estado")
    private Integer estado;

    public ReservacionesConsultas() {
    }

    public ReservacionesConsultas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
        if (!(object instanceof ReservacionesConsultas)) {
            return false;
        }
        ReservacionesConsultas other = (ReservacionesConsultas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ReservacionesConsultas[ id=" + id + " ]";
    }
    
}
