package com.moringa.petfinder.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.petfinder.Constants;
import com.moringa.petfinder.R;
import com.moringa.petfinder.models.Animal;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.petNameTextView) TextView mNameLabel;
    @BindView(R.id.genderTextView) TextView mGenderLabel;
    @BindView(R.id.ageTextView) TextView mAgeLabel;

    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;

    @BindView(R.id.savePetButton) TextView mSavePetButton;

    private Animal mPet;

    public PetDetailFragment() {
        // Required empty public constructor
    }

    public static PetDetailFragment newInstance(Animal pet) {
        PetDetailFragment fragment = new PetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("pet", Parcels.wrap(pet));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments()!= null;
        mPet = Parcels.unwrap(getArguments().getParcelable("pet"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mPet.getName());
        mGenderLabel.setText(mPet.getGender());
        mAgeLabel.setText(mPet.getAge());

        mWebsiteLabel.setText(mPet.getUrl());
        mPhoneLabel.setText(mPet.getContact().toString());
        mAddressLabel.setText(mPet.getOrganizationId());

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSavePetButton.setOnClickListener(this);

        return view;
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
            Intent addressIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mPet.getOrganizationId()));
            startActivity(addressIntent);
        }

        //current authenticated user UID
        if (view == mSavePetButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference petRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PETS)
                    .child(uid);
            //pushid to be saved
            DatabaseReference pushRef = petRef.push();
            String pushId = pushRef.getKey();
            mPet.setPushId(pushId);
            pushRef.setValue(mPet);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}

