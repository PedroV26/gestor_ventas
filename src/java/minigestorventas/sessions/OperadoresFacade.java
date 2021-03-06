/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minigestorventas.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import minigestorventas.entities.Operadores;

/**
 *
 * @author fernando
 */
@Stateless
public class OperadoresFacade extends AbstractFacade<Operadores> {

    @PersistenceContext(unitName = "gestor_ventasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperadoresFacade() {
        super(Operadores.class);
    }
    
}
