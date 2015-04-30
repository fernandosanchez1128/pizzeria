package Logica;

import Logica.Itempedidopizza;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-29T11:41:32")
@StaticMetamodel(Pizzabase.class)
public class Pizzabase_ { 

    public static volatile SingularAttribute<Pizzabase, String> nombre;
    public static volatile SingularAttribute<Pizzabase, Integer> pizzaId;
    public static volatile SingularAttribute<Pizzabase, Integer> precio;
    public static volatile CollectionAttribute<Pizzabase, Itempedidopizza> itempedidopizzaCollection;
    public static volatile SingularAttribute<Pizzabase, byte[]> foto;
    public static volatile SingularAttribute<Pizzabase, String> tamano;
    public static volatile SingularAttribute<Pizzabase, String> presentacion;

}