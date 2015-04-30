/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.FacturaJpaController;
import DAO.SalsaJpaController;
import Logica.Factura;
import java.util.Date;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorFactura {
       
       FacturaJpaController  logica;
       ControladorFacturaPizza fac_pizza ;
       ControladorFacturaIngrediente fac_ingredientes  ;

    public ControladorFactura(EntityManagerFactory con) {
        logica = new FacturaJpaController(con);
        fac_pizza = new ControladorFacturaPizza(con);
        fac_ingredientes = new ControladorFacturaIngrediente(con);
    }
    
    public void procesoCompra (Integer id_pizza, int [] ingr_adicionales) throws Exception
    {
      
        int id_factura = ingresarFactura(0);
        fac_pizza.agregarPizza(id_factura, id_pizza);
        System.out.println("despues " + id_factura);
        for (int i= 0; i<ingr_adicionales.length; i++)
        {
            fac_ingredientes.agregarIngrediente(id_factura, ingr_adicionales[i],"vegetal");
        }
        editarFactura(id_factura);
    }
    public int ingresarFactura (int id_factura) throws Exception
    {
        boolean operacion = false;
        id_factura = logica.getNext();
        System.out.println("antes " + id_factura);
        Date fecha = new Date ("11/12/2015");
        Factura factura = new Factura(0,fecha,0);
        try 
        {
            logica.create(factura);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return id_factura;
    }
    
    
     public boolean editarFactura (int id_factura) throws Exception
    {
        boolean operacion = false;
        Factura fact = logica.findFactura(id_factura);
        try 
        {
            fact.setPreciototal(100000);
            logica.edit(fact);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
    
}
