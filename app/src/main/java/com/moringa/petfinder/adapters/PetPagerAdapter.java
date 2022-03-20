package com.moringa.petfinder.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.ui.PetDetailFragment;

import java.util.List;

public class PetPagerAdapter extends FragmentStatePagerAdapter {
    private List<Animal> mPets;
    public PetPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Animal> pets){
        super(fm, behavior);
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
