package com.example.hello_world.Model.Utils.CameraApplication;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraInteractor {
    private String pathOfCurrentImage = "";
    private Context context;

    public CameraInteractor(Context context){
        this.context = context;
    }

    public Uri getUriForPermission(){

        Uri photoURI = null;
        try {
            photoURI = FileProvider.getUriForFile(context,
                    "com.example.android.fileprovider",
                    createImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return photoURI;
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        pathOfCurrentImage = image.getAbsolutePath();
        return image;
    }

    public String getPathOfCurrentImage() {
        return pathOfCurrentImage;
    }

    public static void deleteCurrentImage(String imagePath){
        File image = new File(imagePath);

        if(image.exists()){
            image.delete();
        }
    }
}
