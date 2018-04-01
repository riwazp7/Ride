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
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class EndpointHandler {

    private static final Logger logger = LoggerFactory.getLogger(EndpointHandler.class.getSimpleName());

    private final Gson gson = new Gson();
    private final DatabaseHandler databaseHandler;
    private final CommunicationHandler communicationHandler;

    private String priceList;

    EndpointHandler(DatabaseHandler databaseHandler, CommunicationHandler communicationHandler) {
        this.databaseHandler = databaseHandler;
        this.communicationHandler = communicationHandler;
        try {
            this.priceList = readPriceList();
        } catch (Exception e) {
            logger.error("Error reading stored price list", e);
            throw new FatalException(e);
        }
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

    public boolean handleDeleteBooking(final Request request, final Response response) {
        String bookingID = request.queryParams("id");
        String email = request.queryParams("email");
        if (Strings.isNullOrEmpty(bookingID) || Strings.isNullOrEmpty(email)) {
            response.status(400);
            return false;
        }
        return databaseHandler.deleteBooking(bookingID, email);
    }

    public boolean handleUpdatePrices(final Request request, final Response response) {
        String requestBody = request.body();
        try {
            Files.write(Paths.get(Params.PRICE_LIST_FILE_PATH), requestBody.getBytes(), StandardOpenOption.WRITE);
            priceList = requestBody; // Cache price list.
            return true;
        } catch (Exception e) {
            logger.error("Error writing to prices file", e);
        }
        return false;
    }

    public Object handleGetPriceList(final Request request, final Response response) {
        return priceList;
    }

    private static String readPriceList() throws IOException {
        return new String(Files.readAllBytes(Paths.get(Params.PRICE_LIST_FILE_PATH)), Charset.defaultCharset());
    }
}
