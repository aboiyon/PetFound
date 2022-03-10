package com.moringa.petfinder.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.moringa.petfinder.R;
import com.moringa.petfinder.adapters.PetPagerAdapter;
import com.moringa.petfinder.models.Animal;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private PetPagerAdapter adapterViewPager;
    List<Animal> mPets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);

        mPets = Parcels.unwrap(getIntent().getParcelableExtra("pets"));
        int startingPosition = getIntent().getIntExtra("position",0);

        adapterViewPager = new PetPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mPets);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
