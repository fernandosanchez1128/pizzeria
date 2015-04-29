/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IngredienteadicionalJpaController;
import DAO.ItempedidoingredienteJpaController;
import DAO.ItempedidopizzaJpaController;
import Logica.Ingredienteadicional;
import Logica.Itempedidoingrediente;
import Logica.Itempedidopizza;
import Logica.Pizzabase;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorFacturaPizza {
    
    ItempedidopizzaJpaController logica;
    ControladorPizzaBase controladorPizzaBase;

    public ControladorFacturaPizza(EntityManagerFactory con) {
        logica = new ItempedidopizzaJpaController(con);
        controladorPizzaBase = new ControladorPizzaBase(con);
    }
    
      
    

    
        public boolean agregarIngrediente (Integer id_factura,Integer id_pizza ) throws Exception
    {
        boolean operacion = false;
        Pizzabase pizza = controladorPizzaBase.buscarPizza(id_pizza);
        Itempedidopizza pizzapedido = new Itempedidopizza(id_factura);
        pizzapedido.setPizzaId(pizza);
        
        
        try 
        {
            
            logica.create(pizzapedido);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
}
