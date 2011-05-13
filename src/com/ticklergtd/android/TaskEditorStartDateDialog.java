package com.ticklergtd.android;

import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TaskEditorStartDateDialog extends Activity  implements OnClickListener {
	RadioGroup startDateOptions;
	DatePicker specificDate;
	String startDate;
	int someday;
	Date formatDate;
	Button btnCancel;
	Button btnOK;
	TaskEditorActivity tskAct = new TaskEditorActivity();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_editor_startdate_dialog);
		
		startDate 	= getIntent().getStringExtra("dateStart");
		formatDate	= Utilities.string2Date(startDate,"dd/MM/yyyy");
		someday		= getIntent().getIntExtra("Someday",0);

		findViews();
		initViews();
		setListeners();
	}
	/*@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radio_specific:
			specificDate.setEnabled(true);
			break;
			
		default:
			specificDate.setEnabled(false);
			break;
		}
		
	}
	*/
	
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_cancel:
				setResult(RESULT_CANCELED,null);
				finish();
				break;
				
			case R.id.button_set:
				saveScreenData();
				setResult(RESULT_OK,null);
				finish();
				break;
		}
	}
	
	private void findViews() {
		specificDate 		= (DatePicker) findViewById(R.id.datePicker_specific);
		startDateOptions 	= (RadioGroup) findViewById(R.id.radioGroup_startDate_options);
		btnCancel			= (Button) findViewById(R.id.button_cancel);
		btnOK				= (Button) findViewById(R.id.button_set);
	}
	
	private void initViews() {
		if (someday == 1) {
			RadioButton rb = (RadioButton)startDateOptions.getChildAt(1);
			rb.setChecked(true);
		}
		else if (startDate.equals("")) {
				RadioButton rb = (RadioButton)startDateOptions.getChildAt(0);
				rb.setChecked(true);
		}
		else{
			RadioButton rb = (RadioButton)startDateOptions.getChildAt(2);
			rb.setChecked(true);
			
			specificDate.updateDate(formatDate.getYear() + 1900,formatDate.getMonth() ,formatDate.getDate());
		}		
	}
	
	private void setListeners() {
		btnCancel.setOnClickListener(this);	
		btnOK.setOnClickListener(this);	
	}

	private void saveScreenData() {
		String res;
		Date auxDate = new Date(specificDate.getYear()-1900,specificDate.getMonth(),specificDate.getDayOfMonth());
		SharedPreferences.Editor editor = getSharedPreferences("task",0).edit();
		editor.putString("task_startdate", Utilities.date2String(auxDate, 1));
        editor.commit();
	}
}
