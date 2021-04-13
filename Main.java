package mailSender;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Main {

    public static void main(String[] args) {

        final String username = "ur email";
        final String password = "ur password";
        final String recipient = "recipient email";
        final String subjectMessage = "Subject Message";
        final String textMessage = "Message";
        final int threadCount = 10;

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient));
            message.setSubject(subjectMessage);
            message.setText(textMessage);
            ThreadManager manager = new ThreadManager();
            manager.createThreads(threadCount, message);
            manager.startThreads();
            while(true)
            {
            	Transport.send(message);
            	System.out.println("Done");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}