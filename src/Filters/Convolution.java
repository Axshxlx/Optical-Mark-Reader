package Filters;

import Interfaces.PixelFilter;
import core.DImage;
 public class Convolution implements PixelFilter {

        private double[][] outlineKernel =
                {
                        {-1, -1, -1},
                        {-1, 8, -1},
                        {-1, -1, -1}};

        private double[][] blurKernel =
                {
                        {-2, -1, 0},
                        {-1, 1, 1},
                        {0, 1, 2}};


        @Override
        public DImage processImage(DImage img) {
            short[][] pixelinput = img.getBWPixelGrid();
            short[][] pixeloutput = img.getBWPixelGrid();
            for (int i = 0; i < pixelinput.length-((blurKernel.length)-1); i++) {
                for (int j = 0; j < pixelinput[i].length-((blurKernel[0].length)-1); j++) {
                    int x = loopydoop(i,j,blurKernel,pixelinput);
                    if(sum(blurKernel)!=0) x=x/(int)sum(blurKernel);
                    if(x<0)x=0;
                    if(x>255)x=255;
                    pixeloutput[i+1][j+1]=(short)x;


                }

            }

            img.setPixels(pixeloutput);
            return img;
        }
        public static int loopydoop (int r, int c, double [][] embosskernel, short [][] pixelinput){
            int output =0;
            for (int i = 0; i < embosskernel.length; i++) {
                for (int j = 0; j < embosskernel[i].length; j++) {
                    int kernel = (int)embosskernel[i][j];
                    int pixelvalue = pixelinput[r+i][c+j];
                    output+= kernel*pixelvalue;

                }

            }
            return output;



        }

        public static double sum(double[][] convulution) {
            double sum = 0;
            for (int i = 0; i < convulution.length; i++) {
                for (int j = 0; j < convulution[i].length; j++) {
                    sum += convulution[i][j];


                }

            }
            return sum;
        }
    }

