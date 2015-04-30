/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import DAO.FacturaJpaController;
import DAO.IngredienteadicionalJpaController;
import DAO.ItempedidoingredienteJpaController;
import DAO.SalsaJpaController;
import DAO.VegetalJpaController;
import Logica.Carne;
import Logica.Ingredienteadicional;
import Logica.Itempedidoingrediente;
import Logica.Salsa;
import Logica.Vegetal;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorFacturaIngrediente {
    ItempedidoingredienteJpaController  logica;
    CarneJpaController log_carne;
    VegetalJpaController log_vegetal;
    SalsaJpaController log_salsa;
    IngredienteadicionalJpaController log_ingrediente;

    public ControladorFacturaIngrediente(EntityManagerFactory con) {
        logica = new ItempedidoingredienteJpaController(con);
        log_carne = new CarneJpaController( con);
        log_vegetal = new VegetalJpaController( con);
        log_salsa = new SalsaJpaController( con);
        log_ingrediente = new IngredienteadicionalJpaController(con);
    }
    
    
        public boolean agregarIngrediente (Integer id_factura,Integer id_ingrediente , String tipo) throws Exception
    {
        boolean operacion = false;
        
   
        
        
        try 
        {
            if (tipo == "carne") {

                Carne carne = log_carne.findCarne(id_ingrediente);
                Ingredienteadicional ing_adicional = log_ingrediente.findIngredienteadicional(carne.getIngredienteId());
                Itempedidoingrediente ingrediente_adicional = new Itempedidoingrediente(id_factura);
                ingrediente_adicional.setIngredienteId(ing_adicional);
                logica.create(ingrediente_adicional);
                operacion = true;
            }
            if (tipo == "vegetal") {
                System.out.println("paso por vegetal");

                Vegetal vegetal = log_vegetal.findVegetal(id_ingrediente);
                System.out.println("creacion del objeto vegetal");
                Ingredienteadicional ing_adicional = log_ingrediente.findIngredienteadicional(vegetal.getIngredienteId());
                System.out.println(ing_adicional);
                System.out.println("paso por nombre del ingrediente");
                Itempedidoingrediente ingrediente_adicional = new Itempedidoingrediente(id_factura);
                ingrediente_adicional.setIngredienteId(ing_adicional);
                System.out.println("un set del ingrediente");
                logica.create(ingrediente_adicional);
                System.out.println("adiccion del objeto a la base de datos ");
                operacion = true;
            }

            if (tipo == "salsa") {

                Salsa salsa = log_salsa.findSalsa(id_ingrediente);
                Ingredienteadicional ing_adicional = log_ingrediente.findIngredienteadicional(salsa.getIngredienteId());
                Itempedidoingrediente ingrediente_adicional = new Itempedidoingrediente(id_factura);
                ingrediente_adicional.setIngredienteId(ing_adicional);
                logica.create(ingrediente_adicional);
                operacion = true;
            }

            
            
        }
        catch (Exception ex)
        {
            System.out.println("paso por catch");
        }
        
        return operacion;
    }
    
}
