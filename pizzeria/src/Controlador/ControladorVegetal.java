/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.PizzabaseJpaController;
import DAO.VegetalJpaController;
import Logica.Carne;
import Logica.Pizzabase;
import static Logica.Pizzabase_.precio;
import Logica.Vegetal;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorVegetal 
{
     VegetalJpaController logica;

    public ControladorVegetal(EntityManagerFactory con) {
        logica = new VegetalJpaController(con);
    }
    
    
    
    public boolean ingresarVegetal (String nombre, int precioporcion, int cantidad, String tipo, String carbohidratos) throws Exception
    {
        boolean operacion = false;
        int val = logica.num_sequence();
        Vegetal vegetal = new Vegetal(0,val,nombre, precioporcion, cantidad, tipo, carbohidratos);
        try 
        {
            logica.create(vegetal);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
    public Vegetal buscarVegetal (Integer id) throws Exception
    {
        
        Vegetal vegetal = new Vegetal();
        try 
        {
            vegetal = logica.findVegetal(id);
        }
        catch (Exception ex)
        {
            
        }
        
        return vegetal;
    }
    
    
       public boolean EditarVegetal (Vegetal vegetal) throws Exception
    {
        boolean operacion = false;
        try 
        {
            logica.edit(vegetal);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
     
       
        public boolean BorrarVegetal (Integer id)
    {
        
        
        boolean operacion = false;
        try 
        {
            logica.destroy(id);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }   

}
