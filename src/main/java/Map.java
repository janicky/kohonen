import java.util.List;

public class Map {

    private List<Point> points;
    private int size;

    public Map(int size) {
//        set map size
        this.size = size;
//        load data from file
        DataLoader dl = new DataLoader("attract_small.txt");
        points = dl.getPoints();
    }
}
