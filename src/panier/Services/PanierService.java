/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Services;

import panier.Entity.Panier;
import panier.Entity.Produit;
import panier.Entity.Utilisateur;
import panier.Util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author broum
 */
public class PanierService {
    
    private static final String REQUETE_SELECT = "select * from panier p inner join produit pr \n" +
"where (p.id_user = ? and p.id_commande = 0 and pr.id_prod=p.id_produit) " ;
    private static final String REQUETE_INSERT = "INSERT INTO panier (id_produit,id_user,quantite) VALUES(?,?,?) ";
    private static final String REQUETE_DELETE = "DELETE FROM PANIER WHERE id = ?";
    private static final String REQUETE_UPDATE = "UPDATE PANIER SET quantite = ? where id = ? ";
    private static final String REQUETE_SELECT_BYID = "SELECT * FROM PANIER WHERE ID = ? ";
    private static final String REQUETE_SELECT_IDPROD = "SELECT id_produit FROM PANIER WHERE ID = ? ";
    private static final String REQUETE_SELECT_QUANTITE = "SELECT quantite FROM produit WHERE ID = ? ";
    private static final String REQUETTE_ID_PANIER = "SELECT ID FROM PANIER WHERE ID_produit = ? and id_user = ? and id_commande = 0";
    
    
    public boolean ajouterPanier(Produit produit,Utilisateur u,int Quantite){
         if(Quantite<produit.getQuaniteDispo()) {
      try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_INSERT);
            pst.setInt(1, produit.getId_prod());
            pst.setInt(2, u.getId());
            pst.setInt(3, Quantite);
            pst.executeUpdate();
            System.out.println("Produit Ajouté au panier! ");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur Produit deja dans panier ");
            return false;
        }
         }
         else {
             System.out.println("Quantite inssufisante");
             return false;
         }
   
        }
     
     public boolean supprimerPanier(int id ){
        
      try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_DELETE);
            pst.setInt(1, id);
           
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Echec de suppression");
            return false;
        }
    }
     
     public ArrayList<Panier> afficherPanier(int id_user){
           ArrayList<Panier> list=new ArrayList();
      try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_SELECT);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Panier p = new Panier();
                p.setId_user(id_user);
                p.setId(rs.getInt("p.id"));
                p.setId_produit(rs.getInt("p.id_produit"));
                p.setTotal(rs.getDouble("pr.prix_prod")*rs.getInt("p.Quantite"));
                p.setQuantite(rs.getInt("p.Quantite"));
                System.out.println(p);
                list.add(p);
                
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
      return list;
     }
        
   public boolean modifierPanier(Panier p,int Quantite){
       
       try{
       PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_UPDATE);
            pst.setInt(1, Quantite);
            pst.setInt(2,p.getId() );
            pst.executeUpdate();
            System.out.println("Produit Modifier! ");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }
         
        
   
   }
   public Panier getData(int id){
        Panier p = new Panier();
         try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_SELECT_BYID);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                p.setId_user(rs.getInt("id_user"));
                p.setId(rs.getInt("id"));
                p.setId_produit(rs.getInt("id_produit"));
                p.setQuantite(rs.getInt("Quantite"));
                p.setId_commande(rs.getInt("id_commande"));
               return p;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
           return p;
   }
   
   public int getQuantiteP(int id){
        Produit p = new Produit();
        int total = 0;
        int id_prod = 0;
         try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_SELECT_IDPROD);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                 id_prod = rs.getInt("id_produit");
            }
            PreparedStatement pst2 = new MyConnection().getCnx().prepareStatement(REQUETE_SELECT_QUANTITE);
            pst2.setInt(1,id_prod);
            ResultSet RS = pst2.executeQuery();
            while(RS.next()){
                total = RS.getInt("quantite");
            }
            pst.close();
            rs.close();
            pst2.close();
            RS.close();
            return total;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
           return total;
   }
         
     public int getIdPanier(Produit p,Utilisateur u){
        Panier panier = new Panier();
        int id = 0;
        
         try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETTE_ID_PANIER);
            pst.setInt(1, p.getId_prod());
            pst.setInt(2, u.getId());

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                 id = rs.getInt("id");
            }
           
            pst.close();
            rs.close();
          
            return id;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
           return id;
   }
             
}

