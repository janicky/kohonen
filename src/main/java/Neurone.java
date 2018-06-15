import org.apache.commons.lang.builder.EqualsBuilder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Neurone {

    private Weight weight;
    private List<Point> points;
    private boolean alive = false;

    public Neurone(List<Point> points) {
        this.points = points;
        weight = new Weight(-0.2, 0.2);
    }

    public double getX() {
        return weight.getX();
    }

    public double getY() {
        return weight.getY();
    }

    private double function(int n) {
        Point point = points.get(n);
        return 1 / Math.sqrt(Math.pow(weight.getX() - point.getX(), 2) + Math.pow(weight.getY() - point.getY(), 2));
    }

    public double getOutput(int n) {
        return function(n);
    }

    public void learn(Neurone winner, Point p, double lambda) {
        weight.correctX(gauss(winner, lambda) * weight.diffX(p));
        weight.correctY(gauss(winner, lambda) * weight.diffY(p));
        alive = true;
    }

    private double gauss(Neurone winner, double lambda) {
        return Math.exp(-(distanceTo(winner) * distanceTo(winner)) / (2 * lambda * lambda));
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

    public double distanceTo(Neurone n) {
        return Math.sqrt(Math.pow(getX() - n.getX(), 2) + Math.pow(getY() - n.getY(), 2));
    }

}
