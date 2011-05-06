package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;

public class ViewFlipTest extends Activity implements OnClickListener {
	
	float downXvalue;
	private ArrayList<String> mStrings_full;
	private ArrayList<String> mStrings_smart;
	private ArrayList<Task> tsk_full = new ArrayList<Task>();
	private ArrayList<Task> tsk_smart = new ArrayList<Task>();
	private ViewFlipper listsViewFlipper = null;
	private ListView lvSmart;
	private ListView lvFull;
	private TextView txtView;
	private View addNewTask;
	
	static final String SMART_LIST 	= "SMART LIST";
	static final String FULL_LIST 	= "FULL LIST";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_lists);
		
        findViews();
		initViews();
		setListeners();
		
	}
	
	// Some tests with viewFlipper in order to switch between Smart List and Full List
	// TODO: Remove this code and upgrade to something similar to Launcher. Drag and slide views by using gestures.
	@Override
	public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.textView_View_Title:
            {
            	if (isFullList()) {
            		listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                    ((ViewAnimator) listsViewFlipper).showPrevious();
                    
                    txtView.setText(SMART_LIST);
                }
            	else if (isSmartList()) {
            		listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                    ((ViewAnimator) listsViewFlipper).showNext();
                    
                    txtView.setText(FULL_LIST);
                }

                break;
            }
            case R.id.button_Title_Bar_Add_New_Task:
            {
            	callTaskEditorActivity();
            	break;
            }
        }
    }

	/* **************************************
	 * PRIVATE FUNCTIONS 
	 * **************************************/
	
	private void findViews() {
		lvSmart = (ListView) findViewById(R.id.list_smart);
		lvFull 	= (ListView) findViewById(R.id.list_full);
		listsViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
		txtView = (TextView)findViewById(R.id.textView_View_Title);
		addNewTask = findViewById(R.id.button_Title_Bar_Add_New_Task);
	}
	
	private void initViews() {
        // Recupera una lista de tareas
        tsk_full = Task.getTasks(ViewFlipTest.this,1);
        tsk_smart = Task.getTasks(ViewFlipTest.this,2);

        // Y ya en una función local, compongo los strings como se necesite
        mStrings_full = getStringsFromTasks(tsk_full);
        mStrings_smart = getStringsFromTasks(tsk_smart);
        
        lvSmart.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings_smart));
        lvSmart.setTextFilterEnabled(true);
        
        lvFull.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings_full));
        lvFull.setTextFilterEnabled(true);
        
        txtView.setText(SMART_LIST);
	}
	
	private void setListeners() {
		txtView.setOnClickListener(this);
		addNewTask.setOnClickListener(this);
	}
	
	// Calls to each optional settings dialog.
	private void callTaskEditorActivity() {
		Intent taskEditorIntent = new Intent(this,TaskEditorActivity.class);
		startActivity(taskEditorIntent);	
	}
	
	private boolean isSmartList () {
		boolean bRes = false;
		
		bRes = (txtView.getText().toString().toUpperCase().equals(SMART_LIST));
		return bRes;
	}
	
	private boolean isFullList () {
		boolean bRes = false;
		
		bRes = (txtView.getText().toString().toUpperCase().equals(FULL_LIST));
		return bRes;
	}
	
    // De una lista de tareas, genera una lista de strings con la información
    // ... que necesitemos
    private ArrayList<String> getStringsFromTasks(ArrayList<Task> al) {
    	
    	int len = al.size();
    	ArrayList<String> res = new ArrayList<String>();
    	
    	for (int i = 0; i < len; i++) {
    		Task tsk = al.get(i);
    		
    		String sItemList = getItemList(tsk);
    		res.add(sItemList);
    	}
    	
    	return res;
    }
    
    // Esta es la función que compone los strings en la forma necesaria. 
    private String getItemList(Task tsk) {
    	String res = "";
    	
    	res = tsk.getName();
    	
    	if (tsk.getStartDate() != null) {
    		String aux = " - " + Utilities.date2String(tsk.getStartDate(),2) + " - (" + getNameContexts(tsk) + ")";
    		res += aux;
    	}
    	
    	return res;
    }
    
    private String getNameContexts(Task tsk) {
    	String res = "";
    	ArrayList<ContextTask> al = new ArrayList<ContextTask>();
    	
    	al = tsk.getContexts();
    	for (int i = 0; i < al.size(); i++) {
    		ContextTask ct = al.get(i);
    		
    		res += ct.getName() + "-";
    	}
    	
    	return res;
    }
}
