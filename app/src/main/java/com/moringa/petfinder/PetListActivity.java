package com.moringa.petfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.petfinder.adapters.PetListAdapter;
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


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private PetListAdapter mAdapter;

    public List<Animal> genders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");

//        Api client = Client.getClient();
        interface1 client = PetClient.getClient();
        Call<SearchResponse> call = client.getPets(gender);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    genders = response.body().getAnimals();
                    mAdapter = new PetListAdapter(PetListActivity.this, genders);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(PetListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showPets();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
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
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}



//API-KEY:bKYfebFDtZTVNWAbv7BMsmQYIgRAlYW717TqhFQWW4ZgEW3gQe
//SECRET-KEY: YRKwMSMd55uY25rxh4xOSssyZO1mu7uQNEoCR5rw
//TOKEN:eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJiS1lmZWJGRHRaVFZOV0FidjdCTXNtUVlJZ1JBbFlXNzE3VHFoRlFXVzRaZ0VXM2dRZSIsImp0aSI6Ijc3MmY2ODFmMDZlMWJhNzMzOGU1NGQ5ZDk3YTY0ZTkyZGVlODE3M2RlY2RkZTFkMGJjNzdjZjkxYWJkY2EyYWQ0OGRiMzgxOWQ1N2EzOWQ0IiwiaWF0IjoxNjQ2Mjc2NDM4LCJuYmYiOjE2NDYyNzY0MzgsImV4cCI6MTY0NjI4MDAzOCwic3ViIjoiIiwic2NvcGVzIjpbXX0.CncnaWwrFo4WYHke81VFuBHywLKWTDHo2L1R4yx-22eMRJajK9cjHALoEWDYYGLeHZFqK0M1l06LFenqX4cz61U6LYRP0LOC0lvOM4vajC4kMIEIdti5QUIx8LyHGxqbPlYsSwVwFFt62795Gdgseqhh8L_6e3j0jP7aB1h_TntfMBv-u9pWqFpCiqfsF0b4zialNFckNThGRo9b4dCI2deeLVj5-PhRnPUjeT950Npp8eM7-eh4oM5kGVeBLyYsxWoH3BWyabqYQ6TeoaOLF-b3YJELpNFdo6Zy6OwOW5EKN2KSiNWAnyftGQjgft3dk_Xcg22YtmzotTr7bQGwIQ