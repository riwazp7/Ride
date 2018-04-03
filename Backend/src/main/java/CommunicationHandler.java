import booking.Booking;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommunicationHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationHandler.class.getSimpleName());

    private static final String EMAIL_HOST = "smtp.googlemail.com";
    private static final int SMTP_PORT = 465;
    private static final String EMAIL_FROM = "dropwebsitetest1@gmail.com"; // is this needed?
    private static final String USER_NAME = "dropwebsitetest1";
    private static final String PASSWORD = "testtest!1"; // lol

    private final String bookingsConfirmationEmailTemplate;
    private final String bookingRequestSuccessfulTemplate;

    public CommunicationHandler() {
        try {
            this.bookingRequestSuccessfulTemplate = readEmailTemplate("");
            this.bookingsConfirmationEmailTemplate = readEmailTemplate("");
        } catch (Exception e) {
            logger.error("Fatal error reading email template: ", e);
            throw new FatalException(e);
        }
    }

    public void handleBookingRequestSuccessful(Booking booking) {
        String emailContent = String.format(bookingRequestSuccessfulTemplate, booking.getBookingID()); // and more
        try {
            sendEmail("", emailContent, booking.getEmail());
        } catch (EmailException e) {
            logger.warn(
                    String.format(
                            "Sending booking confirmation email to %s for %s failed with exception",
                            booking.getEmail(),
                            booking.getBookingID()),
                    e); // Send email to bob to notify.
        }
    }

    public void handleBookingConfirmation(Booking booking) {
        String emailContent = String.format(bookingsConfirmationEmailTemplate, booking.getBookingID());
        try {
            sendEmail("", emailContent, booking.getEmail());
        } catch (EmailException e) {
            logger.warn(
                    String.format(
                            "Sending booking confirmation email to %s for ID %s failed with exception",
                            booking.getEmail(),
                            booking.getBookingID()),
                    e); // Error?
        }
    }

    public static void sendEmail(String subject, String content, String toAddress) throws EmailException {
        Email email = new SimpleEmail();
        email.setSmtpPort(SMTP_PORT);
        email.setHostName(EMAIL_HOST);
        email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
        email.setSSLOnConnect(true);
        email.setFrom(EMAIL_FROM);
        email.setSubject(subject);
        email.setMsg(content);
        email.addTo(toAddress);
        email.send();
    }

    private static String readEmailTemplate(String templateName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(templateName)), Charset.defaultCharset());
    }

    public static void main(String[] args) {

    }

}
