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

    public double distanceTo(Weight w) {
        return Math.sqrt(
                Math.pow(x - w.getX(), 2) +
                        Math.pow(y - w.getY(), 2)
        );
    }

    public double diffX(Weight w) {
        return x - w.getX();
    }
    public double diffY(Weight w) {
        return y - w.getY();
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

