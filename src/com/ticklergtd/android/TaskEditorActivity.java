package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.ticklergtd.android.ListsActivity.IconicAdapter;
import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;


// TODO: Check if code adheres to Code Style Guidelines as per http://source.android.com/source/code-style.html

public class TaskEditorActivity extends Activity implements Runnable{
	
	// Dialog identifiers
	private static final int STARTDATE_DIALOG = 1000, DEADLINE_DIALOG = 1001, RECURRENCE_DIALOG = 1002;
	private long task_id;
	Task tsk;
	private Thread workerThread =new Thread(this);
	
	private AutoCompleteTextView txtTaskName; 
	private AutoCompleteTextView txtTaskContexts; 
	private TextView txtTaskNotes;
	private RadioGroup priorityOptions;
	private RadioButton rbPriority1;
	private RadioButton rbPriority2;
	private RadioButton rbPriority3;
	private Button btnStart;
	private Button btnDeadline;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_editor);
		
		findViews();
		setListeners();
		initDataSet();
	}
	
	private void initDataSet() {
		workerThread.start();
	}

	@Override
	public void run() {
		task_id = getIntent().getLongExtra("task_id", -1);
		tsk = new Task(task_id, TaskEditorActivity.this);
		handler.sendEmptyMessage(0);
	}
	
	private Handler handler = new Handler() {
		@Override
    	public void handleMessage(Message msg) {
			initViews();
    	}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == STARTDATE_DIALOG) {
			if(resultCode==RESULT_OK) {
				loadScreenData();
			}
			else if(resultCode==RESULT_CANCELED) {
			}
		}
		else if (requestCode == DEADLINE_DIALOG) {
			if(resultCode==RESULT_OK) {
				loadScreenData();
			}
			else if(resultCode==RESULT_CANCELED) {
			}
		} 
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

	public void taskEditorDoneListener(View v) {
		callEditorDone();
	}

	// Calls to optional settings dialogues. 
	// TODO: Dialog initialization data should be passed into the extras Bundle.
	
	private void callStartDateOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorStartDateDialog.class);
		intent.putExtra("dateStart", Utilities.date2String(tsk.getStartDate(),1));
		intent.putExtra("someday", tsk.getSomeday());
		saveScreenData();
		startActivityForResult(intent, STARTDATE_DIALOG);
	}
	
	private void callDeadlineOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorDeadlineDialog.class);
		intent.putExtra("dateDeadline", Utilities.date2String(tsk.getDeadline(),1));
		saveScreenData();
		startActivityForResult(intent, DEADLINE_DIALOG);
	}	

	private void callRecurrenceOptionsDialog() {
		Intent intent = new Intent(this,TaskEditorRecurrenceDialog.class);
		startActivityForResult(intent, RECURRENCE_DIALOG);
	}
	
	private void callEditorDone() {
		long lRes = tsk.save();
	}
	
	private void findViews() {
		txtTaskName 	= (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_task_name);
		txtTaskContexts = (AutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView_contexts);
		txtTaskNotes	= (TextView) findViewById(R.id.editText_task_notes);
		priorityOptions = (RadioGroup) findViewById(R.id.radioGroup_task_priority);
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
		txtTaskNotes.setText(tsk.getNote());
		
		switch (tsk.getPriority()){
		case 1:
			rbPriority1.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
			rbPriority1.setChecked(true);
			break;
		case 2:
			rbPriority2.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
			rbPriority2.setChecked(true);
			break;
		case 3:
			rbPriority3.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
			rbPriority3.setChecked(true);
			break;
		default:
			rbPriority2.setChecked(true);
			break;
		}
		
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
	
	private void setListeners() {
		priorityOptions.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(
				RadioGroup group,
				int checkedId) {
				RadioButton rb1 = (RadioButton) priorityOptions.getChildAt(0);
				RadioButton rb2 = (RadioButton) priorityOptions.getChildAt(1);
				RadioButton rb3 = (RadioButton) priorityOptions.getChildAt(2);
				if (rb1.isChecked()) {
					rbPriority1.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
					rbPriority2.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_background));
					rbPriority3.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_background));
					tsk.setPriority(1);
				}
				else if (rb2.isChecked()) {
					rbPriority1.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_background));
					rbPriority2.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
					rbPriority3.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_background));
					tsk.setPriority(2);
				}
				else if (rb3.isChecked()) {
					rbPriority1.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_background));
					rbPriority2.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_background));
					rbPriority3.setBackgroundDrawable(getResources().getDrawable(R.drawable.titlebar_item_pressed));
					tsk.setPriority(3);
				}
			}
		});
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

	private void saveScreenData() {
		SharedPreferences.Editor editor = getSharedPreferences("task",0).edit();
        editor.putString("task_name", txtTaskName.getText().toString());
        editor.putString("task_note", txtTaskNotes.getText().toString());
        editor.putString("task_startdate", Utilities.date2String(tsk.getStartDate(),1));
        editor.putString("task_deadline", Utilities.date2String(tsk.getDeadline(),1));
        editor.putInt("task_someday", tsk.getSomeday());
        editor.putInt("task_priority", tsk.getPriority());
        editor.commit();
	}
	
	private void loadScreenData() {
		SharedPreferences prefs = getSharedPreferences("task",0); 
        String restoredText;
        int restoredInt;
        
        restoredText = prefs.getString("tasḱ_name", null);
        if (restoredText != null) {
        	tsk.setName(restoredText);
        }
        
        restoredText = prefs.getString("task_note", null);
        if (restoredText != null) {
        	tsk.setNote(restoredText);
        }
        
        restoredText = prefs.getString("task_startdate", null);
        if (restoredText != null) {
        	if (!restoredText.equals("")) {
        		tsk.setStartDate(Utilities.string2Date(restoredText,"dd/MM/yyyy"));
        		tsk.setSomeday(0);
        	}	
        	else {
        		tsk.setStartDate(null);
        	}
        }
        
        restoredText = prefs.getString("task_deadlinedate", null);
        if (restoredText != null) {
        	if (!restoredText.equals("")) {
        		tsk.setDeadline(Utilities.string2Date(restoredText,"dd/MM/yyyy"));
        	}	
        	else {
        		tsk.setDeadline(null);
        	}
        }
        
        restoredInt = prefs.getInt("task_someday", 0);
        tsk.setSomeday(restoredInt);
        
        restoredInt = prefs.getInt("task_priority", 2);
        tsk.setPriority(restoredInt);
        
        initViews();
	}
}


