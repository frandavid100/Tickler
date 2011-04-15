package com.ticklergtd.android;

import android.app.Activity;
import android.os.Bundle;

import com.ticklergtd.android.model.TicklerDBAdapter;

public class HomeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	TicklerDBAdapter tck = new TicklerDBAdapter(HomeActivity.this);
    	tck.open();
    	tck.close();
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
