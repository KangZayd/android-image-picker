package com.esafirm.imagepicker.helper;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImagePickerUtils {

    private static final String TAG = "ImageUtils";

    public static File createImageFile(String directory) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), directory);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create " + directory + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp;

        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", mediaStorageDir);
        } catch (IOException e) {
            Log.d(TAG, "Oops! Failed create " + imageFileName + " file");
        }
        return imageFile;
    }

    public static String getNameFromFilePath(String path) {
        if (path.contains(File.separator)) {
            return path.substring(path.lastIndexOf(File.separator) + 1);
        }
        return path;
    }
}
