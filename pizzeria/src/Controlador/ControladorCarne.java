/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.PizzabaseJpaController;
import Logica.Carne;
import Logica.Pizzabase;
import static Logica.Pizzabase_.precio;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorCarne 
{
     CarneJpaController logica;

    public ControladorCarne(EntityManagerFactory con) {
        logica = new CarneJpaController(con);
    }
    
    
    
    public boolean ingresarCarne (String nombre, int precioporcion, int cantidad, String tipo, String presentacion, int cantidadgrasas, String animal) throws Exception
    {
        boolean operacion = false;
        Carne carne = new Carne(nombre, precioporcion, cantidad, tipo, presentacion, cantidadgrasas, animal);
        try 
        {
            logica.create(carne);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
    public Carne buscarCarne (Integer id) throws Exception
    {
        
        Carne carne = new Carne();
        try 
        {
            carne = logica.findCarne(id);
        }
        catch (Exception ex)
        {
            
        }
        
        return carne;
    }
    
    
       public boolean EditarPizza (Carne carne) throws Exception
    {
        boolean operacion = false;
        try 
        {
            logica.edit(carne);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
     
       
        public boolean BorrarCarne (Integer id)
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
