import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CountryWithoutProvinces extends Country{
    List<LocalDate> dates = new ArrayList<>();
    List<Integer> causesNumbers = new ArrayList<>();
    List<Integer> deathsNumbers = new ArrayList<>();

    public CountryWithoutProvinces(String name) {
        super(name);
    }

    public void addDailyStatistic(LocalDate date, int causes, int deaths) {
        dates.add(date);
        causesNumbers.add(causes);
        deathsNumbers.add(deaths);
    }
}
