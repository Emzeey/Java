import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Product.addProducts(FoodProduct::fromCsv, Paths.get("data/food"));
        Product.addProducts(NonFoodProduct::fromCsv, Paths.get("data/nonfood"));

        Cart myCart = new Cart();
        myCart.addProduct(Product.getProducts("Gar"), 1);
        myCart.addProduct(Product.getProducts("Benz"), 1);
        System.out.println(myCart.getPrice(2010, 1));
        System.out.println(myCart.getPrice(2010, 2));
        System.out.println(myCart.getPrice(2010, 3));
        System.out.println(myCart.getPrice(2010, 4));
        System.out.println(myCart.getPrice(2010, 5));
        System.out.println(myCart.getInflation(2010, 1, 2010, 3));
    }
}