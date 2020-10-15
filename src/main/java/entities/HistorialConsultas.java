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
@Table(name = "historial_consultas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialConsultas.findAll", query = "SELECT h FROM HistorialConsultas h"),
    @NamedQuery(name = "HistorialConsultas.findById", query = "SELECT h FROM HistorialConsultas h WHERE h.id = :id"),
    @NamedQuery(name = "HistorialConsultas.findByIdEmpleado", query = "SELECT h FROM HistorialConsultas h WHERE h.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "HistorialConsultas.findByIdDoctor", query = "SELECT h FROM HistorialConsultas h WHERE h.idDoctor = :idDoctor"),
    @NamedQuery(name = "HistorialConsultas.findByIdConsulta", query = "SELECT h FROM HistorialConsultas h WHERE h.idConsulta = :idConsulta"),
    @NamedQuery(name = "HistorialConsultas.findByDiagnostico", query = "SELECT h FROM HistorialConsultas h WHERE h.diagnostico = :diagnostico"),
    @NamedQuery(name = "HistorialConsultas.findByExamenes", query = "SELECT h FROM HistorialConsultas h WHERE h.examenes = :examenes"),
    @NamedQuery(name = "HistorialConsultas.findByReceta", query = "SELECT h FROM HistorialConsultas h WHERE h.receta = :receta")})
public class HistorialConsultas implements Serializable {

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
    @Column(name = "idConsulta")
    private Integer idConsulta;
    @Size(max = 500)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Size(max = 500)
    @Column(name = "examenes")
    private String examenes;
    @Size(max = 500)
    @Column(name = "receta")
    private String receta;

    public HistorialConsultas() {
    }

    public HistorialConsultas(Integer id) {
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

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getExamenes() {
        return examenes;
    }

    public void setExamenes(String examenes) {
        this.examenes = examenes;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
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
        if (!(object instanceof HistorialConsultas)) {
            return false;
        }
        HistorialConsultas other = (HistorialConsultas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialConsultas[ id=" + id + " ]";
    }
    
}
