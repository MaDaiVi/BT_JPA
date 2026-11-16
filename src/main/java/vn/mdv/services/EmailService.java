package vn.mdv.services;

import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

    private static Properties loadEmailConfig() throws Exception {
        Properties config = new Properties();

        InputStream input = EmailService.class
                .getClassLoader()
                .getResourceAsStream("email.properties");

        if (input == null) {
            throw new Exception("Không tìm thấy file email.properties");
        }

        config.load(input);
        input.close();

        return config;
    }

    public static void sendEmail(String to, String subject, String content)
            throws Exception {

        Properties config = loadEmailConfig();

        String username = config.getProperty("mail.username");
        String password = config.getProperty("mail.password");

        Properties props = new Properties();

        props.put("mail.smtp.host", config.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", config.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", config.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable",
                config.getProperty("mail.smtp.starttls.enable"));

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );

        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }
}