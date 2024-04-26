import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Point A = new Point(1.0, 1.0);
        Point B = new Point(2.0, 1.0);
        Point C = new Point(2.0, 2.0);
        Point D = new Point(1.0, 2.0);
        List<Point> list = new ArrayList<>();
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);

        Point toCheck = new Point(3.0, 1.5);
        Polygon polygon = new Polygon(list);
        System.out.println(polygon.insidePoint(toCheck));
    }
}