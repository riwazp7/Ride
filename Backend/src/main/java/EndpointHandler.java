import booking.Booking;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import spark.Request;
import spark.Response;

public class EndpointHandler {

    private final Gson gson = new Gson();
    private final DatabaseManager databaseManager;

    EndpointHandler(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    // Maybe try catch methods here.

    public Object handleCreateNewBooking(final Request request, final Response response) {
        String requestBody = request.body();
        try {
            Booking booking = gson.fromJson(requestBody, Booking.class);

            System.out.println("&&**");
            System.out.println(booking);

            return databaseManager.addBooking(booking);

        } catch (JsonSyntaxException e) {
            response.status(400);
        }
        return false;
    }

    public Object handleCheckBookingStatus(final Request request, final Response response) {
        String bookingID = request.queryParams("id");
        String bookingEmail = request.queryParams("email");
        if (Strings.isNullOrEmpty(bookingID) || Strings.isNullOrEmpty(bookingEmail)) {
            response.status(400);
            return false;
        }
        Booking booking = databaseManager.retrieveBooking(bookingID, bookingEmail);
        if (booking != null) {
            return booking;
        }
        return false;
    }
}
