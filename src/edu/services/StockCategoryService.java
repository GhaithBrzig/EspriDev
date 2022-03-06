/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.dao.StockCategoryCRUD;
import edu.entities.StockCategory;
import edu.utils.JavaMailUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gayth
 */
public class StockCategoryService {

    private StockCategoryCRUD stockCategoryCRUD = new StockCategoryCRUD();

    public void addStockCategory(StockCategory c) {
        this.stockCategoryCRUD.addStockCategory(c);
    }

    public void updateStockCategory(StockCategory c) {
        this.stockCategoryCRUD.updateStockCategory(c);

    }

    public List<StockCategory> DisplayStockCategories() {
        List<StockCategory> myList = this.stockCategoryCRUD.DisplayStockCategories();
        return myList;
    }

    public void deleteStockCategory(int x) {
        this.stockCategoryCRUD.deleteStockCategory(x);
    }

}
