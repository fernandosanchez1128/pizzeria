/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


/**
 *
 * @author fersanq
 */
import DAO.PizzabaseJpaController;
import Logica.Pizzabase;
import javax.persistence.EntityManagerFactory;
public class ControladorPizzaBase {
    PizzabaseJpaController logica;

    public ControladorPizzaBase(EntityManagerFactory con) {
        logica = new PizzabaseJpaController(con);
    }
    
    
    
    public boolean ingresarPizza (String nombre, String tamano, String presentacion, int precio) throws Exception
    {
        boolean operacion = false;
        Pizzabase pizza = new Pizzabase( nombre, tamano, presentacion, precio);
        try 
        {
            logica.create(pizza);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
    public Pizzabase buscarPizza (String nombre) throws Exception
    {
        
        Pizzabase pizza = new Pizzabase();
        try 
        {
            pizza = logica.findPizzabaseName(nombre);
        }
        catch (Exception ex)
        {
            
        }
        
        return pizza;
    }
    
        public Pizzabase buscarPizza (Integer id) throws Exception
    {
        
        Pizzabase pizza = new Pizzabase();
        try 
        {
            pizza = logica.findPizzabase(id);
        }
        catch (Exception ex)
        {
            
        }
        
        return pizza;
    }
    
    
       public boolean EditarPizza (Integer pizzaId, String nombre, String tamano, String presentacion, int precio) throws Exception
    {
        boolean operacion = false;
        Pizzabase pizza = new Pizzabase(pizzaId, nombre, tamano, presentacion, precio);
        try 
        {
            logica.edit(pizza);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
         
       
    public boolean EditarPizza (Pizzabase pizza) throws Exception
    {
        boolean operacion = false;
        try 
        {
            logica.edit(pizza);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
       
       
     
       
        public boolean BorrarPizza (Integer id)
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
