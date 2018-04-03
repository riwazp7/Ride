package booking;

import java.util.List;

/**
 * A POJO specification of the prices list JSON format. Has a single list of arrays of string of length 3.
 * Strings are from, to, and the trip price in that order.
 *
 */
public class PricesList {
    private final List<String[]> priceList;

    public PricesList(List<String[]> priceList) {
        this.priceList = priceList;
    }

    public List<String[]> getPriceList() {
        return priceList;
    }
}
