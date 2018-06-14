import java.util.ArrayList;
import java.util.List;

public class Neurone {

    private List<Weight> weights = new ArrayList<>();
    private List<Point> points;
    private double learningFactor = 0.5;
    private Weight winner = null;

    public Neurone(List<Point> points) {
        this.points = points;
//        init weights
        initWeights();
    }

    public double getX() {
        return winner.getX();
    }

    public double getY() {
        return winner.getY();
    }

    private void initWeights() {
        for (int i = 0; i < points.size(); i++) {
            weights.add(i, new Weight(-10.5, 10.5));
        }
    }

    private double function(int n) {
        Weight weight = weights.get(n);
        Point point = points.get(n);

        return 1 / Math.sqrt(Math.pow(weight.getX() - point.getX(), 2) + Math.pow(weight.getY() - point.getY(), 2));
    }

    public Weight getOutput() {
        Weight w_max = null;
        double max_response = 0;
        for (Point p : points) {
            if (max_response < function(points.indexOf(p))) {
                max_response = function(points.indexOf(p));
                w_max = weights.get(points.indexOf(p));
            }
        }
        winner = w_max;
        return w_max;
    }

    public void learn(double lambda) {
        for (Weight w : weights) {
            if (winner.distanceTo(w) < lambda) {
                w.correctX(function(weights.indexOf(winner)) * winner.diffX(w));
                w.correctY(function(weights.indexOf(winner)) * winner.diffY(w));
            }
        }
    }

    public double getError() {
        double sum = 0d;
        for (Point p : points) {
            sum += Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2));
        }
        return sum;
    }

}
