package com.moringa.petfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringa.petfinder.PetListActivity;
import com.moringa.petfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity {
    @BindView(R.id.findPetsButton) Button mFindPetsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

        mFindPetsButton.setOnclickListener(this);
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
