/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.IngredienteadicionalJpaController;
import DAO.PizzabaseJpaController;
import Logica.Carne;
import Logica.Ingredienteadicional;
import Logica.Pizzabase;
import static Logica.Pizzabase_.precio;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorCarne 
{
     CarneJpaController logica;
     IngredienteadicionalJpaController log_ingrediente;
     

    public ControladorCarne(EntityManagerFactory con) {
        logica = new CarneJpaController(con);
        log_ingrediente = new IngredienteadicionalJpaController(con);
        
    }
    
    
    
    public boolean ingresarCarne (String nombre, int precioporcion, int cantidad, String tipo, String presentacion, int cantidadgrasas, String animal,byte [] foto) throws Exception
    {
        boolean operacion = false;
        int id_ingrediente = log_ingrediente.getNext();
        Ingredienteadicional ingrediente  = new Ingredienteadicional(id_ingrediente, nombre, precioporcion, cantidad, tipo);
        ingrediente.setFoto(foto);
        Carne carne = new Carne(0,id_ingrediente+1, nombre, precioporcion, cantidad, tipo, presentacion, cantidadgrasas, animal);
        carne.setFoto(foto);
        try 
        {
            log_ingrediente.create(ingrediente);
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
    
    
       public boolean EditarCarne (Carne carne) throws Exception
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
        
   
        public Iterator <Carne>  getCarnes ()
        {
           Iterator <Carne>  carnes = logica.findCarneEntities().iterator();
//           while (carnes.hasNext())
//                System.out.println(carnes.next());
           return carnes;
        }
        

}
