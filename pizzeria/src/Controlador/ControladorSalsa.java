/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.IngredienteadicionalJpaController;
import DAO.PizzabaseJpaController;
import DAO.SalsaJpaController;
import Logica.Carne;
import Logica.Ingredienteadicional;
import Logica.Pizzabase;
import static Logica.Pizzabase_.precio;
import Logica.Salsa;
import java.util.Iterator;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorSalsa 
{
     SalsaJpaController logica;
     IngredienteadicionalJpaController  log_ingrediente;

    public ControladorSalsa(EntityManagerFactory con) {
        logica = new SalsaJpaController(con);
        log_ingrediente = new IngredienteadicionalJpaController(con);
    }
    
    
    
    public boolean ingresarSalsa (String nombre, int precioporcion, int cantidad, String tipo)
    {
        boolean operacion = false;
        int id_ingrediente = log_ingrediente.getNext();
        Ingredienteadicional ingrediente  = new Ingredienteadicional(id_ingrediente, nombre, precioporcion, cantidad, tipo);
        Salsa salsa = new Salsa(0,id_ingrediente +1,nombre, precioporcion, cantidad, tipo);
        try 
        {
            log_ingrediente.create(ingrediente);
            logica.create(salsa);
            
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
    
    public Salsa buscarSalsa (Integer id) throws Exception
    {
        
        Salsa salsa = new Salsa();
        try 
        {
            salsa = logica.findSalsa(id);
        }
        catch (Exception ex)
        {
            
        }
        
        return salsa;
    }
    
    
       public boolean EditarSalsa (Salsa salsa) throws Exception
    {
        boolean operacion = false;
        try 
        {
            logica.edit(salsa);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
     
       
        public boolean BorrarSalsa (Integer id)
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

        
           public Iterator <Salsa>  getSalsas ()
        {
           Iterator <Salsa>  salsas = logica.findSalsaEntities().iterator();
           while (salsas.hasNext())
                System.out.println(salsas.next());
           
           return salsas;
        }   
}
