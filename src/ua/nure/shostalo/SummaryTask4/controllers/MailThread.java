package ua.nure.shostalo.SummaryTask4.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * The thread for sends email.
 * 
 * @author Mikhail Shostalo
 *
 */
public class MailThread extends Thread {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MailThread.class);

    /**
     * The object {@link MimeMessage}.
     */
    private MimeMessage message;

    /**
     * The email for sending.
     */
    private String sendToEmail;

    /**
     * The text of email.
     */
    private String mailText;

    /**
     * The proporties.
     */
    private Properties properties;

    /**
     * Create object {@link MailThread}.
     * 
     * @param sendToEmail
     *            the email
     * @param mailText
     *            the text of email
     * @param properties
     *            the proporties
     */
    public MailThread(String sendToEmail, String mailText,
	    Properties properties) {
	this.sendToEmail = sendToEmail;
	this.mailText = mailText;
	this.properties = properties;
    }

    /**
     * The method initalization java mail api.
     */
    public void init() {
	Session mailSession = (new SessionCreator(properties)).createSession();
	mailSession.setDebug(true);
	message = new MimeMessage(mailSession);
	try {
	    message.setSubject("Our order");
	    message.setContent(mailText, "text/plain; charset=\"UTF-8\"");
	    message.setRecipient(Message.RecipientType.TO,
		    new InternetAddress(sendToEmail));
	} catch (AddressException e) {
	    LOGGER.error(e);

	} catch (MessagingException e) {
	    LOGGER.error(e);
	}
    }
    
    @Override
    public void run() {
	init();
	try {
	    Transport.send(message);
	} catch (MessagingException e) {
	    LOGGER.error(e);
	}
    }

}
