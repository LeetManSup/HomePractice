package com.mirea.lutsko.mireaproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class PhotoFragment extends Fragment {

    private final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private ImageView imageViewPhoto;
    private Button buttonSave;
    private Uri imageUri;
    private String photoPath;
    private boolean isWork = false;

    ActivityResultLauncher<Intent> reqCamera;
    ActivityResultLauncher<String[]> reqPermissions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageViewPhoto = view.findViewById(R.id.imageViewPhoto);
        imageViewPhoto.setOnClickListener(this::onPhotoClick);

        buttonSave = view.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this::onSaveClick);

        reqCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        imageViewPhoto.setImageURI(imageUri);
                        buttonSave.setEnabled(true);
                    }
                });

        reqPermissions = registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(),
                isGranted -> {
                    if (isGranted.containsValue(false)){
                        reqPermissions.launch(PERMISSIONS);
                    } else {
                        isWork = true;
                    }
                });

        isWork = hasPermissions(getContext(), PERMISSIONS);
        if(!isWork){
            if (getActivity() != null) {
                reqPermissions.launch(PERMISSIONS);
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

    public void onPhotoClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(isWork){
            File photoFile = null;
            try {
                photoFile = createFile();
                photoPath = photoFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String authorities = getActivity().getApplicationContext()
                    .getPackageName() + ".fileprovider";
            imageUri = FileProvider.getUriForFile(getContext(), authorities, photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            reqCamera.launch(intent);
        }
    }

    public void onSaveClick(View view) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        if (getActivity() != null){
            getActivity().sendBroadcast(mediaScanIntent);
        }
        Toast.makeText(getContext(), "Фотография сохранена в галерею", Toast.LENGTH_SHORT)
                .show();
    }

    private File createFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }
}
