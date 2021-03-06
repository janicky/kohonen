import java.util.Random;

public class Weight {

    private double x;
    private double y;
    private Random random = new Random();

    public Weight(double min, double max) {
        this.x = min + (max - min) * random.nextDouble();;
        this.y = min + (max - min) * random.nextDouble();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void correctX(double x) {
        this.x += x;
    }

    public void correctY(double y) {
        this.y += y;
    }

    public double diffX(Point p) {
        return p.getX() - x;
    }
    public double diffY(Point p) {
        return p.getY() - y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[x: ");
        sb.append(x + ", y: ");
        sb.append(y + "]");
        return sb.toString();
    }
}

