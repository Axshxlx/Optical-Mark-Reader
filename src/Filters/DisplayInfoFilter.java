package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class DisplayInfoFilter implements PixelFilter {
    public DisplayInfoFilter() {
        System.out.println("Filter running...");
    }

    private ArrayList<String> key = new ArrayList<>();
    @Override
    public DImage processImage(DImage img) {
        img=cropImg(img, 0, 500, 0, 800);
        short[][] pixels = img.getBWPixelGrid();


// for question 1

// loop thru questions by starting at row 108, and to get to the next question increment row by 48 pixels
        int col = 102;
        for (int row=108; row < 650; row+=48){
            String answer = extractAnswer(calculateAvgDarknessPerQuestion(row,col,img));
            key.add(answer);

        }
        System.out.println(key);
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



    public int calculateAvgDarknessPerQuestion(int r, int c, DImage img) {
        ArrayList<Double> avgDarknesses = new ArrayList<>();

        for (int a = 0; a < 5; a++) { // answer choices to loop through
            int blackPixels = 0;
            int whitePixels = 0;

            for (int i = r; i < r + 21; i++) { // bubble height
                for (int j = c; j < c + 24; j++) { // bubble length
                    if (img.getBWPixelGrid()[i][j] < 185) blackPixels++;
                    else whitePixels++;
                }
            }

            avgDarknesses.add((double) blackPixels / whitePixels);
            c += 23;
        }
        return indexOfGreatestInList(avgDarknesses);
    }

    public int indexOfGreatestInList(ArrayList<Double> list) {
        double max = 0;

        for (Double d : list) {
            if (max < d) max = d;
        }

        return list.indexOf(max);
    }


}



