import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Diagram extends JFrame {

    private BufferedImage bimg;
    private int size;
    private List<Neurone> neurons = new ArrayList<>();
    private List<Point> points = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();
    private double min_x = Double.MAX_VALUE;
    private double max_x = 0;
    private double min_y = Double.MAX_VALUE;
    private double max_y = 0;

    public Diagram(int size) {
        this.size = size;
    }

    public void setNeurons(List<Neurone> neurons) {
        this.neurons = neurons;
        for (Neurone n : neurons) {
            updateMinMax(n.getX(), n.getY());
        }
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        for (Point p : points) {
            updateMinMax(p.getX(), p.getY());
        }
    }

    private void updateMinMax(double x, double y) {
        if (min_x > x) {
            min_x = x;
        }
        if (min_y > y) {
            min_y = y;
        }
        if (max_x < x) {
            max_x = x;
        }
        if (max_y < y) {
            max_y = y;
        }
    }

    public void draw() {
        setBounds(0, 0, size, size);

        int n;
        Random rand = new Random();
        bimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        for (Neurone ne : neurons) {
            colors.add(rand.nextInt(16777215));
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                n = 0;
                for (int i = 0; i < neurons.size(); i++) {
                    Neurone ni = neurons.get(i);
                    Neurone nn = neurons.get(n);
                    if (distance(scaleX(ni.getX()), x, scaleY(ni.getY()), y) < distance(scaleX(nn.getX()), x, scaleY(nn.getY()), y)) {
                        n = i;
                    }
                }
                bimg.setRGB(x, y, colors.get(n));
            }
        }

        Graphics2D g = bimg.createGraphics();
        for (Point p : points) {
            if (p.getNeurone() != null) {
                g.setColor(new Color(colors.get(neurons.indexOf(p.getNeurone()))).darker());
                g.fill(new Ellipse2D.Double(scaleX(p.getX()), scaleY(p.getY()), 4, 4));
            }
        }

        g.setColor(Color.BLACK);
        for (Neurone ne : neurons) {
            g.fill(new Ellipse2D.Double(scaleX(ne.getX()), scaleY(ne.getY()), 10, 10));
        }

        try {
            ImageIO.write(bimg, "png", new File("voronoi.png"));
        } catch (IOException e) {

        }
    }

    private double scaleX(double x) {
        return ((x + Math.abs(min_x)) / Math.abs(max_x - min_x)) * size;
    }


    private double scaleY(double y) {
        return ((y + Math.abs(min_y)) / Math.abs(max_y - min_y)) * size;
    }

    public void paint (Graphics g) {
        g.drawImage(bimg, 0, 0, this);
    }

    private double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}