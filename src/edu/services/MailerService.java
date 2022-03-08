package edu.services;

public class MailerService {
    public void replyMail(String mail ,String Username , String Description) {
        String from = "oumaima.barika@esprit.tn";
        String pass = "oumaouma123";
        String[] to = {"" + mail}; // list of recipient email addresses
        String subject = "Passage Du Commande";
        String body = Description;
        MailService serv = new MailService();
        MailService.sendFromGMail(from,pass,to,subject,body);
        
    }
}
