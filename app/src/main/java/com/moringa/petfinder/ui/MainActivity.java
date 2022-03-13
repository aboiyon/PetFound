package com.moringa.petfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.petfinder.Constants;
import com.moringa.petfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ValueEventListener mSearchedPetReferenceListener;
    private DatabaseReference mSearchedPetReference;

    @BindView(R.id.findPetsButton) Button mFindPetsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.savedPetsButton) Button mSavedPetsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedPetReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_PET);


        mSearchedPetReferenceListener = mSearchedPetReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot typeSnapshot : dataSnapshot.getChildren()){
                    String type = typeSnapshot.getValue().toString();
                    Log.d("Pet updated", "type: " + type);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindPetsButton.setOnClickListener(this);
        mSavedPetsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == mFindPetsButton) {
            String type = mLocationEditText.getText().toString();

            savePetToFirebase(type);
            Intent intent = new Intent(MainActivity.this, PetListActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
        }
        if (view == mSavedPetsButton) {
            Intent intent = new Intent(MainActivity.this, SavedPetListActivity.class);
            startActivity(intent);
        }
    }

    public void savePetToFirebase(String location) {
        mSearchedPetReference.push().setValue(location);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedPetReference.removeEventListener(mSearchedPetReferenceListener);
    }
}
