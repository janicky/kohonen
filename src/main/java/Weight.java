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
}

