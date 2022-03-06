/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.dao.ReclamationCRUD;
import edu.entities.Reclamation;
import edu.utils.JavaMailUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gayth
 */
public class ReclamationService {

    private ReclamationCRUD reclamationCRUD = new ReclamationCRUD();

    public void addReclamation(Reclamation r) {
        this.reclamationCRUD.addReclamation(r);

    }

    public void updateReclamation(Reclamation r) {
        this.reclamationCRUD.updateReclamation(r);

    }

    public List<Reclamation> DisplayReclamations() {
        List<Reclamation> myList = this.reclamationCRUD.DisplayReclamations();
        return myList;
    }

    public void deleteReclamation(int x) {
        this.reclamationCRUD.deleteReclamation(x);
    }
     public void deleteReclamationD() {
        this.reclamationCRUD.deleteReclamationD();
    }

}
