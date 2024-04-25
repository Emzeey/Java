import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public abstract class Country {
    private final String name;
    private static Path confirmedCasesPath;
    private static Path deathsPath;

    public String getName() {
        return name;
    }

    public Country(String name) {
        this.name = name;
    }

    public static void setFiles(Path deaths, Path confirmedCases) {
        try {
            if (!Files.isReadable(deaths)) {
                throw new FileNotFoundException(deaths.toString());
            }
            if (!Files.isReadable(confirmedCases)) {
                throw new FileNotFoundException(confirmedCases.toString());
            }
            deathsPath = deaths;
            confirmedCasesPath = confirmedCases;
        } catch (Exception e) {

        }
    }

    public static Country fromCsv(String countryName) {
        Country country = null;
        try {
            Scanner deathScanner = new Scanner(deathsPath);
            Scanner casesScanner = new Scanner(confirmedCasesPath);

            CountryColumns information = getCountryColumns(deathScanner.toString(), countryName);
            if(information.columnCount == 1) {
                CountryWithoutProvinces CWP = new CountryWithoutProvinces(countryName);
                while(deathScanner.hasNext()) {
                    String[] deathData = deathScanner.nextLine().split(";");
                    String[] casesData = casesScanner.nextLine().split(";");
                    int cases = Integer.parseInt(casesData[information.firsColumnIndex]);
                    int deaths = Integer.parseInt(deathData[information.firsColumnIndex]);
                    LocalDate date = LocalDate.parse(deathData[0]);
                    CWP.addDailyStatistic(date, cases, deaths);
                }
                country = CWP;
            }
            else if(information.columnCount > 1) {
                ArrayList<CountryWithoutProvinces> subCountries = new ArrayList<>();
                String[] secondRow = deathScanner.nextLine().split(";");
                casesScanner.nextLine();
                for(int i=0; i<information.columnCount; i++) {
                    subCountries.add(new CountryWithoutProvinces(secondRow[information.firsColumnIndex+i]));
                }
                CountryWithProvinces CWP = new CountryWithProvinces(countryName, subCountries);
                while(deathScanner.hasNext()) {
                    String[] deathData = deathScanner.nextLine().split(";");
                    String[] casesData = casesScanner.nextLine().split(";");
                    for(int i=0; i<information.columnCount; i++) {
                        int cases = Integer.parseInt(casesData[information.firsColumnIndex+i]);
                        int deaths = Integer.parseInt(deathData[information.firsColumnIndex+i]);
                        LocalDate date = LocalDate.parse(deathData[0]);
                        CWP.arrayOfCountries.get(i).addDailyStatistic(date, cases, deaths);
                    }
                }
                country = CWP;
            }

            deathScanner.close();
            casesScanner.close();
        } catch (IOException | CountryNotFoundException e) {

        }

        return country;
    }

    private static CountryColumns getCountryColumns(String firstCsvRow, String countryToFind) throws CountryNotFoundException{
        String[] splitRow = firstCsvRow.split(";");
        int sum = 1;
        boolean firstFound = false;
        int firstFountIndex = -1;
        for(int i=0; i<splitRow.length; i++) {
            if(splitRow[i].equals(countryToFind) && !firstFound) {
                firstFound = true;
                firstFountIndex = i;
            }
            if(splitRow[i].equals(countryToFind) && firstFound) {
                sum++;
            }
        }
        if(firstFountIndex == -1) throw new CountryNotFoundException(countryToFind);
        return new CountryColumns(firstFountIndex, sum);
    }

    private static class CountryColumns {
        public final int firsColumnIndex;
        public final int columnCount;

        public CountryColumns(int firsColumnIndex, int columnCount) {
            this.firsColumnIndex = firsColumnIndex;
            this.columnCount = columnCount;
        }
    }
}
