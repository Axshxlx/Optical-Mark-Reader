package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class DisplayInfoFilter implements PixelFilter {
    private ArrayList<String> answers;
    public DisplayInfoFilter() {
        answers = new ArrayList<>();
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();

// loop thru questions by starting at row 108, and to get to the next question increment row by 48 pixels
        int col = 102;
        for (int row=108; row < 650; row+=48){
            String answer = extractAnswer(calculateAvgDarknessPerQuestion(row,col,img));
            answers.add(answer);
        }
        return img;
    }

    public String extractAnswer(int answer) {
        if(answer==0) return "A";
        else if (answer==1) return "B";
        else if (answer==2) return "C";
        else if (answer==3) return "D";
        return "E";
    }
    public int calculateAvgDarknessPerQuestion(int r, int c, DImage img) {
        ArrayList<Double> avgDarknesses = new ArrayList<>();
        short[][] pixels = img.getBWPixelGrid();
        for (int a = 0; a < 5; a++) { // answer choices to loop through
            int blackPixels = 0;
            int whitePixels = 0;

            for (int i = r; i < r + 21; i++) { // bubble height
                for (int j = c; j < c + 24; j++) { // bubble length
                    if (pixels[i][j] < 185) blackPixels++;
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
    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void clearAnswers() {
        answers.clear();
    }
}



