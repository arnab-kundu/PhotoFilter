package com.arnab.photofilter;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MainActivity extends AppCompatActivity {

    ImageView modified_image_view;
    Bitmap editBitmap;
    private int intDominantColor = 0;

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
        setContentView(R.layout.activity_main);

        ImageView original_image_view = findViewById(R.id.original_image_view);
        modified_image_view = findViewById(R.id.modified_image_view);

        VibrantColor = findViewById(R.id.VibrantColor);
        DarkVibrantColor = findViewById(R.id.DarkVibrantColor);
        LightVibrantColor = findViewById(R.id.LightVibrantColor);

        MutedColor = findViewById(R.id.MutedColor);
        DarkMutedColor = findViewById(R.id.DarkMutedColor);
        LightMutedColor = findViewById(R.id.LightMutedColor);

        DominantColor = findViewById(R.id.DominantColor);

        Drawable drawable = original_image_view.getDrawable();
        editBitmap = ((BitmapDrawable) drawable).getBitmap();
        modified_image_view.setImageBitmap(editBitmap);
        getColorsUsingPalette(editBitmap);
    }


    /**
     * @param original Bitmap that will be edited in this method
     * @param oldColor Color to be replace with new color in original bitmap
     * @param newColor This color will replace the old color in bitmap image
     * @return A new Bitmap image
     */
    public static Bitmap replaceColor(Bitmap original, int oldColor, int newColor) {
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
                int colorOffset = 50;
                if (Color.red(pixelColor) < Color.red(oldColor) + colorOffset && Color.red(pixelColor) > Color.red(oldColor) - colorOffset &&
                        Color.green(pixelColor) < Color.green(oldColor) + colorOffset && Color.green(pixelColor) > Color.green(oldColor) - colorOffset &&
                        Color.blue(pixelColor) < Color.blue(oldColor) + colorOffset && Color.blue(pixelColor) > Color.blue(oldColor) - colorOffset) {
                    R = Color.red(newColor);
                    G = Color.green(newColor);
                    B = Color.blue(newColor);
                    finalImage.setPixel(x, y, Color.argb(A, R, G, B));
                } else {
                    finalImage.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }
        }
        return finalImage;
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
                intDominantColor = palette.getDominantColor(Color.TRANSPARENT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_color) {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .initialColor(Color.RED)
                    .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {
                            Log.d("msg", "onColorSelected: 0x" + Integer.toHexString(selectedColor));
                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                            Bitmap convertedBitmap = replaceColor(editBitmap, intDominantColor, selectedColor);
                            modified_image_view.setImageBitmap(convertedBitmap);
                            //Log.d("msg", "menu selectedColor: " + selectedColor);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .build()
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}