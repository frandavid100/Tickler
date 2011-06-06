package com.ticklergtd.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;

public class ListContextActivity extends ListActivity  {
	/** Called with the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
        setContentView(R.layout.task_lists2);
	}
}