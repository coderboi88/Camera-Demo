package com.example.camerademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InvertActivity extends AppCompatActivity {

    private Button invertBtn;
    private ImageView imageView;
    private Bitmap bitmapImage;
    private Drawable myFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invert);

        invertBtn = (Button) findViewById(R.id.invertBtn);
        imageView = (ImageView) findViewById(R.id.imageView);
        myFlower = ResourcesCompat.getDrawable(getResources(),R.drawable.imageone,null);
        bitmapImage = ((BitmapDrawable) myFlower).getBitmap();

       // For Applying Filter in a Image.............................Refer Below Code

       /* Drawable [] layers = new Drawable[2];
        layers[0] = ResourcesCompat.getDrawable(getResources(),R.drawable.imageone,null);
        layers[1] = ResourcesCompat.getDrawable(getResources(),R.drawable.filter,null);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        imageView.setImageDrawable(layerDrawable);*/

        invertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap newPhoto = invertImage(bitmapImage);
                imageView.setImageBitmap(newPhoto);
            }


        });
    }

    private Bitmap invertImage(Bitmap bitmapImage) {
        Bitmap finalImage = Bitmap.createBitmap(bitmapImage.getWidth(),bitmapImage.getHeight(),bitmapImage.getConfig());
        int A,R,G,B;
        int pixelColor;
        int height = bitmapImage.getHeight();
        int width = bitmapImage.getWidth();

        for (int i = 0; i <height ; i++) {
            for(int j=0;j<width;j++){
                pixelColor = bitmapImage.getPixel(j,i);
                A = Color.alpha(pixelColor);
                R = 255-Color.red(pixelColor);
                G = 255-Color.green(pixelColor);
                B = 255-Color.blue(pixelColor);

                finalImage.setPixel(j,i,Color.argb(A,R,G,B));
            }

        }
        return finalImage;
    }
}
