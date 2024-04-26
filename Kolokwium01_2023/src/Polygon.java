import java.util.ArrayList;
import java.util.List;

public class Polygon {
    List<Point> points = new ArrayList<>();

    public Polygon(List<Point> points) {
        this.points = points;
    }

    public boolean insidePoint(Point point){
        int counter = 0;
        List<Point> pointsCopy = points;
        for(int i=0; i<pointsCopy.size(); i++) {
            Point first = pointsCopy.get(i);
            Point second;
            if(i == pointsCopy.size()-1) {
                second = pointsCopy.getFirst();
            } else {
                second = pointsCopy.get(i+1);
            }
            if(first.getY() > second.getY()) {
                Point temp = first;
                first = second;
                second = temp;
            }
            if(first.getY() < point.getY() && point.getY() < second.getY()) {
                double x;
                double d = second.getX() - first.getX();
                if(d == 0) {
                    x = first.getX();
                } else {
                    double a = (second.getY() - first.getY()) / d;
                    double b = first.getY() - a * first.getX();
                    x = (point.getY() - b) / a;
                }
                if(x < point.getX()) {
                    counter++;
                }
            }
        }
        return counter % 2 != 0;
    }
}
