package org.arsir;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;
import java.util.*;

public class MailReceiveClientProg {

    public static void sendEmailFromSession() {
        // Email ID of Recipient.
        String receiver = "cesar_pop@localhost";

        // Using host as smtp.gmail.com for Gmail or localhost for local
        String host = "localhost";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25"); // For TLS

        // Creating session object to get properties
        Session session = Session.getInstance(properties);

        for (int i = 0; i < 4; i++) {

            try {
                // MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From Field: adding sender's email to from field.
                message.setFrom(new InternetAddress(receiver));

                // Set To Field: adding recipient's email to from field.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

                // Set Subject: subject of the email
                message.setSubject("This is the email " + (i + 1));

                // Set body of the email.
                message.setText("Bonjour Cesar, mail " + (i + 1));

                // Send email.
                Transport.send(message);
                System.out.println("Mail successfully sent");

            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }

    public static void receive() {
        Properties props = new Properties();
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.host", "localhost");
        props.put("mail.pop3.port", "110");
        props.put("mail.debug", "true"); // Enable debugging

        Session session = Session.getInstance(props);

        try {
            Store store = session.getStore("pop3");
            store.connect("cesar_pop", "cesar_pop");

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + Arrays.toString(message.getFrom()));

                // Mark the message for deletion
                message.setFlag(Flags.Flag.DELETED, true);
            }

            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendEmailFromSession();
        receive();
    }
}
