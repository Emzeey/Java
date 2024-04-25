import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
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

            deathScanner.close();
            casesScanner.close();
        } catch (Exception e) {

        }

        return country;
    }
}
