package edu.epam.web.utility;

import edu.epam.web.exception.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static edu.epam.web.utility.PropertiesPath.MAIL_PROPERTIES;

public class MailSenderUtil {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(MailSenderUtil.class);
    /**
     * The constant user name.
     */
    private static final String USER_NAME_PROPERTIES = "mail.from";
    /**
     * The constant user password.
     */
    private static final String USER_PASSWORD_PROPERTIES = "mail.smtp.password";
    /**
     * The constant message subject.
     */
    private static final String MESSAGE_SUBJECT = "Thank you for registering";
    /**
     * The constant message text.
     */
    private static final String MESSAGE_TEXT = "Thank you for registering.";
    /**
     * The constant message link.
     */
    private static final String MESSAGE_LINK= " Click on the link to activate the account." +
            " http://localhost:8080/EpamSimpleWeb/Home?action=activateAccount&email=";
    /**
     * The constant message text forgot password.
     */
    private static final String MESSAGE_TEXT_FORGOT_PASSWORD = "Your password - ";
    /**
     * The constant message subject forgot password.
     */
    private static final String MESSAGE_SUBJECT_FORGOT_PASSWORD = "Forgot password ";
    /**
     * The constant message subject password.
     */
    private static final String MESSAGE_SUBJECT_PASSWORD = "Your new password ";
    /**
     * The constant message text forgot password.
     */
    private static final String MESSAGE_TEXT_PASSWORD = "Your password - ";
    /**
     * The user name.
     */
    private static String username;
    /**
     * The password.
     */
    private static String password;
    /**
     * The properties.
     */
    private static Properties properties;

    private MailSenderUtil() {
    }

    static  {
        properties = new Properties();
        try {
            ClassLoader classLoader = MailSenderUtil.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(MAIL_PROPERTIES);
            properties.load(resourceAsStream);
            username = properties.getProperty(USER_NAME_PROPERTIES);
            password = properties.getProperty(USER_PASSWORD_PROPERTIES);
        } catch (IOException e) {
            logger.error("Error uploading a file", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Send message.
     *
     * @param email   the email
     * @param name    the name
     * @param surname the surname
     * @throws EmailException the email exception
     */
    public static void sendMessage(String email, String name, String surname) throws EmailException {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(properties, authenticator);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(MESSAGE_SUBJECT);
            message.setText(MESSAGE_TEXT + name + " " + surname+MESSAGE_LINK+email);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error generating or sending message: " + e);
            throw new EmailException(e);
        }
    }

    /**
     * Send password.
     *
     * @param email        the email
     * @param userPassword the password
     * @throws EmailException the email exception
     */
    public static void sendNewPassword(String email, String userPassword) throws EmailException {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(properties, authenticator);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(MESSAGE_SUBJECT_FORGOT_PASSWORD);
            message.setText(MESSAGE_TEXT_FORGOT_PASSWORD + userPassword);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error generating or sending message: " + e);
            throw new EmailException(e);
        }
    }
}
