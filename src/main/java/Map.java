import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Point> points;
    private List<Neurone> neurons = new ArrayList<>();
    private int size;
    private double lambda;
    private int epoch = 0;

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

        for (Neurone n : neurons) {
            System.out.println(n.getOutput());
        }
        for (int i = 0; i < 20; i++) {
            epoch();
            System.out.println(getGlobalError());
        }

        System.out.println();
        for (Neurone n : neurons) {
            System.out.println(n.getOutput());
        }

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

        Diagram d = new Diagram(400);
        d.setNeurons(neurons);
        d.setPoints(points);
        d.draw();
    }

    public void epoch() {
        for (Neurone n : neurons) {
            n.getOutput();
            n.learn(lambda);
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
}
