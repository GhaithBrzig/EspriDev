///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pidevo;
//
//import Entity.Commande;
//import Entity.Panier;
//import Entity.Produit;
//import Entity.Utilisateur;
//import Services.PanierService;
//import Services.CommandeService;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Scanner;
//
///**
// *
// * @author broum
// */
//public class PIDEVO {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//        int choix = menu();
//        while (choix < 1 || choix > 9) {
//            choix = menu();
//        }
//        switch (choix) {
//            case 1:
//                ajouter();
//                break;
//            case 2:
//                List<Panier> list = afficher(3);
//                 int sommeTotal = 0;
//                if (list.isEmpty()) {
//                    System.out.println("Panier est vide!");
//                } else {
//                    ListIterator<Panier> iterator = list.listIterator();
//                    while (iterator.hasNext()) {
//                        System.out.println(iterator.next());
//                    }
//                }
//                break;
//            case 3:
//                List<Panier> list2 = afficher(1);
//                if (list2.isEmpty()) {
//                    System.out.println("Panier est vide!");
//                } else {
//                    ListIterator<Panier> iterator2 = list2.listIterator();
//                    while (iterator2.hasNext()) {
//                        System.out.println(iterator2.next());
//                    }
//                    System.out.print("Choisir Panier a supprimer");
//                    Scanner scan = new Scanner(System.in);
//                    int id = scan.nextInt();
//                    boolean test = supprimerPanier(id);
//                    if (test) {
//                        List<Panier> list3 = afficher(1);
//                        if (list3.isEmpty()) {
//                            System.out.println("Panier est vide!");
//                        } else {
//                            ListIterator<Panier> iterator3 = list3.listIterator();
//                            while (iterator3.hasNext()) {
//                                System.out.println(iterator3.next());
//                            }
//                        }
//                        System.out.println("Panier Supprimer!");
//                    } else {
//                        System.out.println("Panier Introuvable!");
//                    }
//                }
//                break;
//            case 4:
//                List<Panier> lis = afficher(1);
//                if (lis.isEmpty()) {
//                    System.out.println("Panier est vide!");
//                } else {
//                    ListIterator<Panier> it = lis.listIterator();
//                    while (it.hasNext()) {
//                        System.out.println(it.next());
//                    }
//                    System.out.print("Donner l'ID du Panier:");
//                    Scanner lastsc = new Scanner(System.in);
//                    int id_panier = lastsc.nextInt();
//                    PanierService pa = new PanierService();
//                    Panier panier = pa.getData(id_panier);
//                    int total = pa.getQuantiteP(id_panier);
//                    System.out.println("MAX QUANTITE : "+total);
//                    System.out.print("Donner la nouvelle quantite:");
//                    Scanner lastsc2 = new Scanner(System.in);
//                    int Quantite = lastsc2.nextInt();
//                    while (Quantite > total) {
//                        System.out.println("Quantite Insuffisante");
//                        System.out.print("Re entrer Quantite: ");
//                        Scanner input2 = new Scanner(System.in);
//                        Quantite = input2.nextInt();
//                    }
//                    boolean test = modifierPanier(panier, Quantite);
//                    if (test) {
//                        List<Panier> list3 = afficher(1);
//                        if (list3.isEmpty()) {
//                            System.out.println("Panier est vide!");
//                        } else {
//                            ListIterator<Panier> iterator3 = list3.listIterator();
//                            while (iterator3.hasNext()) {
//                                System.out.println(iterator3.next());
//
//                            }
//                            System.out.println("Panier Modifier !");
//
//                        }
//                    } else {
//                        System.out.println("Erreur");
//                    }
//                }
//
//                break;
//            case 5:
//                List<Commande> listC = afficherCommande(1);
//                if (listC.isEmpty()) {
//                    System.out.println("Pas de Commandes!");
//                } else {
//                    ListIterator<Commande> iteratorCommande = listC.listIterator();
//                    while (iteratorCommande.hasNext()) {
//                        System.out.println(iteratorCommande.next());
//                    }
//                }
//                break;
//            case 6:
//                Utilisateur user = new Utilisateur(3, "Oumayma");
//                CommandeService cs = new CommandeService();
//                cs.ajouterCommande(user);
//                break;
//                
//            case 7:
//                List<Commande> listCo = afficherCommande(1);
//                if (listCo.isEmpty()) {
//                    System.out.println("Pas de Commandes");
//                } else {
//                    ListIterator<Commande> iterator2 = listCo.listIterator();
//                    while (iterator2.hasNext()) {
//                        System.out.println(iterator2.next());
//                    }
//                    System.out.print("Choisir Commande a supprimer");
//                    Scanner scan = new Scanner(System.in);
//                    int id = scan.nextInt();
//                    boolean test = supprimerCommande(id);
//                    if (test) {
//                        List<Commande> list3 = afficherCommande(1);
//                        if (list3.isEmpty()) {
//                            System.out.println("Pas de commandes!");
//                        } else {
//                            ListIterator<Commande> iterator3 = list3.listIterator();
//                            while (iterator3.hasNext()) {
//                                System.out.println(iterator3.next());
//                            }
//                        }
//                        System.out.println("Commande Supprimer!");
//                    } else {
//                        System.out.println("Commande Introuvable!");
//                    }
//                }
//                break;
//            case 8:
//                List<Commande> listCommande = afficherCommande(1);
//                if (listCommande.isEmpty()) {
//                    System.out.println("Pas de commandes!");
//                } else {
//                    ListIterator<Commande> it = listCommande.listIterator();
//                    while (it.hasNext()) {
//                        System.out.println(it.next());
//                    }
//                    System.out.print("Donner l'ID du Commande:");
//                    Scanner lastsc = new Scanner(System.in);
//                    int id_commande = lastsc.nextInt();
//                    Commande cm = new Commande(id_commande);
//                    CommandeService comsrv = new CommandeService();
//                 
//                    boolean test = modifierCommande(cm);
//                    if (test) {
//                        List<Commande> list3 = afficherCommande(1);
//                        if (list3.isEmpty()) {
//                            System.out.println("Pas de commandes!");
//                        } else {
//                            ListIterator<Commande> iterator3 = list3.listIterator();
//                            while (iterator3.hasNext()) {
//                                System.out.println(iterator3.next());
//                            }
//                            System.out.println("Commande Modifier !");
//                        }
//                    } else {
//                        System.out.println("Erreur");
//                    }
//                }
//                case 9:
//                    
//                    miseAjoursFidelete();
////                    miseAjoursFidelete(int id);
//                break;
//
//        }
//
//    }
//
//    public static int menu() {
//        int choix = 0;
//        System.out.println("---------Choisir Option--------------");
//        System.out.println("1: Ajouter Produit au Panier");
//        System.out.println("2: Afficher Panier");
//        System.out.println("3: Supprimer Produit du Panier");
//        System.out.println("4: Modifier Quantite d'un Produit au Panier");
//        System.out.println("5: Afficher Commande");
//        System.out.println("6: Ajouter Commandes");
//        System.out.println("7: Supprimer Commandes ");
//        System.out.println("8: Modifier Commande");
//        System.out.println("9: Points Fidelité");
//
//        System.out.println("--------------------------------------");
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Votre choix : ");
//        choix = sc.nextInt();
//        return choix;
//    }
//
//    public static void ajouter() {
//        Utilisateur user = new Utilisateur(1, "omayma");
//        Produit produit = new Produit(2, 100, 100);
//        System.out.print("Entrer Quantite: ");
//        Scanner input = new Scanner(System.in);
//        int quantite = input.nextInt();
//        PanierService PS = new PanierService();
//        while (quantite > produit.getQuantite()) {
//            System.out.println("Quantite Insuffisante");
//            System.out.print("Re entrer Quantite: ");
//            Scanner input2 = new Scanner(System.in);
//            quantite = input2.nextInt();
//            
//
//        }
//        if (PS.ajouterPanier(produit, user, quantite)) {
//            System.out.println("Insertion effecté avec succes");
//
//        } else {
//            System.out.println("Pas d'insertion");
//        }
//
//    }
//
//    public static List<Panier> afficher(int id_user) {
//        PanierService p = new PanierService();
//        List<Panier> Paniers = p.afficherPanier(id_user);
//        return Paniers;
//    }
//
//    public static List<Commande> afficherCommande(int id_user) {
//        CommandeService cs = new CommandeService();
//        List<Commande> Commandes = cs.afficherCommande(id_user);
//        return Commandes;
//    }
//
//    public static boolean supprimerPanier(int pr) {
//        PanierService p = new PanierService();
//        return p.supprimerPanier(pr);
//    }
//
//    public static boolean supprimerCommande(int id) {
//        CommandeService c = new CommandeService();
//        return c.supprimerCommande(id);
//    }
//
//    public static boolean modifierPanier(Panier panier, int Quantite) {
//        PanierService pa = new PanierService();
//        return pa.modifierPanier(panier, Quantite);
//    }
//
//    public static boolean modifierCommande(Commande c) {
//        CommandeService cs = new CommandeService();
//        return cs.modifierCommande(c);
//    }
//     public static void miseAjoursFidelete(){
//         CommandeService cs = new CommandeService();
//         cs.ajouter_points_fidelite();  
//     }
//}
//
