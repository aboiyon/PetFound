package com.moringa.petfinder.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.petfinder.Constants;
import com.moringa.petfinder.R;
import com.moringa.petfinder.adapters.FirebasePetViewHolder;
import com.moringa.petfinder.models.Animal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPetListActivity extends AppCompatActivity {
    private DatabaseReference mPetReference;
    private FirebaseRecyclerAdapter<Animal, FirebasePetViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout view
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        mPetReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETS);
        setUpFirebaseAdapter();
        hideProgressBar();
        showRestaurants();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Animal> options =
                new FirebaseRecyclerOptions.Builder<Animal>()
                        .setQuery(mPetReference, Animal.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Animal, FirebasePetViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebasePetViewHolder firebaseRestaurantViewHolder, int position, @NonNull Animal pet) {
                firebaseRestaurantViewHolder.bindPet(pet);
            }

            @NonNull
            @Override
            public FirebasePetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
                return new FirebasePetViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
