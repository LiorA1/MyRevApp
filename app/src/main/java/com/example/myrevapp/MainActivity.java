package com.example.myrevapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jni.bitmap_operations.JniBitmapHolder;

public class MainActivity extends AppCompatActivity
{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("JniBitmapOperationsLibrary");
    }

    com.jni.bitmap_operations.JniBitmapHolder jniBitmapHolder = new JniBitmapHolder();
    public static final int IMAGE_RESID_TO_TEST = R.drawable.test;

    Button _ButtonNW = null;
    Button _ButtonNE = null;
    Button _ButtonSE = null;
    Button _ButtonSW = null;
    Button _ButtonRotate_180 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _ButtonNW = findViewById(R.id.ButtonNW);
        _ButtonNE = findViewById(R.id.ButtonNE);
        _ButtonSE = findViewById(R.id.ButtonSE);
        _ButtonSW = findViewById(R.id.ButtonSW);
        _ButtonRotate_180 = findViewById(R.id.ButtonRotate_180);


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //
        // original
        //
        final ImageView imageViewOriginal = (ImageView)findViewById(R.id.imageView);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), IMAGE_RESID_TO_TEST);
        //Bitmap workingBitmap = bitmap.copy(bitmap.getConfig(), true);
        imageViewOriginal.setImageBitmap(bitmap);

        _ButtonNW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // rotated 90 degrees CCW

                //final ImageView imageViewRotated90degreesCcw = (ImageView)findViewById(R.id.imageViewRotated90degreesCcw);
                Bitmap bitmap = ((BitmapDrawable)imageViewOriginal.getDrawable()).getBitmap();

                jniBitmapHolder.storeBitmap(bitmap);
                jniBitmapHolder.rotateBitmapCcw90();

                imageViewOriginal.setImageBitmap(jniBitmapHolder.getBitmapAndFree());

            }
        });

        _ButtonNE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // rotated 90 degrees CW

                //final ImageView imageViewRotated90degreesCw = (ImageView)findViewById(R.id.imageViewRotated90degreesCw);
                Bitmap bitmap = ((BitmapDrawable)imageViewOriginal.getDrawable()).getBitmap();

                jniBitmapHolder.storeBitmap(bitmap);
                jniBitmapHolder.rotateBitmapCw90();

                imageViewOriginal.setImageBitmap(jniBitmapHolder.getBitmapAndFree());

            }
        });

        _ButtonSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not operable at the moment", Toast.LENGTH_LONG).show();


            }
        });

        _ButtonSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not operable at the moment", Toast.LENGTH_LONG).show();

            }
        });

        _ButtonRotate_180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // rotate 180
                //final ImageView imageViewRotated180degreesCw = (ImageView)findViewById(R.id.imageViewRotated180degrees);
                Bitmap bitmap = ((BitmapDrawable)imageViewOriginal.getDrawable()).getBitmap();

                jniBitmapHolder.storeBitmap(bitmap);
                jniBitmapHolder.rotateBitmap180();

                imageViewOriginal.setImageBitmap(jniBitmapHolder.getBitmapAndFree());

            }
        });




        /*
        //
        // rotate 180
        //
        final ImageView imageViewRotated180degreesCw = (ImageView)findViewById(R.id.imageViewRotated180degrees);
        jniBitmapHolder.storeBitmap(bitmap);
        jniBitmapHolder.rotateBitmap180();
        ;
        imageViewRotated180degreesCw.setImageBitmap(jniBitmapHolder.getBitmapAndFree());
        //
        // cropped
        //
        final ImageView imageViewCropped = (ImageView)findViewById(R.id.imageViewCropped);
        jniBitmapHolder.storeBitmap(bitmap);
        jniBitmapHolder.cropBitmap(bitmap.getWidth() / 4, bitmap.getHeight() / 4, bitmap.getWidth() * 3 / 4,
                bitmap.getHeight() * 3 / 4);
        imageViewCropped.setImageBitmap(jniBitmapHolder.getBitmapAndFree());
        //
        // scaled using nearest neighbor algorithm (which is fast, simple, yet
        // it sometimes has aliases problems)
        //
        final ImageView imageViewScaledUsingNearestNeighbour = (ImageView)findViewById(R.id.imageViewScaledUsingNearestNeighbour);
        jniBitmapHolder.storeBitmap(bitmap);
        jniBitmapHolder.scaleBitmap(bitmap.getWidth() * 2, bitmap.getHeight() * 2, JniBitmapHolder.ScaleMethod.NearestNeighbour);
        final Bitmap scaledBitmapNN = jniBitmapHolder.getBitmapAndFree();
        imageViewScaledUsingNearestNeighbour.setImageBitmap(scaledBitmapNN);
        //
        // scaled using nearest neighbor algorithm (which is relatively high
        // quality resizing and it handles aliases nicely)
        //
        final ImageView imageViewScaledUsingBilinearInterpolation = (ImageView)findViewById(R.id.imageViewScaledUsingBilinearInterpolation);
        jniBitmapHolder.storeBitmap(bitmap);
        jniBitmapHolder.scaleBitmap(bitmap.getWidth() * 2, bitmap.getHeight() * 2,
                JniBitmapHolder.ScaleMethod.BilinearInterpolation);
        final Bitmap scaledBitmapBI = jniBitmapHolder.getBitmapAndFree();
        imageViewScaledUsingBilinearInterpolation.setImageBitmap(scaledBitmapBI);
        //
        // flipped on the vertical
        //
        final ImageView imageViewFlippedVertical = (ImageView)findViewById(R.id.imageViewFlippedVertical);
        jniBitmapHolder.storeBitmap(bitmap);
        jniBitmapHolder.flipBitmapVertical();
        imageViewFlippedVertical.setImageBitmap(jniBitmapHolder.getBitmapAndFree());
        //*/

    }

    /*
    @Override
    protected void onStart()
    {
        super.onStart();

        //
        // original
        //
        final ImageView imageViewOriginal = (ImageView)findViewById(R.id.imageViewOriginal);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), IMAGE_RESID_TO_TEST);
        imageViewOriginal.setImageBitmap(b);
        //
        // rotated 90 degrees CCW
        //
        final ImageView imageViewRotated90degreesCcw = (ImageView)findViewById(R.id.imageViewRotated90degreesCcw);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.rotateBitmapCcw90();
        imageViewRotated90degreesCcw.setImageBitmap(bitmapHolder.getBitmapAndFree());
        //
        // rotated 90 degrees CW
        //
        final ImageView imageViewRotated90degreesCw = (ImageView)findViewById(R.id.imageViewRotated90degreesCw);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.rotateBitmapCw90();
        imageViewRotated90degreesCw.setImageBitmap(bitmapHolder.getBitmapAndFree());
        //
        // rotate 180
        //
        final ImageView imageViewRotated180degreesCw = (ImageView)findViewById(R.id.imageViewRotated180degrees);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.rotateBitmap180();
        ;
        imageViewRotated180degreesCw.setImageBitmap(bitmapHolder.getBitmapAndFree());
        //
        // cropped
        //
        final ImageView imageViewCropped = (ImageView)findViewById(R.id.imageViewCropped);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.cropBitmap(b.getWidth() / 4, b.getHeight() / 4, b.getWidth() * 3 / 4,
                b.getHeight() * 3 / 4);
        imageViewCropped.setImageBitmap(bitmapHolder.getBitmapAndFree());
        //
        // scaled using nearest neighbor algorithm (which is fast, simple, yet
        // it sometimes has aliases problems)
        //
        final ImageView imageViewScaledUsingNearestNeighbour = (ImageView)findViewById(R.id.imageViewScaledUsingNearestNeighbour);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.scaleBitmap(b.getWidth() * 2, b.getHeight() * 2, ScaleMethod.NearestNeighbour);
        final Bitmap scaledBitmapNN = bitmapHolder.getBitmapAndFree();
        imageViewScaledUsingNearestNeighbour.setImageBitmap(scaledBitmapNN);
        //
        // scaled using nearest neighbor algorithm (which is relatively high
        // quality resizing and it handles aliases nicely)
        //
        final ImageView imageViewScaledUsingBilinearInterpolation = (ImageView)findViewById(R.id.imageViewScaledUsingBilinearInterpolation);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.scaleBitmap(b.getWidth() * 2, b.getHeight() * 2,
                ScaleMethod.BilinearInterpolation);
        final Bitmap scaledBitmapBI = bitmapHolder.getBitmapAndFree();
        imageViewScaledUsingBilinearInterpolation.setImageBitmap(scaledBitmapBI);
        //
        // flipped on the vertical
        //
        final ImageView imageViewFlippedVertical = (ImageView)findViewById(R.id.imageViewFlippedVertical);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.flipBitmapVertical();
        imageViewFlippedVertical.setImageBitmap(bitmapHolder.getBitmapAndFree());
        //
        // rotated 90 degrees CCW
        //
        final ImageView imageViewFlippedHorizontal = (ImageView)findViewById(R.id.imageViewFlippedHorizontal);
        bitmapHolder.storeBitmap(b);
        bitmapHolder.flipBitmapHorizontal();
        imageViewFlippedHorizontal.setImageBitmap(bitmapHolder.getBitmapAndFree());
    }
    //*/

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
