/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.dao.ProduitCRUD;
import edu.entities.Produit;
import edu.utils.JavaMailUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gayth
 */
public class ProduitService {
    private ProduitCRUD produitCRUD = new ProduitCRUD();

        public void addProduit (Produit p) {
            this.produitCRUD.addProduit(p);
        }
        
        public void updateProduit (Produit p) {
            this.produitCRUD.updateProduit(p);
            if (p.getQuantite() < 5) {
                try {
                    System.out.println(p.getNom()+" stock is getting exhausted");
                    JavaMailUtil.sendMail("tanwichette@gmail.com", p.getNom()+" stock is getting exhausted","Alert !");
                } catch (Exception ex) {
                    Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        public List<Produit> DisplayStock() {
            List<Produit> myList = this.produitCRUD.DisplayStock();
            return myList;
        }
        
         public void deleteProduit (int x) {
            this.produitCRUD.deleteProduit(x);
        }
        
}
