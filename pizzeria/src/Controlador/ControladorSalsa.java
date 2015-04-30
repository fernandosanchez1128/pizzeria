/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.PizzabaseJpaController;
import DAO.SalsaJpaController;
import Logica.Carne;
import Logica.Pizzabase;
import static Logica.Pizzabase_.precio;
import Logica.Salsa;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorSalsa 
{
     SalsaJpaController logica;

    public ControladorSalsa(EntityManagerFactory con) {
        logica = new SalsaJpaController(con);
    }
    
    
    
    public boolean ingresarSalsa (String nombre, int precioporcion, int cantidad, String tipo)
    {
        boolean operacion = false;
        Salsa salsa = new Salsa(nombre, precioporcion, cantidad, tipo);
        try 
        {
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

}
