/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.HistorialConsultas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marvin Moran
 */
@Local
public interface HistorialConsultasFacadeLocal {

    void create(HistorialConsultas historialConsultas);

    void edit(HistorialConsultas historialConsultas);

    void remove(HistorialConsultas historialConsultas);

    HistorialConsultas find(Object id);

    List<HistorialConsultas> findAll();

    List<HistorialConsultas> findRange(int[] range);

    int count();

    public List<Object[]> historialConsultas(int idEmpleado);

    public boolean insertConsulat(int idEmpleado, int idDoctor, int idConsulta);

    
}
