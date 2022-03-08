/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import edu.services.evenementCRUD;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<String, Double> statChart;
    @FXML
    private Button pdfID;
    public static int numeroPDF = 0;
    Document doc = new Document() {};
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenementCRUD ec = new evenementCRUD();
       statChart.setData(ec.statistique());
       statChart.setAnimated(true);
      // statChart.setCreateSymbols(false);
        statChart.setTitle("Chart");
        
       
        
        
    }    

    @FXML
    private void pdf(ActionEvent event)throws FileNotFoundException, DocumentException, BadElementException, IOException  {
        numeroPDF = numeroPDF+ 1;
        String nom = "Graph statistique " + numeroPDF + ".pdf";
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");
            //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

            WritableImage wimg = statChart.snapshot(new SnapshotParameters(), null);
            File file = new File("ChartSnapshot.png");
            ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", file);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" ));
            doc.open();
//            Image img = Image.getInstance("C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\Sanstitre.png");
            Image img = Image.getInstance("ChartSnapshot.png");
            doc.add(img);
            doc.close();
            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Desktop\\" ));
            writer.close();

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
    }

        
    }
    

