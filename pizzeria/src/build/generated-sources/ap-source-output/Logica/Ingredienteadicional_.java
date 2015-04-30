package Logica;

import Logica.Itempedidoingrediente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-29T11:41:32")
@StaticMetamodel(Ingredienteadicional.class)
public class Ingredienteadicional_ { 

    public static volatile SingularAttribute<Ingredienteadicional, String> nombre;
    public static volatile SingularAttribute<Ingredienteadicional, Integer> precioporcion;
    public static volatile SingularAttribute<Ingredienteadicional, Integer> ingredienteId;
    public static volatile SingularAttribute<Ingredienteadicional, Integer> cantidad;
    public static volatile SingularAttribute<Ingredienteadicional, String> tipo;
    public static volatile CollectionAttribute<Ingredienteadicional, Itempedidoingrediente> itempedidoingredienteCollection;
    public static volatile SingularAttribute<Ingredienteadicional, byte[]> foto;

}