package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BasicColorFilter implements PixelFilter {


    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        // Do stuff with color channels here

        img.setColorChannels(red, green, blue);
        return img;
    }
}

