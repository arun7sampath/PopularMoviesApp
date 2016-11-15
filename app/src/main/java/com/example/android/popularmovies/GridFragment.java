package com.example.android.popularmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Arun on 11/13/16.
 */

public class GridFragment extends Fragment {

    private static final String LOG_TAG = GridFragment.class.getSimpleName();
    private int [] resourceArray = {R.drawable.ic_captain_america, R.drawable.ic_doctor_strange,
            R.drawable.ic_jack_reacher, R.drawable.ic_interstellar,
            R.drawable.ic_jurassic_world, R.drawable.ic_x_men};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_main, container, true);
        GridView gridView = (GridView) rootView.findViewById(R.id.movies_grid_view);

        ArrayList<Bitmap> imageList = new ArrayList<>();

        for(int res :  resourceArray) {
            Bitmap image = BitmapFactory.decodeResource(getResources(), res);
            imageList.add(image);
        }

        ArrayAdapter mAdapter = new ImageAdapter(getContext(), R.layout.grid_item, imageList);
        mAdapter.add(imageList);
        gridView.setAdapter(mAdapter);

        Log.v(LOG_TAG, "onCreate GridFragment");
        return rootView;
    }

    private class ImageAdapter extends ArrayAdapter {
        private final Context mContext;
        private ArrayList<Bitmap> mList;

        public ImageAdapter(Context context, int layoutResId, ArrayList data) {
            super(context, layoutResId, data);
            mList = new ArrayList<>(data);
            mContext = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView imageView;
            if (convertView == null) { // if it's not recycled, initialize some attributes
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.grid_item, container, false);
                imageView = (ImageView) view.findViewById(R.id.grid_image_view);
            } else {
                imageView = (ImageView) convertView;
            }
//            if(position < mList.size())
                imageView.setImageBitmap(mList.get(position)); // Load image into ImageView
            return imageView;
        }

    }
}

