/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fersanq
 */
public class Conexion {
    private EntityManagerFactory con;
    
    
    public  Conexion ()
    {
        con = Persistence.createEntityManagerFactory("PizzeriaPU");
    }
    public EntityManagerFactory getCon ()
    {
        return con;
    }
            
}
