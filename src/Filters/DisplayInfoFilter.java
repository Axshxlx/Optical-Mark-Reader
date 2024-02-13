package Filters;

import Interfaces.PixelFilter;
import core.DImage;
import core.DisplayWindow;
import org.gstreamer.lowlevel.GValueAPI;
import processing.core.PImage;

import java.util.ArrayList;

public class DisplayInfoFilter implements PixelFilter {
    public DisplayInfoFilter() {
        System.out.println("Filter running...");
    }

    @Override
    public DImage processImage(DImage img) {
        img=cropImg(img, 0, 500, 0, 500);
        short[][] pixels = img.getBWPixelGrid();


// for question 1

// loop thru questions by starting at row 108, and to get to the next question increment row by 48 pixels
        //double a= calculateAvgDarkness(row, col, img); // 20 is height 23 is width
        return img;
    }

    public DImage cropImg(DImage img, int x1, int x2, int y1, int y2) {
        DImage croppedImage = new DImage(x2 - x1, y2 - y1);
        short[][] pixels = img.getBWPixelGrid();
        short[][] croppedPixels = croppedImage.getBWPixelGrid();

        for (int x = 0; x < x2 - x1; x++) {
            for (int y = 0; y < y2 - y1; y++) {

                short pixelValue = pixels[y][x];

                croppedPixels[y + y1][x + x1] = pixelValue;
            }
        }
        croppedImage.setPixels(croppedPixels);
        return croppedImage;
    }
//            public static String currentFolder = System.getProperty("user.dir") + "/";
//
//            String croppedImagePath = currentFolder + "assets/cropped_page" + page + ".png";
//            croppedImage.save(croppedImagePath);
//            DisplayWindow.showFor(croppedImagePath);
//        }

    public String extractAnswer(int answer) {
        if(answer==0) return "A";
        else if (answer==1) return "B";
        else if (answer==2) return "C";
        else if (answer==3) return "D";
        return "E";
    }



    public double calculateAvgDarknessPerQuestion(int r, int c, DImage img){
        int blackPixels=0;
        int whitePixels=0;
        ArrayList<Double> avgDarknesses= new ArrayList<>();
        short[][]grid  = img.getBWPixelGrid();
        for (int a = 0; a < 4; a++) {
            for (int i = r; i < r + 20; i++) {
                for (int j = c; j < c + 23; j++) {
                    if (grid[r][c] < 126) blackPixels++;
                    else whitePixels++;
                }
            }
            avgDarknesses.add((double)blackPixels/whitePixels);
           c+=23;
        }
        return findGreatestinList(avgDarknesses);
    }

    public int findGreatestinList(ArrayList<Double> list){
        double max = 0;
        for (Double d: list) {
            if(max<d) max=d;
        }
        return list.indexOf(max);
    }


}



