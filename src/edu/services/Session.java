/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

/**
 *
 * @author iheb
 */


import edu.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author HP PC
 */
class Session {
    
    Connection cnx2;
    static Utilisateur user=null ;
    
    
    
    

    public Utilisateur getUser() {
   
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
    public String User(String email) throws SQLException{
        String requete = "SELECT email FROM login WHERE nom ='"+email+"'";

            PreparedStatement pst;
            pst = cnx2.prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);
           
            
            
                if(rs.next())
                    return "Client";
                else{
                    return null;
                }
            }
            
    }