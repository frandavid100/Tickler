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
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TaskEditorStartDateDialog extends Activity  implements OnClickListener {
	private RadioGroup startDateOptions;
	private DatePicker specificDate;
	private String startDate;
	private int someday;
	private int anytime;
	private Date formatDate;
	private Button btnCancel;
	private Button btnOK;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_editor_startdate_dialog);
		
		startDate 	= getIntent().getStringExtra("dateStart");
		formatDate	= Utilities.string2Date(startDate,"dd/MM/yyyy");
		someday		= getIntent().getIntExtra("someday",0);

		findViews();
		initViews();
		setListeners();
	}
	
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
			anytime = 0;
			specificDate.setEnabled(false);
		}
		else if (startDate.equals("")) {
			RadioButton rb = (RadioButton)startDateOptions.getChildAt(0);
			rb.setChecked(true);
			anytime = 1;
			someday = 0;
			specificDate.setEnabled(false);
		}
		else {
			RadioButton rb = (RadioButton)startDateOptions.getChildAt(2);
			rb.setChecked(true);
			anytime = 0;
			someday = 0;
			specificDate.setEnabled(true);
			
			specificDate.updateDate(formatDate.getYear() + 1900,formatDate.getMonth() ,formatDate.getDate());
		}		
	}
	
	private void setListeners() {
		btnCancel.setOnClickListener(this);	
		btnOK.setOnClickListener(this);	
		startDateOptions.setOnCheckedChangeListener( new OnCheckedChangeListener() {
 														@Override
														public void onCheckedChanged(
																RadioGroup group,
																int checkedId) {
															// TODO Auto-generated method stub
 															RadioButton rb = (RadioButton)startDateOptions.getChildAt(2);
 															if (rb.isChecked()) {
 																specificDate.setEnabled(true);
 															}
 															else {
 																specificDate.setEnabled(false);
 															}
														}
													});
	}

	private void saveScreenData() {
		Date auxDate = new Date(specificDate.getYear()-1900,specificDate.getMonth(),specificDate.getDayOfMonth());
		RadioButton rbAnyTime = (RadioButton)startDateOptions.getChildAt(0);
		RadioButton rbSomeDay = (RadioButton)startDateOptions.getChildAt(1);
		
		anytime = rbAnyTime.isChecked()?1:0;
		someday = rbSomeDay.isChecked()?1:0;
		
		SharedPreferences.Editor editor = getSharedPreferences("task",0).edit();
		if (anytime == 0) {
			editor.putString("task_startdate", Utilities.date2String(auxDate, 1));
		}
		else {
			editor.putString("task_startdate", "");
		}
		editor.putInt("task_someday", someday);
			
        editor.commit();
	}
}
