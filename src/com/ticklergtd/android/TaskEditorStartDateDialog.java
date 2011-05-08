package com.ticklergtd.android;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TaskEditorStartDateDialog extends Activity {
	RadioGroup startDateOptions;
	DatePicker specificDate;
	String startDate;
	int someday;
	final Calendar c = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_editor_startdate_dialog);
		
		startDate 	= getIntent().getStringExtra("dateStart");
		someday		= getIntent().getIntExtra("Someday",0);

		findViews();
		initViews();
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
	private void findViews() {
		specificDate 		= (DatePicker) findViewById(R.id.datePicker_specific);
		startDateOptions 	= (RadioGroup) findViewById(R.id.radioGroup_startDate_options);
		//startDateOptions.setOnCheckedChangeListener(this);
	}
	
	private void initViews() {
		if (someday == 1) {
			RadioButton rb = (RadioButton)startDateOptions.getChildAt(1);
			rb.setSelected(true);
		}
		else if (startDate.equals("")) {
				RadioButton rb = (RadioButton)startDateOptions.getChildAt(0);
				rb.setSelected(true);
		}
		else{
			RadioButton rb = (RadioButton)startDateOptions.getChildAt(2);
			rb.setSelected(true);
			
			specificDate.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
		}
	}
}
