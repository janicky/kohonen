import org.apache.commons.lang.builder.EqualsBuilder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        double sum = 0;
        double count = 0;
        for (Point p : points) {
            if (this.equals(p.getNeurone())) {
                sum += p.getX();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    public double getY() {
        double sum = 0;
        double count = 0;
        for (Point p : points) {
            if (this.equals(p.getNeurone())) {
                sum += p.getY();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    private void initWeights() {
        for (int i = 0; i < points.size(); i++) {
            weights.add(i, new Weight(-15.5, 15.5));
        }
    }

    private double function(int n) {
        Weight weight = weights.get(n);
        Point point = points.get(n);

        return 1 / Math.sqrt(Math.pow(weight.getX() - point.getX(), 2) + Math.pow(weight.getY() - point.getY(), 2));
    }

    public double getOutput(int n) {
        return function(n);
//        Weight w_max = null;
//        double max_response = 0;
//        for (Point p : points) {
//            if (max_response < function(points.indexOf(p))) {
//                max_response = function(points.indexOf(p));
//                w_max = weights.get(points.indexOf(p));
//            }
//        }
//        winner = w_max;
//        return w_max;
    }

    public void learn(double lambda, Point p) {
        winner = weights.get(points.indexOf(p));
        for (Weight w : weights) {
            if (points.get(weights.indexOf(w)).distanceTo(this) < lambda) {
                w.correctX(function(points.indexOf(p)) * winner.diffX(w));
                w.correctY(function(points.indexOf(p)) * winner.diffY(w));
                //points.get(weights.indexOf(w)).setNeurone(this);
            }
        }
    }

    public double getError() {
        double sum = 0d;
        for (Point p : points) {
            if (this.equals(p.getNeurone())) {
                sum += Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2));
            }
        }
        return sum;
    }

    public boolean equals(Neurone n) {
        if (n == null) { return false; }
        if (n == this) { return true; }
        if (n.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder()
                .appendSuper(super.equals(n))
                .isEquals();
    }

    private int pointsSize() {
        int p_count = 0;
        for (Point p : points) {
            if (this.equals(p.getNeurone())) {
                p_count++;
            }
        }
        return p_count;
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(formatter.format(getX()));
        sb.append(", ");
        sb.append(formatter.format(getY()));
        sb.append(") points: ");
        sb.append(pointsSize() + "]\n");

        return sb.toString();
    }

}
