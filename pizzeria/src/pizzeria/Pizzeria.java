/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import Controlador.ControladorCarne;
import Controlador.ControladorPizzaBase;
import Controlador.ControladorSalsa;
import Controlador.ControladorVegetal;
import DAO.Conexion;
import Logica.Pizzabase;
import Logica.Salsa;

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
//        String nombre = "p4";
//        String tamano = "mediana";
//        String presentacion = "a";
//        int precio = 1000;
//        ControladorPizzaBase controlador = new ControladorPizzaBase(con.getCon());
//        boolean ingreso =controlador.ingresarPizza(nombre, tamano, presentacion, precio);
//        if (ingreso)
//        {
//            System.out.println("operacion exitosa");
//        }
//        else 
//        {
//            System.out.println("fallo en la operacion");
//        }
//        
//        Pizzabase pizza = controlador.buscarPizza(1);
//        System.out.println(pizza .getTamano());
//        
//        pizza.setPrecio(15000);
//        controlador.EditarPizza(pizza);
        
        ControladorSalsa controlador = new ControladorSalsa(con.getCon());
//        Salsa salsa = controlador.buscarSalsa(2);
//        System.out.println(salsa.getNombre());
        String nombre = "s4"; 
        int precioporcion = 2000;
        int cantidad = 1;
        String tipo = "a";
        controlador.ingresarSalsa(nombre, precioporcion, cantidad, tipo);
        ControladorVegetal controlador2 = new ControladorVegetal(con.getCon());
        String nombre2 = "v4";
        int precioporcion2 = 1500;
        int cantidad2 = 1;
        String tipo2 = "t1";
        String carbohidratos = "100";
        controlador2.ingresarVegetal(nombre2, precioporcion2, cantidad2, tipo2, carbohidratos);
        
        
    }
    
}
