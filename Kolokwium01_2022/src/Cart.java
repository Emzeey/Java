import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> productsInCart = new ArrayList<>();

    public void addProduct(Product product, int amount) {
        for(int i=0; i<amount; i++) {
            productsInCart.add(product);
        }
    }

    public double getPrice(int year, int month) {
        double sum = 0;
        for(Product product : productsInCart) {
            sum += product.getPrice(year, month);
        }
        return sum;
    }

    public double getInflation(int year1, int month1, int year2, int month2) {
        double price1 = getPrice(year1, month1);
        double price2 = getPrice(year2, month2);
        double months = 12 * year2 + month2 - (12 * year1 + month1);
        return (price1 - price2) / price1 * 100 / months * 12;
    }
}
