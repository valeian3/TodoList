package com.example.todolist;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
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

public class NewTaskTest {

    @Rule
    public ActivityTestRule<NewTask> mActivityTestRule = new ActivityTestRule<NewTask>(NewTask.class);

    private NewTask mNewTask = null;

//    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
//
//    @Test
//    public void testButton(){
//        assertNotNull(mNewTask.findViewById(R.id.btnAddTask));
//        onView(withId(R.id.btnAddTask)).perform(click());
//
//        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
//
//        assertNotNull(secondActivity);
//    }

    @Test
    public void testLaunchNewTaskTest(){

        View viewTvName = mNewTask.findViewById(R.id.tvEnterName);
        View viewEtName = mNewTask.findViewById(R.id.etEnterName);
        View viewBtnAddTask = mNewTask.findViewById(R.id.btnAddTask);

        assertNotNull(viewTvName);
        assertNotNull(viewEtName);
        assertNotNull(viewBtnAddTask);

    }

    @Before
    public void setUp() throws Exception {

        mNewTask = mActivityTestRule.getActivity();

    }

    @After
    public void tearDown() throws Exception {

        mNewTask = null;

    }
}