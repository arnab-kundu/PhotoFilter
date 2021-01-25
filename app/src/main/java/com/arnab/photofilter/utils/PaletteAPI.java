package com.arnab.photofilter.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import androidx.palette.graphics.Palette;

public class PaletteAPI {


    /**
     * Get different colors from input bitmap using @link Palette API
     *
     * @param bitmap Input image for analysis
     */
    public static void getColorsUsingPalette(Bitmap bitmap) {
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                // Do something with colors...
                Log.d("msg", "onGenerated VibrantColor: " + palette.getVibrantColor(Color.TRANSPARENT));
                Log.d("msg", "onGenerated DarkVibrantColor: " + palette.getDarkVibrantColor(Color.TRANSPARENT));
                Log.d("msg", "onGenerated LightVibrantColor: " + palette.getLightVibrantColor(Color.TRANSPARENT));

                Log.d("msg", "onGenerated MutedColor: " + palette.getMutedColor(Color.TRANSPARENT));
                Log.d("msg", "onGenerated DarkMutedColor: " + palette.getDarkMutedColor(Color.TRANSPARENT));
                Log.d("msg", "onGenerated LightMutedColor: " + palette.getLightMutedColor(Color.TRANSPARENT));

                Log.d("msg", "onGenerated DominantColor: " + palette.getDominantColor(Color.TRANSPARENT));
            }
        });
    }

}
