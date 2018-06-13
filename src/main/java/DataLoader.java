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


}
