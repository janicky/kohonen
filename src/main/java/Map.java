import javax.xml.crypto.Data;
import java.util.List;

public class Map {

    private List<Point> points;

    public Map() {
        DataLoader dl = new DataLoader("attract_small.txt");
        points = dl.getPoints();
    }
}
