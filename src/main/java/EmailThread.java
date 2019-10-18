
import org.xml.sax.SAXException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Properties;

public class EmailThread extends Thread
{
    String to;
    String subject;
    String core;

    public EmailThread(String to, String subject, String core) {
        this.to = to;
        this.subject = subject;
        this.core = core;
    }

    @Override
    public void run()
    {
        String username=null;
        String password=null;
        try {
            username = Utils.getSettings("Email");
            password = Utils.getSettings("Password");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp-mail.outlook.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        String finalUsername = username;
        String finalPassword = password;
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(finalUsername, finalPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(core);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        /*System.out.println(to);
        System.out.println(subject);
        System.out.println(core);*/
    }
}
