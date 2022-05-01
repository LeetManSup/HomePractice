package com.mirea.lutsko.mireaproject.ui.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mirea.lutsko.mireaproject.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment {
    protected static GoogleMap googleMap;
    private EditText searchEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        searchEditText = view.findViewById(R.id.searchEditText);
        view.findViewById(R.id.searchButton).setOnClickListener(this::onClickSearch);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {

            MapsFragment.googleMap = googleMap;

            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.setTrafficEnabled(true);

            LatLng moscow = new LatLng(55.5815244,36.8251221);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(moscow));


            LatLng pos1 = new LatLng(55.6695953,37.4798824);
            String campName1 = "Кампус на Проспекте Вернадского, 78";
            String description1 = "Пр-т Вернадского, д.78; (55.6695953, 37.4798824)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos1)
                    .title(campName1)
                    .snippet(description1));

            LatLng pos2 = new LatLng(55.6618971,37.4745255);
            String campName2 = "Кампус на Проспекте Вернадского, 86";
            String description2 = "Пр-т Вернадского, д.86; (55.6618971, 37.4745255)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos2)
                    .title(campName2)
                    .snippet(description2));

            LatLng pos3 = new LatLng(55.7938058,37.7000664);
            String campName3 = "Кампус на улице Стромынка";
            String description3 = "ул. Стромынка, д.20; (55.7938058, 37.7000664)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos3)
                    .title(campName3)
                    .snippet(description3));

            LatLng pos4 = new LatLng(55.7317977,37.5745506);
            String campName4 = "Кампус на улице Малая Пироговская";
            String description4 = "ул. Малая Пироговская, д.1c5; (55.7317977, 37.5745506)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos4)
                    .title(campName4)
                    .snippet(description4));

            LatLng pos5 = new LatLng(55.7648399,37.7392163);
            String campName5 = "Кампус на улице Соколиная Гора";
            String description5 = "5-я ул. Соколиной Горы, д.22; (55.7648399, 37.7392163)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos5)
                    .title(campName5)
                    .snippet(description5));

            LatLng pos6 = new LatLng(55.7250254,37.6304868);
            String campName6 = "Колледж программирования и безопасности РТУ - МИРЭА";
            String description6 = "1-й Щипковский пер., д.23; (55.7250254, 37.6304868)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos6)
                    .title(campName6)
                    .snippet(description6));

            LatLng pos7 = new LatLng(55.728676,37.5708812);
            String campName7 = "Военный учебный центр РТУ - МИРЭА";
            String description7 = "ул. Усачева, д.7/1; (55.728676, 37.5708812)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos7)
                    .title(campName7)
                    .snippet(description7));

            LatLng pos8 = new LatLng(55.9604333,38.049562);
            String campName8 = "Филиал РТУ - МИРЭА в г.Фрязино";
            String description8 = "ул. Вокзальная, д.2а; (55.9604333, 38.049562)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos8)
                    .title(campName8)
                    .snippet(description8));

            LatLng pos9 = new LatLng(45.0508385,41.9097125);
            String campName9 = "Филиал РТУ - МИРЭА в г.Ставрополе";
            String description9 = "пр-т Кулакова, д.8, квартал 601; (45.0508385, 41.9097125)";
            googleMap.addMarker(new MarkerOptions()
                    .position(pos9)
                    .title(campName9)
                    .snippet(description9));
        }
    };

    public void onClickSearch(View view) {
        String location = searchEditText.getText().toString();

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        try {
            List<Address>  listAddress = geocoder.getFromLocationName(location,1);

            if (listAddress.size() > 0) {
                LatLng latLng = new LatLng(listAddress.get(0).getLatitude(),
                        listAddress.get(0).getLongitude());

                String searchTitle = "Search";

                googleMap.addMarker(new MarkerOptions().position(latLng).title(searchTitle));
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 5);
                googleMap.animateCamera(cameraUpdate);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}