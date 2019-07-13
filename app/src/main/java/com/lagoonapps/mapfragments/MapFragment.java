package com.lagoonapps.mapfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        return v;
    }

    //https://stackoverflow.com/questions/14083950/duplicate-id-tag-null-or-parent-id-with-another-fragment-for-com-google-androi
    // You cannot create a fragment (android map) inside another fragment (this fragment)
    // Solution is to create the android map fragment programatically
    // Without this solution, the app shows the map once but once you go to another fragment it will crash

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentByTag("mapFragment");
        if (mapFragment == null) {
            mapFragment = new SupportMapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.mapFragmentContainer, mapFragment, "mapFragment");
            ft.commit();
            fm.executePendingTransactions();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Simulate API call
        ArrayList<Place> places = ((PlaceActivity)getActivity()).getPlaces();

        // Set markers on map
       MarkerOptions m;
       for (Place p : places) {
           m = new MarkerOptions();
           m.position(new LatLng(p.getLat(),p.getLng()));
           m.title(p.getName());
           googleMap.addMarker(m);
       }
        Place center = (Place)places.get(0);  // First item will be the map center
        LatLng coords = new LatLng(center.getLat(),center.getLng());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,12));  // zoom level 12
        googleMap.getUiSettings().setZoomControlsEnabled(true); // Enable zoom

    }
}

