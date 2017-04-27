package com.selflearning;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.tvDemoName), withText("Swift perfect demo"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonApiCall), withText("Button"), isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.tvDemoName), withText("Localization demo"), isDisplayed()));
        appCompatTextView2.perform(click());

        pressBack();

    }

}
