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

public class EndpointHandler {

    private static final Logger logger = LoggerFactory.getLogger(EndpointHandler.class.getSimpleName());

    private final Gson gson = new Gson();
    private final DatabaseHandler databaseHandler;
    private final CommunicationHandler communicationHandler;

    EndpointHandler(DatabaseHandler databaseHandler, CommunicationHandler communicationHandler) {
        this.databaseHandler = databaseHandler;
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
                    System.currentTimeMillis() + "",
                    false,
                    null);
            databaseHandler.addBooking(booking);
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
        return databaseHandler.retrieveBooking(bookingID, bookingEmail);
    }

    @Nullable
    public Object handleConfirmBooking(final Request request, final Response response) {
        String bookingID = request.queryParams("id");
        String email = request.queryParams("email");
        if (Strings.isNullOrEmpty(bookingID) || Strings.isNullOrEmpty(email)) {
            response.status(400);
            return false;
        }
        Booking booking = databaseHandler.confirmBooking(bookingID, email);
        if (booking == null) {
            return false;
        }
        // More verification needed here? that the booking was actually confirmed.
        communicationHandler.handleBookingConfirmation(booking);
        return true;
    }

    public Object handleGetAllBookings(final Request request, final Response response) {
        return gson.toJson(databaseHandler.retrieveAll());
    }

    public boolean handleForceAddBooking(final Request request, final Response response) {
        String requestBody = request.body();
        try {
            Booking booking = gson.fromJson(requestBody, Booking.class);
            if (!BookingsUtil.validateBooking(booking)) {
                logger.error("INVALID FORCE ADD BOOKING from client");
                return false;
            }
            databaseHandler.addBooking(booking);
            return true;
        } catch (JsonSyntaxException e) {
            logger.error("Malformed new booking request JSON from client: ", e);
            response.status(400);
        }
        return false;
    }
}
