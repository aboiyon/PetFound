package com.moringa.petfinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class MyPetsArrayAdapter extends ArrayAdapter {

    private final String[] mPets;
    private final String[] mBreeds;
    private final String[] mSex;
    private final String[] mLocation;

    public MyPetsArrayAdapter(@NonNull Context context, int resource, String[] mPets, String[]mBreeds, String [] mSex, String[] mLocation) {
        super(context, resource);
        this.mPets = mPets;
        this.mBreeds = mBreeds;
        this.mSex = mSex;
        this.mLocation = mLocation;
    }
}
