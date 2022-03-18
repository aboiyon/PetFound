package com.moringa.petfinder.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.petfinder.Constants;
import com.moringa.petfinder.R;
import com.moringa.petfinder.adapters.PetListAdapter;
import com.moringa.petfinder.models.Animal;
import com.moringa.petfinder.models.SearchResponse;
import com.moringa.petfinder.network.PetApi;
import com.moringa.petfinder.network.PetClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    private static final String TAG = PetListActivity.class.getSimpleName();
    private PetListAdapter mAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    public List<Animal> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_TYPE_KEY, null);
        if (mRecentAddress != null) {
            fetchPets(mRecentAddress);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String type) {
                addToSharedPreferences(type);
                fetchPets(type);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String type) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void addToSharedPreferences(String type) {
        mEditor.putString(Constants.PREFERENCES_TYPE_KEY, type).apply();
    }

    private void fetchPets(String type) {
        PetApi client = PetClient.getClient();
        Call<SearchResponse> call = client.getPets(type);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    pets = response.body().getAnimals();
                    mAdapter = new PetListAdapter(PetListActivity.this, pets);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PetListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showPets();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
}

//        Intent intent = getIntent();
//        String type = intent.getStringExtra("type");
//
//        PetApi client = PetClient.getClient();
//        Call<SearchResponse> call = client.getPets(type);
//        call.enqueue(new Callback<SearchResponse>() {
//            @Override
//            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
//                hideProgressBar();
//
//                if (response.isSuccessful()) {
//                    pets = response.body().getAnimals();
//                    mAdapter = new PetListAdapter(PetListActivity.this, pets);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PetListActivity.this);
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
