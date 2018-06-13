import java.util.ArrayList;
import java.util.List;

public class Neurone {

    private List<Double> weights = new ArrayList<>();
    private List<Point> points;

    public Neurone(List<Point> points) {
        this.points = points;
    }

    private double function(int n) {
        double weight = weights.get(n);
        Point point = points.get(n);

        return Math.sqrt(Math.pow(weight - point.getX(), 2) + Math.pow(weight - point.getY(), 2));
    }

}
