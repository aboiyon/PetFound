package com.moringa.petfinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class MyPetsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private final String[] mPets;
    private final String[] mBreeds;
    private final String[] mSex;
    private final String[] mLocation;

    public MyPetsArrayAdapter(@NonNull Context context, int resource, String[] mPets, String[] mBreeds, String[]mSex, String[]mLocation) {
        super(context, resource);
        this.mContext = mContext;
        this.mPets = mPets;
        this.mBreeds = mBreeds;
        this.mSex = mSex;
        this.mLocation = mLocation;
    }
    @Override
    public Object getItem(int position) {
        String pet = mPets[position];
        String breed = mBreeds[position];
        String sex = mSex[position];
        String location = mLocation[position];
        return String.format("%s \nBreed: %s %s \nLocation: %s", pet, breed,sex,location);
    }

    @Override
    public int getCount() {

        return mPets.length;
    }
}


