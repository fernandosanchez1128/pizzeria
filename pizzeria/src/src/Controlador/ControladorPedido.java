/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CarneJpaController;
import Logica.Factura;
import Logica.Pizzabase;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fersanq
 */
public class ControladorPedido {
    
    EntityManagerFactory conexion;
    ControladorFactura controlfac;
    ControladorFacturaPizza controlfac_pizza;
    ControladorFacturaIngrediente controlfac_ingrediente;
    
    
    public ControladorPedido(EntityManagerFactory con) {
        conexion = con;
        controlfac = new ControladorFactura(con);
        controlfac_pizza = new ControladorFacturaPizza(con);
        controlfac_ingrediente = new ControladorFacturaIngrediente(con);
    }
}
    

//   public Factura pedido (Pizzabase pizza, Integer [] id_ingredientes) {
// //       id_factura = //consultar el proximo de la secuencia;
//        
//       
//                
//                
//        
//    }
//}
