package com.moringa.petfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.petfinder.Constants;
import com.moringa.petfinder.R;
import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.ui.PetDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebasePetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebasePetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindPet(Animal pet) {
        ImageView petImageView = (ImageView) mView.findViewById(R.id.petImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.petNameTextView);
        TextView genderTextView = (TextView) mView.findViewById(R.id.genderTextView);
        TextView ageTextView = (TextView) mView.findViewById(R.id.ageTextView);

       // Picasso.get().load(restaurant.getImageUrl()).into(restaurantImageView);

        nameTextView.setText(pet.getName());
        genderTextView.setText(pet.getGender());
        ageTextView.setText(pet.getOrganizationId());

        //categoryTextView.setText(pet.getCategories().get(0).getTitle());
        //ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Animal>pets = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_PET).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pets.add(snapshot.getValue(Animal.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PetDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("pets", Parcels.wrap(pets));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

