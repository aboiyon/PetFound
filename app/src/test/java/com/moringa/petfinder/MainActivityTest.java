package com.moringa.petfinder;

import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void validateTextViewContent(){
        TextView appNameTextView = activity.findViewById(R.id.appNameTextView);
        assertTrue("MyRestaurants".equals(appNameTextView.getText().toString()));
    }

    @Test
    public void secondActivityStarted(){
        activity.findViewById(R.id.findPetsButton).performClick();
        Intent expectedIntent = new Intent(activity, PetListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}