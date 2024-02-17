import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import Interfaces.PixelFilter;
import core.DImage;
import core.DisplayWindow;
import processing.core.PImage;

public class FilterTest {
    public static String currentFolder = System.getProperty("user.dir") + "/";

    public static void main(String[] args) {
        // ----------------------------------------------------------------
        // >>> Run this to save a pdf page and run filters on the image <<<
        // ----------------------------------------------------------------
        for (int i = 1; i <= 6 ; i++) {
            SaveAndDisplayExample(i);
            System.out.println("Page "+i+ " has been loaded.");
            RunTheFilter(i);
            System.out.println("Page "+i+ " has had the filter run on it.");
        }
    }

    private static void RunTheFilter(int i ) {
        System.out.println("Loading pdf....");
        PImage in = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf",i);
        DImage img = new DImage(in);       // you can make a DImage from a PImage
        DisplayInfoFilter filter = new DisplayInfoFilter();
        filter.processImage(img);  // if you want, you can make a different method
                                   // that does the image processing an returns a DTO with
                                   // the information you want

    }

    private static void SaveAndDisplayExample(int page) {
        PImage img = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf",page);
        img.save(currentFolder + "assets/page" + page + ".png");

//        DisplayWindow.showFor("assets/page1.png");
    }
}