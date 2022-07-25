
package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComboboxSucursal {
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;

    public ComboboxSucursal() {
    }
    public Arraylist getlistaSucursales(){
        System.out.println("getlistaSucursales()");    
    }
            
}
