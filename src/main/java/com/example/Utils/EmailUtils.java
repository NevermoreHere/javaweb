package com.example.Utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {
    public static Transport createConnection() throws MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "stmp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session= Session.getInstance(prop);

        Transport ts=session.getTransport();
        ts.connect("smtp.qq.com", 587, "qq", "code");
        return ts;
    }

    public static Message createMail(String fromEmail, String toEmail, String emailMsg, Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("激活");
        message.setContent(emailMsg, "text/html;charset=UTF-8");
        return message;
    }

    public void sendMessage(Message message, Transport ts) throws MessagingException {
        ts.sendMessage(message, message.getAllRecipients());
    }

}
