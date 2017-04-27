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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.tvDemoName), withText("Rotate Image demo"), isDisplayed()));
        appCompatTextView.perform(click());

        pressBack();

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.tvDemoName), withText("Observer pattern demo"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etDecimalValue), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etDecimalValue), isDisplayed()));
        appCompatEditText2.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnConvert), withText("Convert"), isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

    }

}
