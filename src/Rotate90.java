import processing.core.PApplet;

public class Rotate90 implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        int[][] grid = img.getColorPixelGrid();
        int[][] out = new int[grid[0].length][grid.length];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                out[c][r] = grid[r][c];
            }
        }

        img.setPixels(out);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
    }

}

