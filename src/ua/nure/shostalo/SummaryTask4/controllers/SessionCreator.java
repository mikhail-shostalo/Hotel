package ua.nure.shostalo.SummaryTask4.controllers;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * The class creates session for java mail api.
 * 
 * @author Mikhail Shostalo
 *
 */
public class SessionCreator {

    /**
     * The host.
     */
    private String smtpHost;

    /**
     * The port.
     */
    private String smtpPort;

    /**
     * The user email.
     */
    private String userName;

    /**
     * The user password.
     */
    private String userPassword;

    /**
     * The properties of session.
     */
    private Properties sessionProperties;

    /**
     * Creates object {@link SessionCreator}.
     * 
     * @param configProperties
     *            the properties of session
     */
    public SessionCreator(Properties configProperties) {
	smtpHost = configProperties.getProperty("mail.smtp.host");
	smtpPort = configProperties.getProperty("mail.smtp.port");
	userName = configProperties.getProperty("mail.user.name");
	userPassword = configProperties.getProperty("mail.user.password");
	sessionProperties = new Properties();
	sessionProperties.setProperty("mail.transport.protocol", "smtp");
	sessionProperties.setProperty("mail.host", smtpHost);
	sessionProperties.put("mail.smtp.auth", "true");
	sessionProperties.put("mail.smtp.port", smtpPort);
	sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
	sessionProperties.put("mail.smtp.socketFactory.class",
		"javax.net.ssl.SSLSocketFactory");
	sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
	sessionProperties.setProperty("mail.smtp.quitwait", "false");
    }

    /**
     * Creates session.
     * 
     * @return new session
     */
    public Session createSession() {

	return Session.getDefaultInstance(sessionProperties,
		new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName,
				userPassword);
		    }
		});
    }
}
