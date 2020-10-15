/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.HistorialConsultas;
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
public class HistorialConsultasFacade extends AbstractFacade<HistorialConsultas> implements HistorialConsultasFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialConsultasFacade() {
        super(HistorialConsultas.class);
    }

    @Override
    public List<Object[]> historialConsultas(int idEmpleado) {
        Query query = em.createNativeQuery("SELECT a.id,b.id as idEmpleado,concat(b.nombres,' ',b.apellidos)AS empleado,c.id AS idDoctor,concat(c.nombres,' ',c.apellidos) AS doctor,\n"
                + "a.idConsulta,d.fecha,d.horaIni,a.diagnostico,a.examenes,a.receta,cast(d.fecha - CURDATE() AS  integer ) AS diferencia,concat(concat(d.horaIni,':00 a '),concat(d.horaFin,':00')) AS hora  \n"
                + "FROM historial_consultas a\n"
                + "LEFT JOIN empleados b ON a.idEmpleado=b.id\n"
                + "LEFT JOIN doctores c ON a.idDoctor=c.id\n"
                + "LEFT JOIN reservaciones_consultas d ON a.idConsulta=d.id\n"
                + "\n"
                + "WHERE a.idEmpleado=?");
        query.setParameter(1, idEmpleado);
        return query.getResultList();

    }

    @Override
    public boolean insertConsulat(int idEmpleado, int idDoctor, int idConsulta) {
        Query query = em.createNativeQuery("insert into historial_consultas (idEmpleado,idDoctor,idConsulta)values(?,?,?) ");
        query.setParameter(1, idEmpleado);
        query.setParameter(2, idDoctor);
        query.setParameter(3, idConsulta);
        query.executeUpdate();
        return true;

    }

}
