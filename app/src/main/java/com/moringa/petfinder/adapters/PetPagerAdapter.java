package com.moringa.petfinder.adapters;

import android.support.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.ui.PetDetailFragment;

import java.util.List;

public class PetPagerAdapter extends FragmentPagerAdapter {
    private List<Animal> mPets;
    public PetPagerAdapter(@NonNull FragmentManager fragmentManager, int behavior, List<Animal> pets){
        super(fragmentManager, behavior);
        mPets = pets;
    }
    @Override
    public Fragment getItem(int position){
        return PetDetailFragment.newInstance(mPets.get(position));
    }
    @Override
    public int getCount(){
        return mPets.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return mPets.get(position).getName();
    }
}
