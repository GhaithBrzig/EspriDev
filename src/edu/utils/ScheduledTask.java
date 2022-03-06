/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utils;
import edu.services.ReclamationService;
import java.util.TimerTask;
import java.util.Date;
/**
 * 
 * @author Dhinakaran P.
 */
// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {

	Date now; // to display current time

	// Add your task here
       
        @Override
	public void run() {
            ReclamationService rsv = new ReclamationService();
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
                rsv.deleteReclamationD();
	}
}
