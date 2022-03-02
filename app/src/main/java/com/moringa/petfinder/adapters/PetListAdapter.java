package com.moringa.petfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringa.petfinder.PetDetailActivity;
import com.moringa.petfinder.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder>  {
    private List<Animal> mPets;
    private Context mContext;

    public PetListAdapter(Context context, List<Animal> pets) {
        mContext = context;
        mPets = pets;
    }
    public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.petImageView) ImageView mPetImageView;
        @BindView(R.id.petNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public PetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        @Override
        public PetListAdapter.PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
            PetViewHolder viewHolder = new PetViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(PetViewHolder holder, int position) {
            holder.bindPet(mPets.get(position));
        }

        @Override
        public int getItemCount() {
            return mPets.size();
        }

        public void bindPet(Animal gender) {
            mNameTextView.setText(gender.getName());
            mCategoryTextView.setText(gender.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + gender.getDescription());

            for(int i=0;i<gender.getPhotos().size();i++){
                Log.d("Photo","photo sizes"+gender.getPhotos().get(i));
                String photo = gender.getPhotos().get(i).getFull();
                Picasso.get().load(photo).into(mPetImageView);
            }
            public void onClick(View v) {

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, PetDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("pets", Parcels.wrap(mNameTextView));
                mContext.startActivity(intent);
            }
        }
    }
}
