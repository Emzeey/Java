import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class FoodProduct extends Product{
    Double[][] prices;
    private static String[] provinces;

    public FoodProduct(String name, Double[][] prices) {
        super(name);
        this.prices = prices;
    }

    public static String[] getProvinces(Path path){
        if(provinces == null) {
            try {
                String[] prov = new String[16];
                Scanner scanner = new Scanner(path);
                scanner.nextLine();
                scanner.nextLine();
                int i = 0;
                while (scanner.hasNext()) {
                    prov[i++] = scanner.nextLine().split(";")[0];
                }
                provinces = prov;
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return provinces;
    }

    public static FoodProduct fromCsv(Path path) {
        String name;
        Double[][] prices = new Double[16][];
        getProvinces(path);

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();
            scanner.nextLine();

            int i = 0;
            while(scanner.hasNext()) {
                prices[i++] = Arrays.stream(scanner.nextLine().split(";"))
                        .skip(1)
                        .map(value -> value.replace(",","."))
                        .map(Double::valueOf)
                        .toArray(Double[]::new);
            }
            scanner.close();

            return new FoodProduct(name, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public double getPrice(int year, int month) {
        double retVal = 0;
        try {
            if(month < 1 || month > 12) {
                throw new IndexOutOfBoundsException();
            }
            if(year < 2010 || year > 2022 || (year == 2022 && month > 3)) {
                throw new IndexOutOfBoundsException();
            }

            for (Double[] price : prices) {
                retVal += price[12 * (year - 2010) + month - 1];
            }
        }
        catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
        return retVal / prices.length;
    }

    public double getPrice(int year, int month, String province) {
        double retVal = -1;
        try {
            boolean validProvince = false;
            for(String prov : provinces) {
                if (prov.equals(province)) {
                    validProvince = true;
                    break;
                }
            }
            if(!validProvince) {
                throw new IndexOutOfBoundsException();
            }
            if(month < 1 || month > 12) {
                throw new IndexOutOfBoundsException();
            }
            if(year < 2010 || year > 2022 || (year == 2022 && month > 3)) {
                throw new IndexOutOfBoundsException();
            }

            for(int i=0; i<provinces.length; i++) {
                if(provinces[i].equals(province)) {
                    retVal = prices[i][12 * (year - 2010) + month - 1];
                    break;
                }
            }
        }
        catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
        return retVal;
    }
}
