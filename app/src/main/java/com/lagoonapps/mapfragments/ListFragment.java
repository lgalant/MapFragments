package com.lagoonapps.mapfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    PlacesAdapter placesAdapter;

    public ListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("ListFragment","onCreateView");
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ListView lv = (ListView) v.findViewById(R.id.places);


        placesAdapter = new PlacesAdapter(getContext(),new ArrayList<Place>());
        lv.setAdapter(placesAdapter);

        // Simulate API call
        ArrayList<Place> places = ((PlaceActivity)getActivity()).getPlaces();
        placesAdapter.setPlaces(places);
        placesAdapter.notifyDataSetChanged();

        return v;
    }


}
