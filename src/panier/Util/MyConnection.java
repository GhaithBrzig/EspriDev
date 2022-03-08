/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author broum
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/tanwichette";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public MyConnection() {
        try {
           cnx= DriverManager.getConnection(url,login,pwd);
           System.out.println("connexion etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connexion non etablie!");
        }
    }
    public Connection getCnx() {
        return cnx;
    }
}
