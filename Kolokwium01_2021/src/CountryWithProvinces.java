import java.util.ArrayList;

public class CountryWithProvinces extends Country{
    public ArrayList<CountryWithoutProvinces> arrayOfCountries;

    public CountryWithProvinces(String name, ArrayList<CountryWithoutProvinces> arrayOfCountries) {
        super(name);
        this.arrayOfCountries = arrayOfCountries;
    }
}
