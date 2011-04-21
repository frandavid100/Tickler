package com.ticklergtd.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.ticklergtd.android.model.TicklerDBAdapter;

public class HomeActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	TicklerDBAdapter tck = new TicklerDBAdapter(HomeActivity.this);
    	tck.open();
    	tck.close();
    	/*Cursor objRs = tck.selectTasks();
    	ArrayList<Task> tsk = Task.getTasks(HomeActivity.this); */
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Set up click listeners for all the buttons
		View addButton = findViewById(R.id.btn_title_add);
		addButton.setOnClickListener(this);

    }
    
    // Callback de pulsación sobre los botones
    public void onClick(View v) {
    	switch (v.getId()) {
	    	case R.id.btn_title_add:
	    		Intent i = new Intent(this, ListTest.class);
        		startActivity(i);
	    		break;	    	
    	}
    }
  
}
