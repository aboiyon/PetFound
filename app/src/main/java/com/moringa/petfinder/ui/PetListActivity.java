package com.moringa.petfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.petfinder.MyPetsArrayAdapter;
import com.moringa.petfinder.R;
import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.models.SearchResponse;
import com.moringa.petfinder.network.PetClient;
import com.moringa.petfinder.network.interface1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListActivity extends AppCompatActivity {
    private static final String TAG = PetListActivity.class.getSimpleName();

    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(PetListActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);

        interface1 client = PetClient.getClient();

        Call<SearchResponse> call = client.getPets(location, "pets");

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Animal> petsList = response.body().getAnimals();
                    String[] pets = new String[petsList.size()];
                    String[] breeds = new String[petsList.size()];

                    for (int i = 0; i < pets.length; i++){
                        pets[i] = petsList.get(i).getName();
                    }

                    for (int i = 0; i < breeds.length; i++) {
//                        ULocale.Category category = petsList.get(i).getBreeds().getPrimary(0);
                        breeds[i] = String.valueOf(petsList.get(i).getBreeds());
                    }

                    ArrayAdapter adapter
                            = new MyPetsArrayAdapter(PetListActivity.this, android.R.layout.simple_expandable_list_item_1, pets, breeds);
                    mListView.setAdapter(adapter);

                    showPets();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showPets() {
        mListView.setVisibility(View.VISIBLE);
        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}

//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.moringa.petfinder.R;
//import com.moringa.petfinder.adapters.PetListAdapter;
//import com.moringa.petfinder.models.Animal;
//import com.moringa.petfinder.models.SearchResponse;
//import com.moringa.petfinder.network.PetClient;
//import com.moringa.petfinder.network.interface1;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PetListActivity extends AppCompatActivity {
//    private static final String TAG = PetListActivity.class.getSimpleName();
//
//
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//    @BindView(R.id.errorTextView) TextView mErrorTextView;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;
//
//    private PetListAdapter mAdapter;
//
//    public List<Animal> pets;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pets);
//        ButterKnife.bind(this);
//
//        Intent intent = getIntent();
//        String type = intent.getStringExtra("type");
////        String breed = intent.getStringExtra("breed");
//
//        interface1 client = PetClient.getClient();
//        Call<SearchResponse> call = client.getPets(type, "pets");
//        call.enqueue(new Callback<SearchResponse>() {
//            @Override
//            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
//                hideProgressBar();
//
//                if (response.isSuccessful()) {
//                    pets = response.body().getAnimals();
//                    mAdapter = new PetListAdapter(PetListActivity.this, pets);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager =
//                            new LinearLayoutManager(PetListActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//
//                    showPets();
//                } else {
//                    showUnsuccessfulMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SearchResponse> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
//                hideProgressBar();
//                showFailureMessage();
//            }
//
//        });
//    }
//
//    private void showFailureMessage() {
//        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showUnsuccessfulMessage() {
//        mErrorTextView.setText("Something went wrong. Please try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showPets() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//    }
//
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//    }
//}
