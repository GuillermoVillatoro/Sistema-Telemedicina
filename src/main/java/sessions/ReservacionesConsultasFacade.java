/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ReservacionesConsultas;
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
public class ReservacionesConsultasFacade extends AbstractFacade<ReservacionesConsultas> implements ReservacionesConsultasFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservacionesConsultasFacade() {
        super(ReservacionesConsultas.class);
    }

    @Override
    public List<Object[]> buscaReserConsul(int idUser) {
        Query query = em.createNativeQuery("SELECT a.id,a.fecha,a.horaIni,a.horaFin,CURDATE(),cast(DATE_FORMAT(NOW( ), \"%H\") AS INT) AS hora,idEmpleado,idDoctor,estado FROM reservaciones_consultas a WHERE a.idEmpleado= ?");
        query.setParameter(1, idUser);
        return query.getResultList();

    }
    
     @Override
    public List<Object[]> buscaReserConsulLibres() {
        Query query = em.createNativeQuery("SELECT a.id,a.fecha,a.horaIni,a.horaFin,CURDATE(),cast(DATE_FORMAT(NOW( ), \"%H\") AS INT) AS hora,idEmpleado,idDoctor,estado FROM reservaciones_consultas a WHERE a.estado =0");
        return query.getResultList();

    }
    
    @Override
    public boolean updateCita(int idUser,int idDoctor,int idEvento){
        Query query = em.createNativeQuery("update reservaciones_consultas  SET idEmpleado=?,idDoctor=?,estado=1 where id =? ");
        query.setParameter(1,idUser );
        query.setParameter(2,idDoctor);
        query.setParameter(3,idEvento);
        query.executeUpdate();
        return true;
    }

}
