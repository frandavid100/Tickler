package com.ticklergtd.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioGroup;

public class TaskEditorStartDateDialog extends Activity implements RadioGroup.OnCheckedChangeListener {
	RadioGroup startDateOptions;
	DatePicker specificDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_editor_startdate_dialog);
		specificDate = (DatePicker) findViewById(R.id.datePicker_specific);
		startDateOptions = (RadioGroup) findViewById(R.id.radioGroup_startDate_options);
		startDateOptions.setOnCheckedChangeListener(this);
	}
	@Override
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
}
