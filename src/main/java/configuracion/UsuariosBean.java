package configuracion;

/**
 * Usuarios Bean se encarga de manejar el ingreso al Sistema y crear el menu
 * dinamico, tambien se encarga de abrir y cerrar la sesion
 *
 * @author Marvin Moran
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import clases.Decryptor;
import clases.md5;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import sessions.UsuariosFacadeLocal;
import wrapper.UsuarioModel;

@SessionScoped
@Named(value = "UsuariosBean")
public class UsuariosBean implements Serializable {

    private String usuario;
    private String contrasena;
    private boolean logeado = false;
    private List<Object[]> SisAccesos;
    private String page;
    private static String titulo;
    private MenuModel modelSol;
    private boolean muestraSol;
    private MenuModel modelSecurity;
    private boolean muestraSecurity;
    private MenuModel modelProp;
    private boolean muestraProp;
    private MenuModel modelEstadisticas;
    private boolean muestraEstadisticas;
    private MenuModel modelContrasenas;
    private boolean muestraContrasenas;
    private String tituloMenu;
    private String grupo;
    private int idGrupo;
    private int idUsuario;
    private boolean estado;

    private int Role;
    private int habilitado;

    @EJB
    private UsuariosFacadeLocal sisusuarios;

    public UsuariosBean() {

    }

    /**
     * Este metodo se utiliza para dar o no acceso al usuario al sistema
     */
    public void buscaLogin() {
        //RequestContext context = RequestContext.getCurrentInstance();
        habilitado = 0;
        FacesMessage msg = null;
        //logeado = consumidor();
        List<Object[]> SisUsuariosLogin = sisusuarios.SisUsuariosLogin(usuario, contrasena);
        if (SisUsuariosLogin.size() > 0) {
            logeado = true;
            for (Object[] val : SisUsuariosLogin) {
                Role = (int) val[2];
                habilitado = (int) val[5];
                idUsuario = (int) val[6];
            }

        }

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Al Sistema \n" + "\n" + usuario, "");
        if (logeado) {
            logeado = true;

        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario ó Contraseña Incorrectas", " ");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);

        PrimeFaces.current().ajax().addCallbackParam("estaLogeado", logeado);
        if (habilitado == 1 && logeado) {
            if (logeado) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idUsuario", idUsuario);

                Compilacion config = new Compilacion();
                config.getDirecCompile();
                GeneraMenus();
                if (Role == 1) {

                    PrimeFaces.current().ajax().addCallbackParam("view", config.getDirecCompile() + "/TeleSisQCI/umg/indexPaciente.xhtml");
                }
                if (Role == 2) {

                    PrimeFaces.current().ajax().addCallbackParam("view", config.getDirecCompile() + "/TeleSisQCI/umg/indexDoctor.xhtml");
                }
                setEstaLogueado(true);
                this.titulo = " ";
            }
        }
        if (logeado && habilitado == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario deshabilitado", " ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    /**
     * Metodo que se encarga de salir de la sesion y eliminarla
     *
     * @throws IOException
     */
    public void logout() throws IOException {
        logeado = false;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/umg/");

    }

    public void GeneraMenus() {
        CreaMenu();
    }

    /**
     * Metodo donse se van generando los modulos para el menu
     */
    public void CreaMenu() {
        /*SisAccesos = sisusuarios.SisAccesosNatGen(usuario);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SisAccesos", SisAccesos);
        CreaMenu creaMenu = new CreaMenu();
        //modulo Seguridad
        modelSecurity = new DefaultMenuModel();
        modelSecurity = creaMenu.CreaMenus(SisAccesos, "1", modelSecurity);

        if (creaMenu.getCountAccesos() > 0) {
            muestraSecurity = true;
        }
        //modulo Sol99
        modelSol = new DefaultMenuModel();
        modelSol = creaMenu.CreaMenus(SisAccesos, "2", modelSol);/////////////////////////////////////////////////////////////
        if (creaMenu.getCountAccesos() > 0) {
            muestraSol = true;
        }
        //modulo Propuestas
        modelProp = new DefaultMenuModel();
        modelProp = creaMenu.CreaMenus(SisAccesos, "7", modelProp);
        if (creaMenu.getCountAccesos() > 0) {
            muestraProp = true;
        }
        //modulo Estadisticas
        modelEstadisticas = new DefaultMenuModel();
        modelEstadisticas = creaMenu.CreaMenus(SisAccesos, "12", modelEstadisticas);
        if (creaMenu.getCountAccesos() > 0) {
            muestraEstadisticas = true;
        }

        //modulo Contrasenas
        modelContrasenas = new DefaultMenuModel();
        modelContrasenas = creaMenu.CreaMenus(SisAccesos, "5", modelContrasenas);
        if (creaMenu.getCountAccesos() > 0) {
            muestraContrasenas = true;
        }*/

    }

    public List<Object[]> getSisAccesos() {
        return SisAccesos;
    }

    public void setSisAccesos(List<Object[]> SisAccesos) {
        this.SisAccesos = SisAccesos;
    }

    public boolean getEstaLogeado() {
        return logeado;
    }

    public void setEstaLogueado(boolean logeado) {
        this.logeado = logeado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toUpperCase();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        if (!Compara(page.trim())) {
            page = "Construccion.xhtml";
        }
        this.page = page;
        // GeneraMenus();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo, List<Object[]> SisAccesosS) {
        this.titulo = "";
        String Titulo = "";
        if (titulo.length() > 0 && titulo != null && !titulo.equals("")) {
            if (titulo.indexOf("/") != 0) {
                int pos = titulo.lastIndexOf("/");
                String cad1 = titulo.substring(pos + 1, titulo.length());
                if (cad1.length() > 0) {
                    String cad2 = cad1.substring(0, cad1.indexOf("."));
                    if (SisAccesosS != null) {
                        for (Object[] result : SisAccesosS) {
                            if (result[1].toString().matches(cad2)) {
                                Titulo = (String) result[0];
                            }
                        }

                    }
                }
            }
        }

        this.titulo = Titulo;

    }

    public List<String> getXhtmls() {
        return getResources("/", ".xhtml");
    }

    /**
     * Trae en un list Las paginas Existentes
     *
     * @param path
     * @param suffix
     * @return
     */
    protected List<String> getResources(String path, String suffix) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Set<String> resources = context.getResourcePaths(path);
        List<String> filteredResources = new ArrayList<String>();
        for (String resource : resources) {
            if (resource.endsWith(suffix)) {
                filteredResources.add(resource);
            } else if (resource.endsWith("/")) {
                filteredResources.addAll(getResources(resource, suffix));
            }
        }
        return filteredResources;
    }

    /**
     * Compara si la pagina existe o No
     *
     * @param page
     * @return
     */
    public boolean Compara(String page) {
        List<String> xhtmls = getXhtmls();
        return xhtmls.contains(page);
    }

    public MenuModel getModelSol() {
        return modelSol;
    }

    public void setModelSol(MenuModel modelSol) {
        this.modelSol = modelSol;
    }

    public MenuModel getModelSecurity() {
        return modelSecurity;
    }

    public void setModelSecurity(MenuModel modelSecurity) {
        this.modelSecurity = modelSecurity;
    }

    public MenuModel getModelProp() {
        return modelProp;
    }

    public void setModelProp(MenuModel modelProp) {
        this.modelProp = modelProp;
    }

    public boolean isMuestraSol() {
        return muestraSol;
    }

    public void setMuestraSol(boolean muestraSol) {
        this.muestraSol = muestraSol;
    }

    public boolean isMuestraSecurity() {
        return muestraSecurity;
    }

    public void setMuestraSecurity(boolean muestraSecurity) {
        this.muestraSecurity = muestraSecurity;
    }

    public boolean isMuestraProp() {
        return muestraProp;
    }

    public void setMuestraProp(boolean muestraProp) {
        this.muestraProp = muestraProp;
    }

    public MenuModel getModelEstadisticas() {
        return modelEstadisticas;
    }

    public void setModelEstadisticas(MenuModel modelEstadisticas) {
        this.modelEstadisticas = modelEstadisticas;
    }

    public boolean isMuestraEstadisticas() {
        return muestraEstadisticas;
    }

    public void setMuestraEstadisticas(boolean muestraEstadisticas) {
        this.muestraEstadisticas = muestraEstadisticas;
    }

    public String getTituloMenu() {
        return tituloMenu;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    public MenuModel getModelContrasenas() {
        return modelContrasenas;
    }

    public void setModelContrasenas(MenuModel modelContrasenas) {
        this.modelContrasenas = modelContrasenas;
    }

    public boolean isMuestraContrasenas() {
        return muestraContrasenas;
    }

    public void setMuestraContrasenas(boolean muestraContrasenas) {
        this.muestraContrasenas = muestraContrasenas;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
