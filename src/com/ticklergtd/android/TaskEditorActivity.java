package com.ticklergtd.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


// TODO: Check if code adheres to Code Style Guidelines as per http://source.android.com/source/code-style.html

public class TaskEditorActivity extends Activity {
	
	// Dialog identifiers
	private static final int STARTDATE_DIALOG = 0, DEADLINE_DIALOG = 1, RECURRENCE_DIALOG = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_editor);
	}
	
	// Event handlers for optional settings buttons OnClick method, defined in task_editor.xml
	
	public void startDateOptionsListener(View v){
		callStartDateOptionsDialog();
	}
	
	public void deadlineOptionsListener(View v){
		callDeadlineOptionsDialog();
	}

	public void recurrenceOptionsListener(View v){
		callRecurrenceOptionsDialog();
	}

	// Calls to each optional settings dialogues. 
	// TODO: Dialog initialization data should be passed into the extras Bundle.
	
	private void callStartDateOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorStartDateDialog.class);
		startActivityForResult(intent, STARTDATE_DIALOG);
	}
	
	private void callDeadlineOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorDeadlineDialog.class);
		startActivityForResult(intent, DEADLINE_DIALOG);
	}	

	private void callRecurrenceOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorRecurrenceDialog.class);
		startActivityForResult(intent, RECURRENCE_DIALOG);
	}
	
}
