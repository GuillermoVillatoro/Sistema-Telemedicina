/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ReservacionesConsultas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marvin Moran
 */
@Local
public interface ReservacionesConsultasFacadeLocal {

    void create(ReservacionesConsultas reservacionesConsultas);

    void edit(ReservacionesConsultas reservacionesConsultas);

    void remove(ReservacionesConsultas reservacionesConsultas);

    ReservacionesConsultas find(Object id);

    List<ReservacionesConsultas> findAll();

    List<ReservacionesConsultas> findRange(int[] range);

    int count();

    public List<Object[]> buscaReserConsul(int idUser);

    public List<Object[]> buscaReserConsulLibres();

    public boolean updateCita(int idUser, int idDoctor, int idEvento);
    
}
