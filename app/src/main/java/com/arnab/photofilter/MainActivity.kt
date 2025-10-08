package com.arnab.photofilter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.palette.graphics.Palette


class MainActivity : AppCompatActivity() {
    var modified_image_view: ImageView? = null
    var editBitmap: Bitmap? = null
    private var intDominantColor = 0

    var VibrantColor: ImageView? = null
    var DarkVibrantColor: ImageView? = null
    var LightVibrantColor: ImageView? = null
    var MutedColor: ImageView? = null
    var DarkMutedColor: ImageView? = null
    var LightMutedColor: ImageView? = null
    var DominantColor: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val original_image_view = findViewById<ImageView?>(R.id.original_image_view)
        modified_image_view = findViewById<ImageView?>(R.id.modified_image_view)

        VibrantColor = findViewById<ImageView?>(R.id.VibrantColor)
        DarkVibrantColor = findViewById<ImageView?>(R.id.DarkVibrantColor)
        LightVibrantColor = findViewById<ImageView?>(R.id.LightVibrantColor)

        MutedColor = findViewById<ImageView?>(R.id.MutedColor)
        DarkMutedColor = findViewById<ImageView?>(R.id.DarkMutedColor)
        LightMutedColor = findViewById<ImageView?>(R.id.LightMutedColor)

        DominantColor = findViewById<ImageView?>(R.id.DominantColor)

        val drawable = original_image_view.getDrawable()
        editBitmap = (drawable as BitmapDrawable).getBitmap()
        modified_image_view?.setImageBitmap(editBitmap)
        getColorsUsingPalette(editBitmap)
    }

    /**
     * Get different colors from input bitmap using @Palette API
     *
     * @param bitmap Input image for analysis
     */
    private fun getColorsUsingPalette(bitmap: Bitmap?) {
        Palette.generateAsync(bitmap, object : Palette.PaletteAsyncListener {
            override fun onGenerated(palette: Palette?) {
                if(palette==null)
                    return
                // Do something with colors...
                Log.d("msg", "onGenerated VibrantColor: " + palette.getVibrantColor(Color.TRANSPARENT))
                Log.d("msg", "onGenerated DarkVibrantColor: " + palette.getDarkVibrantColor(Color.TRANSPARENT))
                Log.d("msg", "onGenerated LightVibrantColor: " + palette.getLightVibrantColor(Color.TRANSPARENT))

                Log.d("msg", "onGenerated MutedColor: " + palette.getMutedColor(Color.TRANSPARENT))
                Log.d("msg", "onGenerated DarkMutedColor: " + palette.getDarkMutedColor(Color.TRANSPARENT))
                Log.d("msg", "onGenerated LightMutedColor: " + palette.getLightMutedColor(Color.TRANSPARENT))

                Log.d("msg", "onGenerated DominantColor: " + palette.getDominantColor(Color.TRANSPARENT))

                VibrantColor?.setColorFilter(palette.getVibrantColor(Color.TRANSPARENT))
                DarkVibrantColor?.setColorFilter(palette.getDarkVibrantColor(Color.TRANSPARENT))
                LightVibrantColor?.setColorFilter(palette.getLightVibrantColor(Color.TRANSPARENT))

                MutedColor?.setColorFilter(palette.getMutedColor(Color.TRANSPARENT))
                DarkMutedColor?.setColorFilter(palette.getDarkMutedColor(Color.TRANSPARENT))
                LightMutedColor?.setColorFilter(palette.getLightMutedColor(Color.TRANSPARENT))

                DominantColor?.setColorFilter(palette.getDominantColor(Color.TRANSPARENT))
                intDominantColor = palette.getDominantColor(Color.TRANSPARENT)
            }
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)
//        val menuInflater = getMenuInflater()
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.getItemId() == R.id.menu_color) {
//            ColorPickerDialogBuilder
//                .with(this)
//                .setTitle("Choose color")
//                .initialColor(Color.RED)
//                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
//                .density(12)
//                .setOnColorSelectedListener(object : OnColorSelectedListener() {
//                    public override fun onColorSelected(selectedColor: Int) {
//                        Log.d("msg", "onColorSelected: 0x" + Integer.toHexString(selectedColor))
//                    }
//                })
//                .setPositiveButton("ok", object : ColorPickerClickListener() {
//                    public override fun onClick(dialog: DialogInterface?, selectedColor: Int, allColors: Array<Int?>?) {
//                        val convertedBitmap: Bitmap = MainActivity.Companion.replaceColor(editBitmap, intDominantColor, selectedColor)
//                        modified_image_view.setImageBitmap(convertedBitmap)
//                        //Log.d("msg", "menu selectedColor: " + selectedColor);
//                    }
//                })
//                .setNegativeButton("cancel", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                    }
//                })
//                .build()
//                .show()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    companion object {
        /**
         * @param original Bitmap that will be edited in this method
         * @param oldColor Color to be replace with new color in original bitmap
         * @param newColor This color will replace the old color in bitmap image
         * @return A new Bitmap image
         */
        fun replaceColor(original: Bitmap, oldColor: Int, newColor: Int): Bitmap {
            val finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig()!!)

            var A: Int
            var R: Int
            var G: Int
            var B: Int
            var pixelColor: Int
            val height = original.getHeight()
            val width = original.getWidth()

            for (y in 0..<height) {
                for (x in 0..<width) {
                    pixelColor = original.getPixel(x, y)
                    A = Color.alpha(pixelColor)
                    R = Color.red(pixelColor)
                    G = Color.green(pixelColor)
                    B = Color.blue(pixelColor)
                    //Log.d("msg", "A: " + A + ", R: " + R + ", G: " + G + ", B:" + B);
                    val colorOffset = 50
                    if (Color.red(pixelColor) < Color.red(oldColor) + colorOffset && Color.red(pixelColor) > Color.red(oldColor) - colorOffset && Color.green(pixelColor) < Color.green(oldColor) + colorOffset && Color.green(pixelColor) > Color.green(oldColor) - colorOffset && Color.blue(pixelColor) < Color.blue(
                            oldColor
                        ) + colorOffset && Color.blue(pixelColor) > Color.blue(oldColor) - colorOffset
                    ) {
                        R = Color.red(newColor)
                        G = Color.green(newColor)
                        B = Color.blue(newColor)
                        finalImage.setPixel(x, y, Color.argb(A, R, G, B))
                    } else {
                        finalImage.setPixel(x, y, Color.argb(A, R, G, B))
                    }
                }
            }
            return finalImage
        }
    }
}