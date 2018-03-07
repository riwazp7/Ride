import booking.Booking;
import booking.BookingRequest;
import booking.BookingResponse;
import booking.BookingsUtil;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.annotation.Nullable;
import java.util.Date;

public class EndpointHandler {

    private static final Logger logger = LoggerFactory.getLogger(EndpointHandler.class.getSimpleName());

    private final Gson gson = new Gson();
    private final DatabaseManager databaseManager;
    private final CommunicationHandler communicationHandler;

    EndpointHandler(DatabaseManager databaseManager, CommunicationHandler communicationHandler) {
        this.databaseManager = databaseManager;
        this.communicationHandler = communicationHandler;
    }

    @Nullable
    public Object handleCreateNewBooking(final Request request, final Response response) {
        String requestBody = request.body();
        try {
            BookingRequest bookingRequest = gson.fromJson(requestBody, BookingRequest.class);
            if (!BookingsUtil.validateBookingRequest(bookingRequest)) {
                logger.error("Invalid booking request from client");
                return null;
            }
            Booking booking = bookingRequest.toBooking(
                    BookingsUtil.generateRandomBookingID(),
                    System.currentTimeMillis(),
                    false,
                    null);
            databaseManager.addBooking(booking);
            communicationHandler.handleBookingRequestSuccessful(booking);
            return gson.toJson(new BookingResponse(booking));
        } catch (JsonSyntaxException e) {
            logger.error("Malformed new booking request JSON from client: ", e);
            response.status(400);
        }
        return null;
    }

    @Nullable
    public Object handleCheckBookingStatus(final Request request, final Response response) {
        String bookingID = request.queryParams("id");
        String bookingEmail = request.queryParams("email");
        if (Strings.isNullOrEmpty(bookingID) || Strings.isNullOrEmpty(bookingEmail)) {
            response.status(400);
            return false;
        }
        return databaseManager.retrieveBooking(bookingID, bookingEmail);
    }
}
