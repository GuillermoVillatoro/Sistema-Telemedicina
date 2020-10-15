package manage.pacientes;

import clases.UrlBase;
import entities.HistorialConsultas;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import sessions.HistorialConsultasFacadeLocal;
import sessions.UsuariosFacadeLocal;
import wrapper.EmpleadoModel;
import wrapper.HistorialConsultasModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marvin Moran
 *
 */
@Named
@ViewScoped
public class indexPaciente implements Serializable {

    private int idUsuario;
    @EJB
    private UsuariosFacadeLocal usuarios;
    @EJB
    private HistorialConsultasFacadeLocal historial;
    private List<EmpleadoModel> ListEmpleado;
    private List<HistorialConsultasModel> ListHistorialConsultas;
    private HistorialConsultasModel selectedHistorial;
    private int idEmpleado;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private Date fecNac;
    private String nomDepto;
    private BigDecimal sueldo;
    private String email;
    private int sexo;
    private String sexoStr;
    private String foto;
    private String urlFoto;
    private String diferenciaStr;
    private String claseStatus;
   

    public indexPaciente() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String idUsuarioStr = session.getAttribute("idUsuario").toString();
        idUsuario = Integer.parseInt(idUsuarioStr);
 
    }

    @PostConstruct
    public void init() {
        boolean muestraDetalle;
        boolean habilitaVideo;
        
        List<Object[]> buscaDatosPaciente = usuarios.buscaDatosPaciente(idUsuario);
        for (Object[] val : buscaDatosPaciente) {
            idEmpleado = (int) val[0];
            nombres = (String) val[1];
            apellidos = (String) val[2];
            direccion = (String) val[3];
            telefono = (String) val[4];
            fecNac = (Date) val[5];
            nomDepto = (String) val[6];
            sueldo = (BigDecimal) val[7];
            email = (String) val[8];
            sexo = (int) val[9];
            if (sexo == 1) {
                sexoStr = "Masculino";
            } else {
                sexoStr = "Femenimo";
            }
            FacesContext ctx = FacesContext.getCurrentInstance();
            String base = UrlBase.getURLBase(ctx);
            foto = (String) val[10];
            urlFoto = base + "/TeleSisQCI/resources/images/" + foto.trim();
            ListHistorialConsultas = new ArrayList<>();
            List<Object[]> historialConsultas = historial.historialConsultas(idEmpleado);
            for (Object[] res : historialConsultas) {
                long diferencia = (long) res[11];
                if (diferencia == 0) {
                    claseStatus = "btnStatusColorA";
                    diferenciaStr = "Consulta HOY";
                }
                if (diferencia < 0) {
                    claseStatus = "btnStatusColorR";
                    diferenciaStr = "Consulta realizada";
                }
                if (diferencia > 0) {
                    claseStatus = "btnStatusColorV";
                    diferenciaStr = "Consulta Pendiente";
                }
                String diagnostico = (String) res[8];
                if (diagnostico.length()>1) {
                    muestraDetalle = true;
                   
                }else{
                    muestraDetalle=false;
                }
                
                
                if(diferencia ==0){
                    habilitaVideo=true;
                }else{
                    habilitaVideo=false;
                }
                
                ListHistorialConsultas.add(new HistorialConsultasModel((int) res[0], (int) res[1], (String) res[2], (int) res[3], (String) res[4], (int) res[5],
                        (Date) res[6], (int) res[7], (String) res[8], (String) res[9], (String) res[10], diferenciaStr, (String) res[12],muestraDetalle,habilitaVideo, claseStatus));
            }

        }

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuariosFacadeLocal getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(UsuariosFacadeLocal usuarios) {
        this.usuarios = usuarios;
    }

    public List<EmpleadoModel> getListEmpleado() {
        return ListEmpleado;
    }

    public void setListEmpleado(List<EmpleadoModel> ListEmpleado) {
        this.ListEmpleado = ListEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getSexoStr() {
        return sexoStr;
    }

    public void setSexoStr(String sexoStr) {
        this.sexoStr = sexoStr;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public List<HistorialConsultasModel> getListHistorialConsultas() {
        return ListHistorialConsultas;
    }

    public void setListHistorialConsultas(List<HistorialConsultasModel> ListHistorialConsultas) {
        this.ListHistorialConsultas = ListHistorialConsultas;
    }

    public HistorialConsultasModel getSelectedHistorial() {
        return selectedHistorial;
    }

    public void setSelectedHistorial(HistorialConsultasModel selectedHistorial) {
        this.selectedHistorial = selectedHistorial;
    }

    public String getDiferenciaStr() {
        return diferenciaStr;
    }

    public void setDiferenciaStr(String diferenciaStr) {
        this.diferenciaStr = diferenciaStr;
    }

    public String getClaseStatus() {
        return claseStatus;
    }

    public void setClaseStatus(String claseStatus) {
        this.claseStatus = claseStatus;
    }

}
