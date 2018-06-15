import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Point> points;
    private List<Neurone> neurons = new ArrayList<>();
    private int size;
    private double lambda;
    private int epoch = 0;
    private double current_error = Double.MAX_VALUE;
    private double last_error = 0;
    private double precision = 0.0005;

    public Map(int size) {
//        set map size
        this.size = size;
//        load data from file
        DataLoader dl = new DataLoader("attract_small.txt");
        points = dl.getPoints();
//        create neurons
        createNeurons();
//        init lambda
        lambda = max();

        double active_error = Double.MAX_VALUE;
        while (Math.abs((current_error - last_error) / current_error) > precision) {
            epoch();
            classification();
            active_error = Math.abs((current_error - last_error) / current_error);
            last_error = current_error;
            current_error = getGlobalError();
        }
        System.out.println(neurons.toString());
        System.out.println("Epochs: " + epoch);
        System.out.println("D = " + getGlobalError());
        System.out.println("e = " + active_error);

        Diagram d = new Diagram(400);
        d.setNeurons(neurons);
        d.setPoints(points);
        d.draw();
    }

    public void epoch() {
        for (Point p : points) {
            double response = 0;
            Neurone winner = null;
            for (Neurone n : neurons) {
                if (n.getOutput(points.indexOf(p)) > response) {
                    response = n.getOutput(points.indexOf(p));
                    winner = n;
                }
            }
            for (Neurone n : neurons) {
                if (winner.distanceTo(n) < lambda) {
                    n.learn(winner, p, lambda);
                }
            }
        }
        lambda *= 0.9;
        ++epoch;
    }


    private double max() {
        double range_max = 0;
        for (Point p : points) {
            if (p.getX() > range_max) {
                range_max = p.getX();
            }
            if (p.getY() > range_max) {
                range_max = p.getY();
            }
        }
        return range_max;
    }

    private void createNeurons() {
        for (int i = 0 ; i < size; i++) {
            neurons.add(new Neurone(points));
        }
    }

    private double getGlobalError() {
        double sum = 0d;
        if (neurons.size() == 0) {
            return 0;
        }
        for (Neurone n : neurons) {
            sum += n.getError();
        }
        return sum / neurons.size();
    }

    private void classification() {
        for (Point p : points) {
            Neurone best_neurone = null;
            double min = Double.MAX_VALUE;
            for (Neurone n : neurons) {
                if (min > p.distanceTo(n)) {
                    min = p.distanceTo(n);
                    best_neurone = n;
                }
            }
            p.setNeurone(best_neurone);
        }
    }
}
