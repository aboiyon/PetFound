package com.moringa.petfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetsActivity extends Activity {
    private String [] pets = new String[]{"Rex","Simba","Mata","T.z","Ceyos","Hesky","Bosco","South","Devs","Sparta","Java","Maven","Mambuzi","Rexxie","Ketepa"};
    private String [] breeds = new String[] {"Beagle", "Pomerarian", "Siberian Husky", "Scandinavian", "Bichon Frise", "Bichon King", "Maltese Dog", "Bulldog", "Labrador Retriever", "Mexican", "Basset Hound", "Cuban", "Pug", "Shiba Inu", "Bull Terrier" };
    private String [] sex = new String[] {"Male","Female","Male","Female","Female","Male","Female","Male","Female","Female","Male","Female","Male","Female","Female"};
    private String [] location = new String[] {"Kyiv","Kharkov","Siberia","Alps","Atlas","Pampas","Downs","Veld","Tigers","East","West","Mombasa","Nairobi","Kisumu","Kampala","Lebekwet"};
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.locationListView)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        MyPetsArrayAdapter adapter = new MyPetsArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, pets,breeds,sex,location); // the arguments must match constructor's parameters!
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener((adapterView, view, position, l) -> {
            String pet = ((TextView)view).getText().toString();
            Log.v("RestaurantsActivity", "In the onItemClickListener!");
            Toast.makeText(PetsActivity.this, pet, Toast.LENGTH_LONG).show();
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText(String.format("Hybrid pets%s", location));
        Log.d("RestaurantsActivity", "In the onCreate method!");
    }
}



