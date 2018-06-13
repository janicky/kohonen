import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Point> points;
    private List<Neurone> neurons = new ArrayList<>();
    private int size;

    public Map(int size) {
//        set map size
        this.size = size;
//        load data from file
        DataLoader dl = new DataLoader("attract_small.txt");
        points = dl.getPoints();
//        create neurons
        createNeurons();

        System.out.println(neurons.toString());
    }

    private void createNeurons() {
        for (int i = 0 ; i < size; i++) {
            neurons.add(new Neurone(points));
        }
    }
}
