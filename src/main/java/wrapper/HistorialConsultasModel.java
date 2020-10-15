/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Marvin Moran
 */
public class HistorialConsultasModel implements Serializable {
    private int id;
    private int idEmpleado;
    private String empleado;
    private int idDoctor;
    private String doctor;
    private int idConsulta;
    private Date fecha;
    private int hora;
    private String diagnostico;
    private String examenes;
    private String receta;
    private String diferencia;
    private String horario;
    private boolean habilitaDetalle;
    private boolean habilitaVideo;
    private String claseStatus;
    
    public HistorialConsultasModel(){}

    public HistorialConsultasModel(int id, int idEmpleado, String empleado, int idDoctor, String doctor, int idConsulta, Date fecha, int hora, String diagnostico, String examenes, String receta,String diferencia,String horario,boolean habilitaDetalle,boolean habilitaVideo, String claseStatus ) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.empleado = empleado;
        this.idDoctor = idDoctor;
        this.doctor = doctor;
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.hora = hora;
        this.diagnostico = diagnostico;
        this.examenes = examenes;
        this.receta = receta;
        this.diferencia=diferencia;
        this.horario=horario;
        this.habilitaDetalle= habilitaDetalle;
        this.habilitaVideo = habilitaVideo;
        this.claseStatus = claseStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
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

    public String getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isHabilitaDetalle() {
        return habilitaDetalle;
    }

    public void setHabilitaDetalle(boolean habilitaDetalle) {
        this.habilitaDetalle = habilitaDetalle;
    }

    public boolean isHabilitaVideo() {
        return habilitaVideo;
    }

    public void setHabilitaVideo(boolean habilitaVideo) {
        this.habilitaVideo = habilitaVideo;
    }

    public String getClaseStatus() {
        return claseStatus;
    }

    public void setClaseStatus(String claseStatus) {
        this.claseStatus = claseStatus;
    }

   
    
}
