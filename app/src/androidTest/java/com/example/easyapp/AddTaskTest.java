package com.example.easyapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTaskTest {
    @Rule
    public ActivityTestRule<AddTask> addTaskTestRule = new ActivityTestRule<AddTask>(AddTask.class);
    private AddTask addTask = null;

    @Before
    public void setUp() throws Exception {
        addTask = addTaskTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = addTask.findViewById(R.id.newtask);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        addTask = null;
    }
}