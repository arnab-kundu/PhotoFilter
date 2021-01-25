package com.arnab.photofilter.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ColorChanger {

    /**
     * Modify bitmap. Basically change color in bitmap
     *
     * @param original bitmap input of original image
     * @param color input color code in Integer
     * @return A bitmap where RED color will be replaced by input color in 2nd param
     */
    public static Bitmap replaceRedColor(Bitmap original, int color) {
        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = original.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                //Log.d("msg", "A: " + A + ", R: " + R + ", G: " + G + ", B:" + B);
                if (Color.red(pixelColor) > 200 && Color.green(pixelColor) < 50 && Color.blue(pixelColor) < 50) {
                    R = Color.red(color);
                    G = Color.green(color);
                    B = Color.blue(color);
                    finalImage.setPixel(x, y, Color.argb(A, R, G, B));
                } else {
                    finalImage.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }
        }
        return finalImage;
    }
}
