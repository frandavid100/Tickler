package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;


// TODO: Check if code adheres to Code Style Guidelines as per http://source.android.com/source/code-style.html

public class TaskEditorActivity extends Activity {
	
	// Dialog identifiers
	private static final int STARTDATE_DIALOG = 1000, DEADLINE_DIALOG = 1001, RECURRENCE_DIALOG = 1002;
	private long task_id;
	private Task tsk;
	
	private AutoCompleteTextView txtTaskName; 
	private AutoCompleteTextView txtTaskContexts; 
	private TextView txtTaskNotes;
	private RadioButton rbPriority1;
	private RadioButton rbPriority2;
	private RadioButton rbPriority3;
	private Button btnStart;
	private Button btnDeadline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_editor);
		
		task_id = getIntent().getLongExtra("task_id", -1);
		
		tsk = new Task(task_id, TaskEditorActivity.this);
		
		findViews();
		initViews();
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

	// Calls to optional settings dialogues. 
	// TODO: Dialog initialization data should be passed into the extras Bundle.
	
	private void callStartDateOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorStartDateDialog.class);
		intent.putExtra("dateStart", Utilities.date2String(tsk.getStartDate(),1));
		intent.putExtra("Someday", tsk.getSomeday());
		startActivityForResult(intent, STARTDATE_DIALOG);
	}
	
	private void callDeadlineOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorDeadlineDialog.class);
		intent.putExtra("date", tsk.getDeadline());
		startActivityForResult(intent, DEADLINE_DIALOG);
	}	

	private void callRecurrenceOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorRecurrenceDialog.class);
		startActivityForResult(intent, RECURRENCE_DIALOG);
	}
	
	private void findViews() {
		txtTaskName 	= (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_task_name);
		txtTaskContexts = (AutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView_contexts);
		txtTaskNotes	= (TextView) findViewById(R.id.editText_task_notes);
		rbPriority1		= (RadioButton) findViewById(R.id.radio_priority_1);
		rbPriority2		= (RadioButton) findViewById(R.id.radio_priority_2);
		rbPriority3		= (RadioButton) findViewById(R.id.radio_priority_3);
		btnStart		= (Button) findViewById(R.id.button_task_startdate_options);
		btnDeadline		= (Button) findViewById(R.id.button_task_deadline_options);
	}
	
	private void initViews() {
		String txtBtnStart;
		String txtBtnDeadline;
		
		txtTaskName.setText(tsk.getName());
		txtTaskContexts.setText(getNameContexts(tsk));
		txtTaskNotes.setText("PRUEBA");
		
		if (tsk.getPriority() == 1)
			rbPriority1.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
		if (tsk.getPriority() == 2)
			rbPriority2.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
		if (tsk.getPriority() == 3)
			rbPriority3.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
		
		//Set priority checkbox
		rbPriority2.setChecked(tsk.getPriority() == 2);
		rbPriority3.setChecked(tsk.getPriority() == 2);
		
		// START DATE
		if (tsk.getSomeday() == 1) // Someday
			txtBtnStart = getResources().getStringArray(R.array.task_editor_startdate_button_texts)[1];
		else if (tsk.getStartDate() != null)
			txtBtnStart = getResources().getStringArray(R.array.task_editor_startdate_button_texts)[2].replace("%1$", tsk.getStartDate().toLocaleString());
		else
			txtBtnStart = getResources().getStringArray(R.array.task_editor_startdate_button_texts)[0];
		
		// DEADLINE
		if (tsk.getDeadline() != null)
			txtBtnDeadline = getResources().getStringArray(R.array.task_editor_deadline_button_texts)[1].replace("%1$", tsk.getDeadline().toLocaleString());
		else
			txtBtnDeadline = getResources().getStringArray(R.array.task_editor_deadline_button_texts)[0];
		
		btnStart.setText((CharSequence)txtBtnStart);
		btnDeadline.setText((CharSequence)txtBtnDeadline);
	}
	
    private String getNameContexts(Task tsk) {
    	String res = "";
    	ArrayList<ContextTask> al = new ArrayList<ContextTask>();
    	
    	al = tsk.getContexts();
    	for (int i = 0; i < al.size(); i++) {
    		ContextTask ct = al.get(i);
    		
    		res += ct.getName() + "-";
    	}
    	if (res.length() > 0)
    		res = res.substring(0, res.length()-1);
    	
    	return res;
    }
}
