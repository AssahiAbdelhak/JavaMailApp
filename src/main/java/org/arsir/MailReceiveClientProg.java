package org.arsir;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Arrays;
import java.util.Properties;

public class MailReceiveClientProg {

    public static void receive() {
        Properties props = new Properties();
        props.put("mail.store.protocol", "pop3s");
        props.put("mail.pop3s.host", "pop.gmail.com");
        props.put("mail.pop3s.port", "995");
        props.put("mail.pop3s.ssl.enable", "true");
        props.put("mail.debug", "true"); // Enable debugging

        Session session = Session.getInstance(props);

        try {
            Store store = session.getStore("pop3s");
            store.connect("assahiabdelhak@gmail.com", "ziqq nlgn rcbl ugrd"); // Use App Password here

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);


            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + Arrays.toString(message.getFrom()));
            }

            /*Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Body: " + message.getContent());
            }*/

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        receive();
    }
}
