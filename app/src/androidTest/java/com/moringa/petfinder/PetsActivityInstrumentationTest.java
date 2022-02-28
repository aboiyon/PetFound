package com.moringa.petfinder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PetsActivityInstrumentationTest {
    @Rule
    public ActivityScenarioRule<PetsActivity> activityTestRule =
            new ActivityScenarioRule<>(PetsActivity.class);

    private View activityDecorView;

    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<PetsActivity>() {
            @Override
            public void perform(PetsActivity activity) {
                activityDecorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void listItemClickDisplaysToastWithCorrectPet(){
        String restaurantName = "Bosco";
        onData(anything())
                .inAdapterView(withId(R.id.locationListView))
                .atPosition(0)
                .perform(click());
        onView(withText(restaurantName))
                .inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(restaurantName)));
    }

}