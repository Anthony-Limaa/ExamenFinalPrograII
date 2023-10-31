package edu.umg;

import java.sql.Connection;
import java.sql.DriverManager;

public class Cconexion {

    Connection conectar = null;

    String usuario = "postgres";
    String contraseña = "mysecretpassword";

    String bd = "postgres";
    String puerto = "5432";

    public Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection("jdbc:postgresql://localhost:" + puerto + "/" + bd, usuario, contraseña);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexión");
            e.printStackTrace(); // Esto imprimirá detalles del error en la consola
        }
        return conectar;
    }
}
