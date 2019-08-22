package com.example.todolist;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(NewTask.class.getName(), null, false);

    @Test
    public void testButton(){
        assertNotNull(mActivity.findViewById(R.id.fab));
        onView(withId(R.id.fab)).perform(click());

        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(secondActivity);
    }

    @Test
    public void testLaunchMainActivity(){

        View viewFab = mActivity.findViewById(R.id.fab);
        View viewLv = mActivity.findViewById(R.id.lvTasks);

        assertNotNull(viewLv);
        assertNotNull(viewFab);

    }

    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();

    }

    @After
    public void tearDown() throws Exception {

        mActivity = null;

    }
}