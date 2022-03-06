/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.tests;

import edu.utils.MyConnection;
import edu.entities.Produit;
import edu.entities.Reclamation;
import edu.entities.StockCategory;
import edu.services.ProduitService;
import edu.services.ReclamationService;
import edu.services.StockCategoryService;
import edu.utils.ScheduledTask;
import java.util.Timer;


/**
 *
 * @author gayth
 */
public class MainCLass {

    public static void main(String[] args) throws Exception {
        Timer time = new Timer(); // Instantiate Timer Object
	ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
		time.schedule(st, 0, 1000); // Create Repetitively task for every 1 secs

		//for demo only.
		for (int i = 0; i <= 5; i++) {
			System.out.println("Execution in Main Thread...." + i);
			Thread.sleep(2000);
			if (i == 5) {
				System.out.println("Application Terminates");
				System.exit(0);
			}
		}
         

        // MyConnection mc = new MyConnection();
       
               ProduitService psv = new ProduitService();
                                  StockCategoryService csv = new StockCategoryService();
        //Produit p = new Produit(7,404,"saumon", "g", 4, "Fish", 10.7, 80);
                //Reclamation r = new Reclamation("ghaith","ghaith@gmail.com","bgbbgbg5zefze(g(gfezfeffzeerf88fefz5efef2785bgbgb", false);
                StockCategory c = new StockCategory(1,"vegetables");

       
       //PRODUIT TEST
      //psv.updateProduit(p);
       
       //for (Produit e : psv.DisplayStock()) {
           //System.out.println(e);
     // }
      
       //psv.addProduit(p);
      
      //psv.deleteProduit(5);
      
      
      
      // RECLAMATION TEST
    //rsv.addReclamation(r);
     
    // rsv.updateReclamation(r);
    
    //for (Reclamation m : rsv.DisplayReclamations()) {
          // System.out.println(m);
   //}
       //rsv.deleteReclamation(4);
       
       
        // StockCategoryTEST
     //csv.addStockCategory(c);
     
    //csv.updateStockCategory(c);
    
    //for (StockCategory s : csv.DisplayStockCategories()) {
           // System.out.println(s);
       //}
       //csv.deleteStockCategory(1);
       
    }
}
