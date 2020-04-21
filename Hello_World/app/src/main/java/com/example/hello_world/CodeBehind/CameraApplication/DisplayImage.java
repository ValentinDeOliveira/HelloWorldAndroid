package com.example.hello_world.CodeBehind.CameraApplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.Model.Utils.CameraApplication.CameraInteractor;
import com.example.hello_world.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DisplayImage extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image);

        imageView = findViewById(R.id.imageViewResult);

        int code = getIntent().getIntExtra("ImageCode", -1);

        if(code == CameraApplicationMainPage.PICK_IMAGE_CODE)
            loadPickedImage();
        else if(code == CameraApplicationMainPage.TAKE_PHOTO_CODE)
            loadTakenImage();
        else{
            Toast t = Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_SHORT);
            t.show();
            startActivity(new Intent(this, CameraApplicationMainPage.class));
        }
    }

    private void loadPickedImage() {
        String uriString = getIntent().getStringExtra("ImagePath");
        Uri uri = Uri.parse(uriString);

        try {
            InputStream imageStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadTakenImage() {
        String imagePath = getIntent().getStringExtra("ImagePath");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imageView.setImageBitmap(bitmap);

        CameraInteractor.deleteCurrentImage(imagePath);
    }
}
