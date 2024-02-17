import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import com.jogamp.opengl.util.texture.spi.DDSImage;
import core.DImage;
import processing.core.PImage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class OpticalMarkReaderMain {
    public static void main(String[] args) {
        String pathToPdf = fileChooser();
        System.out.println("Loading pdf at " + pathToPdf);
        DisplayInfoFilter filter = new DisplayInfoFilter();
        /*Your code here to...
        (4).  Output 2 csv files
         */

        for (int i = 1; i <=6 ; i++) {
            filter.processImage(new DImage(PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf",i)));
            System.out.println(i +" "+ filter.getAnswers());
            filter.clearAnswers();
        }




    }

    private static String fileChooser() {
        String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }
}
