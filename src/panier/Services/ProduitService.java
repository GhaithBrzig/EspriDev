/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Services;

/**
 *
 * @author broum
 */
public class ProduitService {

//    private static final String REQUETE_SELECT = "select * from produit";
//    private static final String REQUETTE_SELECT_BYID = "select * from produit where id=?";
//
//    public ArrayList<Produit> afficherProduits() {
//        ArrayList<Produit> list = new ArrayList();
//        try {
//            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETE_SELECT);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                Produit c = new Produit();
//                c.setId(rs.getInt("id_prod"));
//                c.setPrix(rs.getDouble("prix"));
//                c.setQuantite(rs.getInt("quantite"));
//                c.setNom(rs.getString("lib_prod"));
//                c.setDescription(rs.getString("description"));
//                c.setImage(rs.getString("image"));
//                list.add(c);
//            }
//            pst.close();
//            rs.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            System.out.println("Erreur");
//        }
//        return list;
//    }
//
//    public Produit produitLoadData(int id) {
//        Produit c = new Produit();
//        try {
//            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETTE_SELECT_BYID);
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                c.setId(rs.getInt("id"));
//                
//                c.setPrix(rs.getDouble("prix"));
//                c.setQuantite(rs.getInt("quantite"));
//                c.setNom(rs.getString("nom"));
//                c.setDescription("Salade Cesar");
//                c.setImage(rs.getString("Image"));
//                
//                return c;
//            }
//            pst.close();
//            rs.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            System.out.println("Erreur");
//        }
//        return c;
//    }

}
