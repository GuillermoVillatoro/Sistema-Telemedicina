/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import clases.md5;
import entities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marvin Moran
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public List<Object[]> SisUsuariosLogin(String usuario, String contrasena) {
        List<Object[]> results = null;
        int lenUsuario = usuario.length();
        int lenContrasena = contrasena.length();
        if (lenUsuario <= 20 && lenContrasena <= 35) {
            usuario = usuario.substring(0, lenUsuario);
            contrasena = contrasena.substring(0, lenContrasena);
            usuario.replaceAll("'", "");
            contrasena = contrasena.replaceAll("'", "");
            contrasena = md5.encriptaEnMD5(contrasena.trim().toUpperCase());
            Query query = em.createNativeQuery("select c.usuario,c.clave,c.idRole,c.nombres,c.apellidos,c.habilitado,c.id from usuarios c where c.usuario=? AND c.clave=? ");
            query.setParameter(1, usuario.toUpperCase().trim());
            query.setParameter(2, contrasena.trim());
            results = query.getResultList();
        }
        return results;

    }

    @Override
    public List<Object[]> buscaDatosPaciente(int idUsuario) {
        Query query = em.createNativeQuery("SELECT a.id AS idEmpleado,a.nombres,a.apellidos,a.direccion,a.telefono,a.fecNac,b.nombre AS nomDepto,a.sueldo,a.email,a.sexo,a.foto "
                + " FROM empleados a  "
                + " LEFT JOIN departamento b ON a.idDepto=b.id "
                + " WHERE idUsuario = ? ");
        query.setParameter(1, idUsuario);
        return query.getResultList();
    }
}
