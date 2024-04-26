import java.util.ArrayList;
import java.util.List;

public class City extends Polygon {
    private String name;
    public final Point center;

    public City(String name, Point center, double length) {
        super(center, length);
        this.name = name;
        this.center = center;
    }
}
