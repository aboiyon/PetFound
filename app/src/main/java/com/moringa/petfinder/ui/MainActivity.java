package com.moringa.petfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.petfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.findPetsButton) Button mFindPetsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.savedPetButton) Button savedPetButton;
//    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindPetsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == mFindPetsButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, PetListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }

    }
}
