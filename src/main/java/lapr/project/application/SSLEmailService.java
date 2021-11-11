/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import lapr.project.application.EmailUtil;

/**
 *
 * @author leona
 */
public class SSLEmailService {
    
    private final String FROM_EMAIL = "lapr3.2021.g10@gmail.com";
    private final String PASSWORD = "qwerty2qwerty";
    /**
        Outgoing Mail (SMTP) Server
        requires SSL: smtp.gmail.com (use authentication)
        Use Authentication: Yes
        Port for SSL: 465
        
     * @param toEmail
     * @param subject
     * @param body
    */
    public void sendEmail(final String toEmail, final String subject, final String body){

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
        };
        Session session = Session.getDefaultInstance(props, auth);
        //EmailUtil.sendEmail(session, toEmail,"SSLEmailService Testing Subject", "SSLEmailService Testing Body");
        EmailUtil.sendEmail(session, toEmail, subject, body);
    }
}
