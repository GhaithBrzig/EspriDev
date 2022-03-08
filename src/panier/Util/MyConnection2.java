/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panier.Util;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SBS
 */
public class MyConnection2 {
    public String url="jdbc:mysql://localhost:3306/pidev";
    public String login ="root";
    public String pwd="";
    public Connection cnx;
    public static MyConnection2 instance;
    
    private MyConnection2(){
        try {
            cnx = (Connection) DriverManager.getConnection(url,login,pwd);
            System.out.println("Connecté!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnection2 getInstance() {
        if(instance == null)
            instance = new MyConnection2();
        
        return instance;
    }
    
    
}
