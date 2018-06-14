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
        lambda = max() / neurons.size();

        System.out.println(lambda);

        epoch();
    }

    public void epoch() {
        for (Neurone n : neurons) {
            System.out.println(n.getOutput());

        }
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
}
