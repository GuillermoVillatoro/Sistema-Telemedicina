/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Doctores;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marvin Moran
 */
@Local
public interface DoctoresFacadeLocal {

    void create(Doctores doctores);

    void edit(Doctores doctores);

    void remove(Doctores doctores);

    Doctores find(Object id);

    List<Doctores> findAll();

    List<Doctores> findRange(int[] range);

    int count();
    
}
