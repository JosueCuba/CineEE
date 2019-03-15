/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author PC13
 */
public class Conexion {
    public static  Connection cnx = null;
    public static Connection conectar () throws Exception{
         InputStream inputStream
                = Conexion.class.getClassLoader().getResourceAsStream("propiedades/db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String user = properties.getProperty("user");
            String pwd = properties.getProperty("pwd");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("Error de conexion");
        }
        return  cnx ;
    }
    public static void cerrarCnx() throws Exception {
       if (cnx != null) {
            cnx.close();
        } 
    }
    public static void main(String[] args) throws Exception{
       conectar();
        if (cnx != null) {
            System.out.println("abierta");
        } else {
            System.out.println("cerrada");
        }
    }
}
