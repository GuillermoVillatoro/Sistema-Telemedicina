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
public class calendarModel implements Serializable {
   
    private int id;
    private Date fecha;
    private int horaIni;
    private int horaFin;
    private Date fecActual;
    private int horaActual;
    private int idEmpleado;
    private int idDoctor;
    private int estado;
     
    public calendarModel(){}

    public calendarModel(int id, Date fecha, int horaIni, int horaFin, Date fecActual, int horaActual, int idEmpleado, int idDoctor, int estado) {
        this.id = id;
        this.fecha = fecha;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.fecActual = fecActual;
        this.horaActual = horaActual;
        this.idEmpleado = idEmpleado;
        this.idDoctor = idDoctor;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(int horaIni) {
        this.horaIni = horaIni;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFecActual() {
        return fecActual;
    }

    public void setFecActual(Date fecActual) {
        this.fecActual = fecActual;
    }

    public int getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(int horaActual) {
        this.horaActual = horaActual;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
