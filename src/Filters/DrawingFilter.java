package Filters;

import Interfaces.Drawable;
import Interfaces.PixelFilter;
import core.DImage;
import processing.core.PApplet;

public class DrawingFilter implements PixelFilter, Drawable {

    @Override
    public DImage processImage(DImage img) {
        // we don't change the input image at all!
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(255, 0, 0);
        window.ellipse(original.getWidth(), original.getHeight(), 10, 10);

        window.fill(0, 255, 0);
        window.ellipse(0, 0, 10, 10);
    }
}

