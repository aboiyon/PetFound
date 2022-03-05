package com.moringa.petfinder.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.moringa.petfinder.R;
import com.moringa.petfinder.models.Animal;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.petImageView) ImageView mImageView;
    @BindView(R.id.petNameTextView) TextView mNameLabel;
    @BindView(R.id.locationTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.savePetButton) TextView mSavePetsButton;

    private Animal mPet;

    public PetDetailFragment() {
        // Required empty public constructor
    }

    public static PetDetailFragment newInstance(Animal animal) {
        PetDetailFragment fragment = new PetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("animal", Parcels.wrap(animal));
        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments()!= null;
        mPet = Parcels.unwrap(getArguments().getParcelable("animal"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mPet.getUrl()).into(mImageView);
        mNameLabel.setText(mPet.getName());
        mRatingLabel.setText(mPet.getCoat().toString());
        mPhoneLabel.setText(mPet.getContact().toString());
        mAddressLabel.setText(mPet.getAge());

        return view;
//        return inflater.inflate(R.layout.fragment_pet_detail, container, false);
    }

    @Override
    public void onClick(View view) {
        if (view == mWebsiteLabel){
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPet.getUrl()));
            startActivity(webIntent);
        }
        if (view == mPhoneLabel){
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel"+ mPet.getContact()));
            startActivity(phoneIntent);
        }
        if (view == mAddressLabel){
            Intent addressIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mPet.getAge()));
            startActivity(addressIntent);
        }
    }
}