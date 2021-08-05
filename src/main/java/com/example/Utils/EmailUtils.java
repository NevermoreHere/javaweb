package com.example.Utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {
    private Session session;
    private Transport transport;

    public EmailUtils() throws MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.office365.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        Session session= Session.getInstance(prop);

        Transport ts=session.getTransport();
        ts.connect("smtp.office365.com", 587, "xulide123@outlook.com", "xld353142927");
        this.session = session;
        this.transport = ts;
    }

    public Message createMail(String fromEmail, String toEmail, String emailMsg) throws MessagingException {
        MimeMessage message = new MimeMessage(this.session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("激活");
        message.setContent(emailMsg, "text/html;charset=UTF-8");
        return message;
    }

    public void sendMessage(Message message) throws MessagingException {
        this.transport.sendMessage(message, message.getAllRecipients());
    }

    public static void main(String[] args) throws MessagingException {
        EmailUtils emailUtils = new EmailUtils();
        Message msg = emailUtils.createMail("xulide123@outlook.com", "576847219@qq.com", "hello");
        emailUtils.sendMessage(msg);
    }
}
