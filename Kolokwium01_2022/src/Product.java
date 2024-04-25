import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class Product {
    private final String  name;
    public static List<Product> listOfProducts = new ArrayList<>();

    public static void clearProducts() {
        listOfProducts.clear();
    }

    public static void addProducts(Function<Path, Product> fromCsvFunction, Path directoryPath) {
        try (Stream<Path> stream = Files.walk(directoryPath)) {
            stream.skip(1).forEach(s -> listOfProducts.add(fromCsvFunction.apply(s)));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static Product getProducts(String prefix) {
        List<Product> retList = new ArrayList<>();
        try {
            for(Product product : listOfProducts) {
                if(product.getName().startsWith(prefix)) retList.add(product);
            }
            if (retList.isEmpty()) {
                throw new IndexOutOfBoundsException("No index found");
            }
            if (retList.size() > 1) {
                List<String> listOfNames = retList.stream().map(Product::getName).toList();
                throw new AmbigiousProductException(listOfNames);
            }
        } catch (IndexOutOfBoundsException | AmbigiousProductException e) {
            System.out.println(e.getMessage());
        }
        return retList.getFirst();
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice(int year, int month);
}
