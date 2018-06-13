import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neurone {

    private List<Weight> weights = new ArrayList<>();
    private List<Point> points;

    public Neurone(List<Point> points) {
        this.points = points;
//        init weights
        initWeights();
    }

    private void initWeights() {
        for (int i = 0; i < points.size(); i++) {
            weights.add(i, new Weight(0, 1));
        }
    }

    private double function(int n) {
        Weight weight = weights.get(n);
        Point point = points.get(n);

        return 1 / Math.sqrt(Math.pow(weight.getX() - point.getX(), 2) + Math.pow(weight.getY() - point.getY(), 2));
    }

    public Point getOutput() {
        Point p_max = null;
        double max_response = 0;
        for (Point p : points) {
            if (max_response < function(points.indexOf(p))) {
                max_response = function(points.indexOf(p));
                p_max = p;
            }
        }
        return p_max;
    }

}
