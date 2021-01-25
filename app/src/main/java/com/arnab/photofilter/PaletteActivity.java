package com.arnab.photofilter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

public class PaletteActivity extends AppCompatActivity {

    ImageView VibrantColor,
            DarkVibrantColor,
            LightVibrantColor;
    ImageView MutedColor,
            DarkMutedColor,
            LightMutedColor;
    ImageView DominantColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        VibrantColor = findViewById(R.id.VibrantColor);
        DarkVibrantColor = findViewById(R.id.DarkVibrantColor);
        LightVibrantColor = findViewById(R.id.LightVibrantColor);

        MutedColor = findViewById(R.id.MutedColor);
        DarkMutedColor = findViewById(R.id.DarkMutedColor);
        LightMutedColor = findViewById(R.id.LightMutedColor);

        DominantColor = findViewById(R.id.DominantColor);

        ImageView input_image = findViewById(R.id.input_image);
        Drawable drawable = input_image.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        getColorsUsingPalette(bitmap);

    }

    /**
     * Get different colors from input bitmap using @Palette API
     *
     * @param bitmap Input image for analysis
     */
    private void getColorsUsingPalette(Bitmap bitmap) {
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

                VibrantColor.setColorFilter(palette.getVibrantColor(Color.TRANSPARENT));
                DarkVibrantColor.setColorFilter(palette.getDarkVibrantColor(Color.TRANSPARENT));
                LightVibrantColor.setColorFilter(palette.getLightVibrantColor(Color.TRANSPARENT));

                MutedColor.setColorFilter(palette.getMutedColor(Color.TRANSPARENT));
                DarkMutedColor.setColorFilter(palette.getDarkMutedColor(Color.TRANSPARENT));
                LightMutedColor.setColorFilter(palette.getLightMutedColor(Color.TRANSPARENT));

                DominantColor.setColorFilter(palette.getDominantColor(Color.TRANSPARENT));
            }
        });
    }

}