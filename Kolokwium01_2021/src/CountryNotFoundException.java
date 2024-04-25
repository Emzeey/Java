public class CountryNotFoundException extends Exception {
    private final String countryNotFound;

    public CountryNotFoundException(String message) {
        super(message);
        countryNotFound = message;
    }
    @Override
    public String getMessage() {
        return countryNotFound;
    }
}
