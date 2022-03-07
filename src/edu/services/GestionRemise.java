/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.services;

import edu.entities.Produit;
import edu.entities.ProduitM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SBS
 */
public class GestionRemise {

    List<ProduitM> l;
    List<ProduitM> l2;
    ProduitCRUD pcr;
    double taux;

    public GestionRemise() {
        pcr = new ProduitCRUD();
        l = pcr.GetProd();
        l2 = clone(l);
        for (ProduitM p : l2) {

            if (!p.getRemise().equals("0%")) {
                p.setPrix_prod(p.getPrix_prod() - (p.getPrix_prod() * Integer.parseInt(p.getRemise().replace("%", ""))) * 0.01);
            }
        }

        taxPerProd(10);

        taux = 0;

    }

    public void taxPerProd(double tax) {

        if (tax > 0 && tax < 20) {

            for (ProduitM e : l2) {

                e.setPrix_prod(e.getPrix_prod() - (e.getPrix_prod() * (tax * 0.01)));
                taux = tax;

            }
            System.out.println("tax effectué");
        }

    }

    public void displayProd() {
        System.out.println("\nAvant le Remise");

        for (ProduitM e : l) {
            System.out.println(e);
        }
        System.out.println("\nApres le Remise");
        for (ProduitM e : l2) {
            System.out.println(e);
        }
    }

    public List<ProduitM> clone(List<ProduitM> lo) {
        List<ProduitM> lc = new ArrayList();
        for (ProduitM e : lo) {
            ProduitM p = new ProduitM();
            p.setId_prod(e.getId_prod());
            p.setLib_prod(e.getLib_prod());
            p.setDescription(e.getDescription());
            p.setPrix_prod(e.getPrix_prod());
            p.setQuaniteDispo(e.getQuaniteDispo());
            p.setRemise(e.getRemise());
            p.setCategorie(e.getCategorie());
            lc.add(p);
        }
        return lc;
    }

    public List<ProduitM> getListB() {
        return l2;
    }

    public List<ProduitM> getListA() {
        return l;
    }

    public void ReDefine() {
        l = pcr.GetProd();
        l2 = clone(l);
        for (ProduitM p : l2) {

            if (!p.getRemise().equals("0%")) {
                p.setPrix_prod(p.getPrix_prod() - (p.getPrix_prod() * Integer.parseInt(p.getRemise().replace("%", ""))) * 0.01);
            }
        }
        taxPerProd(10);
        taux = 0;
    }
}
