public class Point {

    private double x;
    private double y;
    private Neurone neurone = null;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Neurone getNeurone() {
        return neurone;
    }

    public void setNeurone(Neurone neurone) {
        this.neurone = neurone;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Neurone n) {
        return Math.sqrt(
                Math.pow(x - n.getX(), 2) +
                        Math.pow(y - n.getY(), 2)
        );
    }
}
