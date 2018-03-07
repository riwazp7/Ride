import booking.Booking;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class CommunicationHandler {

    private static final String EMAIL_HOST = "smtp.googlemail.com";
    private static final int SMTP_PORT = 465;
    private static final String EMAIL_FROM = ""; // is this needed?
    private static final String USER_NAME = "dropwebsitetest1";
    private static final String PASSWORD = "testtest!1"; // lol

    public void handleBookingConfirmation(Booking booking) {

    }

    public void handleBookingRequestSuccessful(Booking booking) {

    }

    public static void main(String[] args) throws Exception {
        Email email = new SimpleEmail();
        email.setSmtpPort(465);
        email.setHostName(EMAIL_HOST);
        email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
        email.setSSLOnConnect(true);
        email.setFrom(EMAIL_FROM);
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("rp7@williams.edu");
        email.send();
    }

}
