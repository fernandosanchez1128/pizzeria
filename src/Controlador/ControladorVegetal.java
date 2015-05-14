/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.IngredienteadicionalJpaController;
import DAO.PizzabaseJpaController;
import DAO.VegetalJpaController;
import Logica.Carne;
import Logica.Ingredienteadicional;
import Logica.Pizzabase;
import static Logica.Pizzabase_.precio;
import Logica.Salsa;
import Logica.Vegetal;
import java.util.Iterator;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorVegetal 
{
     VegetalJpaController logica;
     IngredienteadicionalJpaController  log_ingrediente;
    public ControladorVegetal(EntityManagerFactory con) {
        logica = new VegetalJpaController(con);
        log_ingrediente = new IngredienteadicionalJpaController(con);
    }
    
    
    
    public boolean ingresarVegetal (String nombre, int precioporcion, int cantidad, String tipo, String carbohidratos,byte [] foto) throws Exception
    {
        boolean operacion = false;
        int id_ingrediente = log_ingrediente.getNext();
        Ingredienteadicional ingrediente  = new Ingredienteadicional(id_ingrediente, nombre, precioporcion, cantidad, tipo);
        ingrediente.setFoto(foto);
        System.out.println("id " + id_ingrediente);
        Vegetal vegetal = new Vegetal(0,id_ingrediente +1, nombre, precioporcion, cantidad, tipo, carbohidratos);
        vegetal.setFoto(foto);
        try 
        {
            log_ingrediente.create(ingrediente);
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

        
             public Iterator <Vegetal>  getVegetales ()
        {
           Iterator <Vegetal>  vegetales = logica.findVegetalEntities().iterator();
//           while (vegetales.hasNext())
//                System.out.println(vegetales.next());   
           return vegetales;
        }   
}
