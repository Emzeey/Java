import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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

            getCountryColumns(deathScanner.toString(), countryName);

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
