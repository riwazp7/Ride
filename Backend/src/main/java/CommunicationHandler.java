import booking.Booking;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicationHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationHandler.class.getSimpleName());

    private static final String EMAIL_HOST = "smtp.googlemail.com";
    private static final int SMTP_PORT = 465;
    private static final String EMAIL_FROM = ""; // is this needed?
    private static final String USER_NAME = "dropwebsitetest1";
    private static final String PASSWORD = "testtest!1"; // lol

    private static final String bookingsConfirmationEmailTemplate = "%s%s";
    private static final String bookingRequestSuccessfulTemplate = "%s%s";



    public void handleBookingRequestSuccessful(Booking booking) {
        String emailContent = String.format(bookingRequestSuccessfulTemplate, booking.getBookingID()); // and more
        try {
            sendEmail("", emailContent, booking.getEmail());
        } catch (EmailException e) {
            logger.warn(
                    String.format("Sending booking confirmation email to %s failed with exception", booking.getEmail()),
                    e); // Error?
        }
    }

    public void handleBookingConfirmation(Booking booking) {
        String emailContent = String.format(bookingsConfirmationEmailTemplate, booking.getBookingID());
        try {
            sendEmail("", emailContent, booking.getEmail());
        } catch (EmailException e) {
            logger.warn(
                    String.format("Sending booking confirmation email to %s failed with exception", booking.getEmail()),
                    e); // Error?
        }
    }

    private void sendEmail(String subject, String content, String toAddress) throws EmailException {
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

    public static void main(String[] args) throws Exception {

    }

}
