package com.moringa.petfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringa.petfinder.R;
import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.util.ItemTouchHelperAdapter;
import com.moringa.petfinder.util.OnStartDragListener;

public class FirebasePetListAdapter extends FirebaseRecyclerAdapter<Animal, FirebasePetViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebasePetListAdapter(FirebaseRecyclerOptions<Animal> options,
                                         DatabaseReference ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull FirebasePetViewHolder firebaseRestaurantViewHolder, int position, @NonNull Animal pet) {
        firebaseRestaurantViewHolder.bindPet(pet);
    }

    @NonNull
    @Override
    public FirebasePetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item_drag, parent, false);
        return new FirebasePetViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }
}