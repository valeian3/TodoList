package com.example.todolist;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityTaskDetailsTest {

    @Rule
    public ActivityTestRule<ActivityTaskDetails> mActivityTestRule = new ActivityTestRule<ActivityTaskDetails>(ActivityTaskDetails.class);

    private ActivityTaskDetails mActivityTaskDetails = null;

    @Test
    public void testLaunchActivityTaskDetails(){

        View viewFragmentText = mActivityTaskDetails.findViewById(R.id.flText);
        View viewFragmentList = mActivityTaskDetails.findViewById(R.id.flList);

        assertNotNull(viewFragmentText);
        assertNotNull(viewFragmentList);

    }

    @Before
    public void setUp() throws Exception {

        mActivityTaskDetails = mActivityTestRule.getActivity();

    }

    @After
    public void tearDown() throws Exception {

        mActivityTaskDetails = null;

    }
}