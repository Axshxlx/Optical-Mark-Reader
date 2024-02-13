package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;


public class KMeans implements PixelFilter {
    static int CLUSTERS = 3;
    @Override
    public DImage processImage(DImage img) {
        Clusters clusters = new Clusters(CLUSTERS, Point.arrayFrom(img));
        Point.setColorsFromClusters(clusters, img);
        return img;
    }
}



class Cluster {
    ArrayList<Point> points;
    Point center;
    public Cluster() {
        points = new ArrayList<>();
        center = new Point();
        center.r = Math.random()*256;
        center.g = Math.random()*256;
        center.b = Math.random()*256;
    }
    public double reCalculateCenter() {
        Point newCenter = new Point();
        for (Point p : points) {
            newCenter.r += p.r / points.size();
            newCenter.g += p.g / points.size();
            newCenter.b += p.b / points.size();
        }
        double similarity = distanceTo(newCenter);
        center = newCenter;
        return similarity;
    }
    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(center.r-p.r,2) + Math.pow(center.g-p.g,2) + Math.pow(center.b-p.b,2));
    }
    public void addPoint(Point p) {
        points.add(p);
    }
    public void clearPoints() {
        points = new ArrayList<>();
    }
    public Point getCenter() {
        return center;
    }
}





class Clusters {
    ArrayList<Cluster> clusters;
    ArrayList<Point> points;
    public Clusters(int k, ArrayList<Point> points) {
        clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster());
        }
        this.points = points;
        for (int i = 0; i < 20; i++) {
            if (reassign() < 12.) return;
        }
    }

    public double reassign() {
        for (Point p : points) {
            closestCluster(p).addPoint(p);
        }
        double movement = 0.0;
        for (Cluster c : clusters) {
            movement += c.reCalculateCenter() / clusters.size();
            c.clearPoints();
        }
        return movement;
    }
    public Cluster closestCluster(Point p) {
        double min = 9999999999999999.9;
        int index = -1;
        for (int i = 0; i < clusters.size(); i++) {
            double dist = clusters.get(i).distanceTo(p);
            if (dist < min) {
                min = dist;
                index = i;
            }
        }
        return clusters.get(index);
    }
    public Point recolor(Point p) {
        return closestCluster(p).center;
    }
}




class Point {
    double r,g,b;
    public Point() {
        r = 0;
        g = 0;
        b = 0;
    }
    public Point(short r, short g, short b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public static ArrayList<Point> arrayFrom(DImage img) {
        ArrayList<Point> points = new ArrayList<>(img.getWidth() * img.getHeight());
        short[][] red   = img.getRedChannel();
        short[][] blue  = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                points.add(new Point(red[y][x],green[y][x],blue[y][x]));
            }
        }
        return points;
    }
    public static void setColorsFromClusters(Clusters c, DImage img) {
        short[][] red   = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue  = img.getBlueChannel();
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Point p = c.recolor(new Point(red[y][x],green[y][x],blue[y][x]));
                red[y][x] = (short) p.r;
                green[y][x] = (short) p.g;
                blue[y][x] = (short) p.b;
            }
        }
        img.setColorChannels(red,green,blue);
    }
}