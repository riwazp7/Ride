import jdk.internal.joptsimple.internal.Strings;
import spark.Request;
import spark.Response;

public class EndpointHandler {

    public EndpointHandler() {}

    // Maybe try catch methods here.

    public Object handleCreateNewBooking(final Request request, final Response response) {
        return true;
    }

    public Object handleCheckBookingStatus(final Request request, final Response response) {
        String bookingID = request.queryParams("id");
        String bookingEmail = request.queryParams("email");
        if (Strings.isNullOrEmpty())

        return true;
    }
}
