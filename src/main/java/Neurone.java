import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neurone {

    private List<Double> weights = new ArrayList<>();
    private List<Point> points;
    private Random random = new Random();

    public Neurone(List<Point> points) {
        this.points = points;
//        init weights
        initWeights();
    }

    private void initWeights() {
        double range_min = Double.MAX_VALUE;
        double range_max = 0;
        for (Point p : points) {
            if (p.getX() < range_min) {
                range_min = p.getX();
            }
            if (p.getX() > range_max) {
                range_max = p.getX();
            }
            if (p.getY() < range_min) {
                range_min = p.getY();
            }
            if (p.getY() > range_max) {
                range_max = p.getY();
            }
        }
        for (int i = 0; i < points.size(); i++) {
            double weight = range_min + (range_max - range_min) * random.nextDouble();
            weights.add(i, weight);
        }
    }

    private double function(int n) {
        double weight = weights.get(n);
        Point point = points.get(n);

        return Math.sqrt(Math.pow(weight - point.getX(), 2) + Math.pow(weight - point.getY(), 2));
    }

}
