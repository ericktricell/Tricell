/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.fabrica;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Eu
 */
public class FabricaSessao {
    private EntityManagerFactory emf;

    public FabricaSessao() {
        emf = Persistence.createEntityManagerFactory("TricellDashBoardPU");
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
    
    
}
