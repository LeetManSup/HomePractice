package com.mirea.lutsko.mireaproject.ui.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mirea.lutsko.mireaproject.R;

public class CameraFragment extends Fragment {
    private Button saveButton;
    private ImageView cameraImageView;
    private final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private boolean isWork = false;
    private Uri imageUri;
    private String currentPhotoPath;
    ActivityResultLauncher<Intent> cameraRequest;
    ActivityResultLauncher<String[]> permissionsRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera,
                container, false);

        cameraImageView = (ImageView) view.findViewById(R.id.camera_image_view);
        cameraImageView.setOnClickListener(this::onCameraImageClick);
        saveButton = (Button) view.findViewById(R.id.save_photo_button);
        saveButton.setOnClickListener(this::onSaveButtonClick);

        cameraRequest = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        cameraImageView.setImageURI(imageUri);
                        saveButton.setEnabled(true);
                    }
                });

        permissionsRequest = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                    if (isGranted.containsValue(false)){
                        permissionsRequest.launch(PERMISSIONS);
                    } else {
                        isWork = true;
                    }
                });
        isWork = hasPermissions(getContext(), PERMISSIONS);
        if(!isWork){
            if (getActivity() != null) {
                permissionsRequest.launch(PERMISSIONS);
            }
        }
        return view;
    }

    public static boolean hasPermissions(Context context, String... permissions){
        if (context != null && permissions != null){
            for (String permission: permissions){
                if (ActivityCompat.checkSelfPermission(context, permission)
                        == PackageManager.PERMISSION_DENIED){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void onSaveButtonClick(View view){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        if (getActivity() != null){
            getActivity().sendBroadcast(mediaScanIntent);
        }
        Toast.makeText(getContext(), "Фотография сохранена в галерею", Toast.LENGTH_SHORT)
                .show();
    }

    private void onCameraImageClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(isWork){
            File photoFile = null;
            try {
                photoFile = createImageFile();
                currentPhotoPath = photoFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String authorities = getActivity().getApplicationContext()
                    .getPackageName() + ".fileprovider";
            imageUri = FileProvider.getUriForFile(getContext(), authorities, photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraRequest.launch(intent);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }
}