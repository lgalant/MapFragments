package com.lagoonapps.mapfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class PlacesAdapter extends BaseAdapter {

        private ArrayList<Place> places;
        private Context context;

        PlacesAdapter(Context context, ArrayList<Place> places) {
            this.places = places;
            this.context = context;
        }

        @Override
        public int getCount() {
            return places.size();
        }

        @Override
        public Object getItem(int position) {
            return places.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setPlaces(Place[] places) {
            this.places = new ArrayList<>(Arrays.asList(places)); // Converts array into ArrayList
        }

        public void setPlaces(ArrayList<Place> places) {
            this.places = places;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            TextView name = (TextView) view.findViewById(R.id.name);
            TextView lat = (TextView) view.findViewById(R.id.lat);
            TextView lng = (TextView) view.findViewById(R.id.lng);

            Place p = places.get(position);
            name.setText(String.valueOf(p.getName()));
            lat.setText(String.valueOf(p.getLat()));
            lng.setText(String.valueOf(p.getLng()));
            return view;

        }
    }

