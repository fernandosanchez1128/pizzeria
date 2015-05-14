/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.FacturaJpaController;
import DAO.SalsaJpaController;
import DAO.VegetalJpaController;
import Logica.Carne;
import Logica.Factura;
import Logica.Ingredienteadicional;
import Logica.Itempedidoingrediente;
import Logica.Salsa;
import Logica.Vegetal;
import java.util.Date;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorFactura {
       
       FacturaJpaController  logica;
       ControladorFacturaPizza fac_pizza ;
       ControladorFacturaIngrediente fac_ingredientes;
       ControladorPizzaBase log_pizza;
       CarneJpaController log_carne;
       VegetalJpaController log_vegetal;
       SalsaJpaController log_salsa;

       
    public ControladorFactura(EntityManagerFactory con) {
        logica = new FacturaJpaController(con);
        fac_pizza = new ControladorFacturaPizza(con);
        fac_ingredientes = new ControladorFacturaIngrediente(con);
        log_carne = new CarneJpaController( con);
        log_vegetal = new VegetalJpaController( con);
        log_salsa = new SalsaJpaController( con);
        log_pizza = new ControladorPizzaBase(con);
    }
    
    public Factura procesoCompra (Integer id_pizza, int [] ingr_adicionales,String [] objetos) throws Exception
    { 
      
        int precio = 0;
        int id_factura = ingresarFactura(0);
        fac_pizza.agregarPizza(id_factura, id_pizza);
        
        precio +=  log_pizza.buscarPizza(id_pizza).getPrecio();
      
        
        System.out.println("despues " + id_factura);
        for (int i= 0; i<ingr_adicionales.length; i++)
        {
            fac_ingredientes.agregarIngrediente(id_factura, ingr_adicionales[i],objetos[i]);
            System.out.println("a");
              if (objetos[i] == "carne") {

                Carne carne = log_carne.findCarne(ingr_adicionales[i]);
                //precio+= carne.getPrecioporcion();
            }
            if (objetos[i] == "vegetal") {
                
                Vegetal vegetal = log_vegetal.findVegetal(ingr_adicionales[i]);
               // precio+= vegetal.getPrecioporcion();
            }

            if (objetos[i] == "salsa") {
                
                Salsa salsa = log_salsa.findSalsa(ingr_adicionales[i]);
               // System.out.println(salsa);
                //precio+= salsa.getPrecioporcion();
            }
            
        }
        editarFactura(id_factura,precio);
        Factura fact = logica.findFactura(id_factura);
        return fact;
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
    
    
     public boolean editarFactura (int id_factura,double precio) throws Exception
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
