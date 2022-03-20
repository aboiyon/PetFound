package com.moringa.petfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringa.petfinder.R;
import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.ui.PetDetailActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetListAdapter  extends RecyclerView.Adapter<PetListAdapter.petViewHolder>{
    private List<Animal> mPets;
    private Context mContext;

    public PetListAdapter(Context context, List<Animal> pets) {
        mContext = context;
        mPets = pets;
    }

    @Override
    public PetListAdapter.petViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent,false);

        petViewHolder viewHolder = new petViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( PetListAdapter.petViewHolder holder, int position) {
        holder.bindPet(mPets.get(position));
    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }

    public class petViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.petNameTextView) TextView mNameTextView;
        @BindView(R.id.genderTextView) TextView mGenderTextView;
//        @BindView(R.id.ageTextView) TextView mAgeTextView;

        private Context mContext;

        public petViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindPet(Animal pets) {
            mNameTextView.setText(pets.getName());
            mGenderTextView.setText(pets.getGender());
//            mAgeTextView.setText(pet.getOrganizationId());
        }

        @Override
        public void onClick(View view) {

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PetDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("pets", Parcels.wrap(mPets));
            mContext.startActivity(intent);
        }
    }
}



