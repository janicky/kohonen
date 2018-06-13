import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

    private final String fileName;
    private File file;
    private Scanner scanner;
    private List<Point> points = new ArrayList<>();

    public DataLoader(String fileName) {
        this.fileName = fileName;

    }

    private boolean readFile() {
        try {
            file = new File(fileName);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] values = line.split("\\,");
                points.add(new Point(
                        Double.parseDouble(values[0]),
                        Double.parseDouble(values[1])
                ));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Point> getPoints() {
        return points;
    }

}
