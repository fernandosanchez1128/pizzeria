/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.FacturaJpaController;
import DAO.SalsaJpaController;
import Logica.Factura;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorFactura {
    
       FacturaJpaController  logica;

    public ControladorFactura(EntityManagerFactory con) {
        logica = new FacturaJpaController(con);
    }
    
    public boolean ingresarFactura (Integer id_factura) throws Exception
    {
        boolean operacion = false;
        Factura factura = new Factura(id_factura);
        try 
        {
            logica.create(factura);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
     public boolean editarFactura (Factura factura) throws Exception
    {
        boolean operacion = false;
        try 
        {
            logica.edit(factura);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
    
}
