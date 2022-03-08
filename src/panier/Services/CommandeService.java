/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Services;

import panier.Entity.Commande;
import panier.Entity.Panier;
import panier.Entity.Produit;
import panier.Entity.Utilisateur;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import panier.Util.MyConnection;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 *
 * @author broum
 */
public class CommandeService {
    
    private static final String REQUETE_SELECT = "select * from commande where (id_user = ? and id != 0) order by id";
    private static final String REQUETE_INSERT = "INSERT INTO commande (id_user,etat_cmd) VALUES(?,'0') ";
    private static final String REQUETE_DELETE = "DELETE FROM commande WHERE id = ?";
    private static final String REQUETE_UPDATE = "UPDATE commande SET etat_cmd = '1' where id = ? ";
    private static final String UPDATE_ALL_FIDELETE = "select count(*)*50 , pf.id_user from points_fidelite as pf inner join commande as c where pf.id_user=c.id_user and c.etat_cmd = 1 group by  pf.id_user";
    private static final String UPDATE_FIDELETE = "UPDATE POINTS_FIDELITE  set nombre_points = ((select count(*)   from commande inner join points_fidelete where commande.id_user =points_fidelete and etat_cmd = 1)*50)";
    private static final String VALIDER_PANIER = "UPDATE PANIER SET id_commande = ? where (id_user= ? and id_commande =0 ) ";
    private static final String LAST_ID = "SELECT ID FROM commande order by id desc limit 1";
    private static final String LAST_COMMAND = "SELECT * FROM commande where id_user = ?  order by id desc limit 1";
    private static final String GET_PANIERS = "SELECT * FROM PANIER WHERE ID_COMMANDE = ? ";
    public boolean ajouterCommande(Utilisateur u) {
        
        Commande c = new Commande();
        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_INSERT);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("Commande Ajouté! ");
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(LAST_ID);
            while (rs.next()) {
                c.setId(rs.getInt("id"));
            }
            PreparedStatement pst2 = new MyConnection().getCnx().prepareStatement(VALIDER_PANIER);
            pst2.setInt(1, c.getId());
            pst2.setInt(2, u.getId());
            pst2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }
    }

    public boolean supprimerCommande(int id) {

        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_DELETE);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            System.out.println("Commande supprimé!");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Echec de suppression ");
            return false;
        }
    }

    public ArrayList<Commande> afficherCommande(int id_user) {
        ArrayList<Commande> list = new ArrayList();
        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_SELECT);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande c = new Commande();
                c.setId_user(id_user);
                c.setId(rs.getInt("id"));
                if (rs.getInt("etat_cmd") == 0) {
                    c.setEtat_cmd(false);
                } else {
                    c.setEtat_cmd(true);
                }
                c.setDate_cmd(rs.getDate("date_cmd"));
                list.add(c);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
       // System.out.println(list);
        return list;
    }

      public ArrayList<Panier> getPaniers(int id_commande) {
        ArrayList<Panier> list = new ArrayList();
        ProduitService ps = new ProduitService() ;
        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(GET_PANIERS);
            pst.setInt(1, id_commande);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Panier c = new Panier();
                
                c.setId(rs.getInt("id"));
                c.setId_user(rs.getInt("id_user"));
                c.setId_commande(id_commande);
                c.setId_produit(rs.getInt("id_produit"));
                c.setQuantite(rs.getInt("Quantite"));
                c.setProduit();
                list.add(c);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return list;
    }
    public boolean modifierCommande(Commande c) {
        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_UPDATE);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Commande Validé!! ");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }

    }

    public void ajouter_points_fidelite() {
        try {
            Statement stmt = new MyConnection().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery(UPDATE_ALL_FIDELETE);
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement("update points_fidelite set nombre_points = ? where id_user = ? ");
            while (rs.next()) {
                double Points = rs.getDouble("count(*)*50");
                int id = rs.getInt("id_user");
                System.out.println("Utilisateur id: "+id+" à  "+Points+" de fidelité");
                pst.setDouble(1,Points);
                pst.setInt(2,id);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
public Commande commandeData(int id) {
    Commande c = new Commande();
    return c;
    
}
public boolean genererQR(ArrayList<Panier> u,Commande c) throws IOException{
 
            String code = "QRCODE";
            String data = "Commande Id : "+ c.getId()+"\n ";
            String details="";
            int total = 0;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            for (Panier e : u){
                
                e.setProduit();
                Produit prods = e.getProduit();
                details = details+ "\n Panier id : "+e.getId()+": \n"+
                        "Produit : "+prods.getLib_prod()+" Quantite : "+e.getQuantite()+" Total :"+e.getQuantite()*prods.getPrix_prod()+"DT";
                total = (int) (total + (e.getQuantite()*prods.getPrix_prod()));
                
            }
            String qrdata = data+details+"\n Total de commande : "+total+"DT";
            int width = 500;
            int height = 500;
            BufferedImage bufferedImage = null;
            try {
                BitMatrix byteMatrix = qrCodeWriter.encode(qrdata, BarcodeFormat.QR_CODE, width, height);
                bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bufferedImage.createGraphics();

                Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
                image.setColor(Color.WHITE);
                image.fillRect(0, 0, width, height);
                image.setColor(Color.BLACK);

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (byteMatrix.get(i, j)) {
                            image.fillRect(i, j, 1, 1);
                        }
                    }
                }
                if (ImageIO.write(bufferedImage, "png", new File("C:\\Users\\gayth\\" + code+c.getId() + ".png"))) {
                    System.out.println("-- saved");
                }
                  Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Commande validé et email envoyé!");
        tray.setMessage("Merci pour votre confiance!!");
        tray.setNotification(notification);
        tray.showAndDismiss(Duration.seconds(6));
                System.out.println("QR created successfully....");
                return true;

            } catch (WriterException ex) {
                System.out.println(ex);
                return false;
            } catch (IOException ex) {
                System.out.println(ex);
                return false;
            }
}


}

