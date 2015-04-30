package Logica;

import Logica.Itempedidoingrediente;
import Logica.Itempedidopizza;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-29T11:41:32")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Integer> preciototal;
    public static volatile SingularAttribute<Factura, Itempedidopizza> itempedidopizza;
    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Itempedidoingrediente> itempedidoingrediente;
    public static volatile SingularAttribute<Factura, Integer> facturaId;

}