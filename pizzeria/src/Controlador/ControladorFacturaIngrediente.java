/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.FacturaJpaController;
import DAO.IngredienteadicionalJpaController;
import DAO.ItempedidoingredienteJpaController;
import Logica.Ingredienteadicional;
import Logica.Itempedidoingrediente;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorFacturaIngrediente {
    ItempedidoingredienteJpaController  logica;
    IngredienteadicionalJpaController log_ingrediente;

    public ControladorFacturaIngrediente(EntityManagerFactory con) {
        logica = new ItempedidoingredienteJpaController(con);
        log_ingrediente = new IngredienteadicionalJpaController(con);
    }
    
    
        public boolean agregarIngrediente (Integer id_factura,Integer id_ingrediente ) throws Exception
    {
        boolean operacion = false;
        Ingredienteadicional ingrediente = log_ingrediente.findIngredienteadicional(id_ingrediente);
        Itempedidoingrediente ingrediente_adicional = new Itempedidoingrediente(id_factura);
        ingrediente_adicional.setIngredienteId(ingrediente);
        
        
        try 
        {
            
            logica.create(ingrediente_adicional);
            operacion = true;
        }
        catch (Exception ex)
        {
            
        }
        
        return operacion;
    }
    
}
