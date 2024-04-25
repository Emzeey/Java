
public class CountryWithProvinces extends Country{
    private Country[] arrayOfCountries;

    public CountryWithProvinces(String name, Country[] arrayOfCountries) {
        super(name);
        this.arrayOfCountries = arrayOfCountries;
    }
}
