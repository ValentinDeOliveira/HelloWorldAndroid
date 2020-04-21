package com.example.hello_world.CodeBehind.CameraApplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.Model.Utils.CameraApplication.CameraInteractor;
import com.example.hello_world.R;

public class CameraApplicationMainPage extends AppCompatActivity {

    static final int PICK_IMAGE_CODE = 1;
    public static final int TAKE_PHOTO_CODE = 2;
    private CameraInteractor cameraInteractor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_app_main_layout);

        cameraInteractor  = new CameraInteractor(this);
    }

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Allow full screen pictures
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraInteractor.getUriForPermission());
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    public void loadPhotoFromGallery(View view) {
        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == -1){
            if (requestCode == PICK_IMAGE_CODE) {
                Intent intent = new Intent(this, DisplayImage.class);
                intent.putExtra("ImageCode", requestCode);

                String s = data.getData().toString();
                intent.putExtra("ImagePath",s);
                startActivity(intent);
            }
            else if(requestCode == TAKE_PHOTO_CODE){
                Intent intent = new Intent(this, DisplayImage.class);
                intent.putExtra("ImageCode", requestCode);

                intent.putExtra("ImagePath", cameraInteractor.getPathOfCurrentImage());
                startActivity(intent);
            }

        }
    }
}
