import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void main(String[] args) {
        // Email ID of Recipient.
        String recipient = "bdalhqa89@gmail.com"; // Change to your recipient's email

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
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding sender's email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("This is Subject");

            // Set body of the email.
            message.setText("This is a test mail");

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
