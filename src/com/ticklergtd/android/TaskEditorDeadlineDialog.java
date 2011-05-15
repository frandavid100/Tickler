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

public class TaskEditorDeadlineDialog extends Activity  implements OnClickListener {
	RadioGroup deadlineDateOptions;
	DatePicker specificDeadline;
	String dateDeadline;
	int nodeadline;
	Date formatDate;
	Button btnCancel;
	Button btnOK;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_editor_deadline_dialog);
		
		dateDeadline 	= getIntent().getStringExtra("dateDeadline");
		formatDate		= Utilities.string2Date(dateDeadline,"dd/MM/yyyy");

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
		specificDeadline 	= (DatePicker) findViewById(R.id.datePicker_specific);
		deadlineDateOptions	= (RadioGroup) findViewById(R.id.radioGroup_deadline_options);
		btnCancel			= (Button) findViewById(R.id.button_cancel);
		btnOK				= (Button) findViewById(R.id.button_set);
	}
	
	private void initViews() {
		if (dateDeadline.equals("")) {
			RadioButton rb = (RadioButton)deadlineDateOptions.getChildAt(0);
			rb.setChecked(true);
			nodeadline = 1;
			specificDeadline.setEnabled(false);
		}
		else {
			RadioButton rb = (RadioButton)deadlineDateOptions.getChildAt(1);
			rb.setChecked(true);
			nodeadline = 0;
			specificDeadline.setEnabled(true);
			
			specificDeadline.updateDate(formatDate.getYear() + 1900,formatDate.getMonth() ,formatDate.getDate());
		}		
	}
	
	private void setListeners() {
		btnCancel.setOnClickListener(this);	
		btnOK.setOnClickListener(this);	
		deadlineDateOptions.setOnCheckedChangeListener( new OnCheckedChangeListener() {
 														@Override
														public void onCheckedChanged(
																RadioGroup group,
																int checkedId) {
															// TODO Auto-generated method stub
 															RadioButton rb = (RadioButton)deadlineDateOptions.getChildAt(1);
 															if (rb.isChecked()) {
 																specificDeadline.setEnabled(true);
 															}
 															else {
 																specificDeadline.setEnabled(false);
 															}
														}
													});
	}

	private void saveScreenData() {
		Date auxDate = new Date(specificDeadline.getYear()-1900,specificDeadline.getMonth(),specificDeadline.getDayOfMonth());
		RadioButton rb = (RadioButton)deadlineDateOptions.getChildAt(0);
		
		if (rb.isChecked()) {
			nodeadline = 1;
		}
		else {
			nodeadline = 0;
		}
		
		SharedPreferences.Editor editor = getSharedPreferences("task",0).edit();
		if (nodeadline == 0) {
			editor.putString("task_deadlinedate", Utilities.date2String(auxDate, 1));
		}
		else {
			editor.putString("task_deadlinedate", "");
		}		
        editor.commit();
	}
}
