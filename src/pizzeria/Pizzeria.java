/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import Controlador.ControladorCarne;
import Controlador.ControladorFactura;
import Controlador.ControladorPizzaBase;
import Controlador.ControladorSalsa;
import Controlador.ControladorVegetal;
import DAO.Conexion;
import Logica.Carne;
import Logica.Pizzabase;
import Logica.Salsa;
import Logica.Vegetal;
import com.sun.media.sound.DLSModulator;

/**
 *
 * @author fersanq
 */
public class Pizzeria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        
        Conexion con =  new Conexion();
//        Insercion de los datos ;
        String nombre = "p5";
        String tamano = "mediana";
        String presentacion = "a";
        int precio = 1000;
       
        //ControladorPizzaBase controlador = new ControladorPizzaBase(con.getCon());
//        ControladorCarne control = new ControladorCarne (con.getCon());
//        Carne carne  = control.buscarCarne(40);
//        System.out.println(carne);
//        
//        byte [] a = {};
//        boolean ingreso =controlador.ingresarPizza(nombre, tamano, presentacion, precio,a);
//        if (ingreso)
//        {
//            System.out.println("operacion exitosa");
//        }
//        else 
//        {
//            System.out.println("fallo en la operacion");
//        }
//        Pizzabase pizza = controlador.buscarPizza(17);
//        pizza.setPrecio(2);
//        controlador.EditarPizza(pizza);
        

//        ControladorSalsa control = new ControladorSalsa(con.getCon());
//
//        String nombre1 = "s7"; 
//        int precioporcion = 2000;
//        int cantidad = 1;
//        String tipo = "a";
//        control.ingresarSalsa(nombre1, precioporcion, cantidad, tipo);
//  ControladorVegetal controlador2 = new ControladorVegetal(con.getCon());
// 
//
//        String nombre2 = "v8";
//        int precioporcion2 = 1500;
//        int cantidad2 = 1;
//        String tipo2 = "t1";
//        String carbohidratos = "100";
//        controlador2.ingresarVegetal(nombre2, precioporcion2, cantidad2, tipo2, carbohidratos);
//        
//       ControladorSalsa control = new ControladorSalsa(con.getCon()); 
//       control.getSalsas();
        
//    ControladorFactura controlador_fac = new ControladorFactura( con.getCon());
//    int [] ingredientes = {1};
//    controlador_fac.procesoCompra(1,ingredientes);
        
        //vista del administrador
//        VistaAdmin admin = new VistaAdmin(con.getCon()); 
//         admin.setVisible(true);
//        
         
//         //vista del cliente
//         
         VistaCliente cliente = new VistaCliente(con.getCon());
         cliente.setVisible(true);
         
    }
    
}
