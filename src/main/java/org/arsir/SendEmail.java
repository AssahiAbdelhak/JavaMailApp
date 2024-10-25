import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static <recepient> void main(String[] args) {
        // Email ID of Recipient.
        HashMap<String, String> recipients = new HashMap<>();
        recipients.put("Abdelhak", "bdalhqa89@gmail.com");
        recipients.put("Adam", "adam.kharfi01@gmail.com");
        recipients.put("Alexandre", "alexandre.le1908@gmail.com");

        // Email ID of Sender.
        String sender = "assahiabdelhak@gmail.com"; // Change to your email

        // Using host as smtp.gmail.com for Gmail
        String host = "smtp.gmail.com";
        String username = "assahiabdelhak@gmail.com"; // Your Gmail address
        String password = "ziqq nlgn rcbl ugrd"; // Your Gmail password or app password

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587"); // For TLS
        properties.setProperty("mail.smtp.auth", "true"); // Enable authentication
        properties.setProperty("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Creating session object to get properties
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            for (String key : recipients.keySet()){
                // MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From Field: adding sender's email to from field.
                message.setFrom(new InternetAddress(recipients.get(key)));

                // Set To Field: adding recipient's email to from field.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients.get(key)));

                // Set Subject: subject of the email
                message.setSubject("This is Subject");

                // Set body of the email.
                message.setText("Bonjour "+key);

                // Send email.
                Transport.send(message);
                System.out.println("Mail successfully sent");
            }

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
