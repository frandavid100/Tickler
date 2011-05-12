package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;

public class ListsActivity extends Activity implements OnClickListener {
	
	private ArrayList<String> mStrings_full;
	private ArrayList<String> mStrings_smart;
	private ArrayList<Task> tsk_full 		= new ArrayList<Task>();
	private ArrayList<Task> tsk_smart 		= new ArrayList<Task>();
	private ArrayList<Task> tsk_current 	= new ArrayList<Task>();
	private ViewFlipper listsViewFlipper 	= null;
	private ListView lvSmart;
	private ListView lvFull;
	private TextView txtView;
	private View addNewTask;

	static final int SMART 	= 1;
	static final int FULL 	= 2;
	
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
                    
                    tsk_current = tsk_smart;
                    
                    txtView.setText(R.string.smart_list_view_title);
                }
            	else if (isSmartList()) {
            		listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                    ((ViewAnimator) listsViewFlipper).showNext();
                    
                    tsk_current = tsk_full;
                    
                    txtView.setText(R.string.full_list_view_title);
                }

                break;
            }
            case R.id.button_Title_Bar_Add_New_Task:
            {
            	callTaskEditorActivity(0);
            	break;
            }
        }
    }
	
	/* 
	public void onListItemClick(View v, int position) {
		
		long lTaskId = 0;
		
		switch (v.getId()) {
			case R.id.list_full:
				lTaskId = getTaskIdFromList(tsk_full, position);
				break;
			case R.id.list_smart:
				lTaskId = getTaskIdFromList(tsk_smart, position);
				break;
		}
		
		callTaskEditorActivity(lTaskId);
	}
	*/

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
        tsk_full = Task.getTasks(ListsActivity.this,FULL);
        tsk_smart = Task.getTasks(ListsActivity.this,SMART);
        
        tsk_current = tsk_smart;

        // Y ya en una función local, compongo los strings como se necesite
        mStrings_full = getStringsFromTasks(tsk_full);
        mStrings_smart = getStringsFromTasks(tsk_smart);
        
        lvSmart.setAdapter(new IconicAdapter(mStrings_smart));
        lvSmart.setTextFilterEnabled(true);
        
        lvFull.setAdapter(new IconicAdapter(mStrings_full));
        lvFull.setTextFilterEnabled(true);
        
        txtView.setText(R.string.smart_list_view_title);
	}
	
	private void setListeners() {
		txtView.setOnClickListener(this);
		addNewTask.setOnClickListener(this);
		/*lvSmart.setOnClickListener(this);
		lvFull.setOnClickListener(this);
		
		lvSmart.setOnItemClickListener(new ListView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
	            try {
	                // Remembers the selected Index
	            	onListItemClick(lvSmart,i);
	            }
	            catch(Exception e) {
	                System.out.println("Nay, cannot get the selected index");
	            }
	        }
		});

		lvFull.setOnItemClickListener(new ListView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
	            try {
	                // Remembers the selected Index
	            	onListItemClick(lvFull,i);
	            }
	            catch(Exception e) {
	                System.out.println("Nay, cannot get the selected index");
	            }
	        }
		});
	*/
	}
	
	// Calls to each optional settings dialog.
	private void callTaskEditorActivity(long task_id) {
		Intent taskEditorIntent = new Intent(this,TaskEditorActivity.class);
		
		if (task_id > 0) {
			taskEditorIntent.putExtra("task_id",task_id);
		}
		startActivity(taskEditorIntent);	
	}
	
	private boolean isSmartList () {
		boolean bRes = false;
		
		bRes = (txtView.getText().toString().equals(getResources().getString(R.string.smart_list_view_title)));
		return bRes;
	}
	
	private boolean isFullList () {
		boolean bRes = false;
		
		bRes = (txtView.getText().toString().equals(getResources().getString(R.string.full_list_view_title)));
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
    		String aux = " - " + Utilities.date2String(tsk.getStartDate(),2) + "##" + getNameContexts(tsk);
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
    	
    	res = res.substring(0,res.length() - 1);
    	
    	return res;
    }
    
    private long getTaskIdFromList(ArrayList<Task> tsk, int position) {
    	Task auxTask;
    	long lRes = 0;
    	
    	auxTask = tsk.get(position);
    	
    	if (auxTask != null) {
    		lRes = auxTask.getId();
    	}
    	
    	return lRes;
    }
    
    class IconicAdapter extends ArrayAdapter<String> {
    	private ArrayList<String> localArray;
    	private int rowpos;
    	IconicAdapter(ArrayList<String> mStrings) {
        	super(ListsActivity.this, R.layout.task_lists_row, R.id.textView_lists_row_task_name, mStrings);
        	localArray = mStrings;
        }
        
        public View getView(int position, View convertView, ViewGroup parent) {
        	
        	String sName		= "";
        	String sContexts	= "";
        	int iPriority		= 2;
        	
        	int currentListID;
        	View row=super.getView(position, convertView, parent);
        	currentListID=parent.getId();
        	
        	// TODO: Set "imageView_lists_row_priority_level" background color depending on task priority;
        	ImageView priorityLevel 	= (ImageView)row.findViewById(R.id.imageView_lists_row_priority_level);
        	CheckBox chkTaskCompleted 	= (CheckBox)row.findViewById(R.id.checkBox_lists_row_task_completed);
        	TextView txtTaskName 		= (TextView)row.findViewById(R.id.textView_lists_row_task_name);
        	TextView txtTaskContexts 	= (TextView)row.findViewById(R.id.textView_lists_row_task_contexts);
        	ImageView icon				= (ImageView)row.findViewById(R.id.imageView_lists_row_edit_task);
        	
        	sName 		= tsk_current.get(position).getName();
        	sContexts 	= getNameContexts(tsk_current.get(position));
        	iPriority	= tsk_current.get(position).getPriority();
        	
        	txtTaskName.setText(sName);
        	txtTaskContexts.setText(sContexts);
        	
        	//Checkbox listener. Strikestrough this task name when checked.
        	chkTaskCompleted.setOnCheckedChangeListener(new chkCompletedListener(txtTaskName));
        	
        	//ImageView listener. Calls task editor passing this task ID through extras. 
        	icon.setOnClickListener(new iconClickListener(currentListID,position));
        	return(row);       	
        }
        
        class chkCompletedListener implements OnCheckedChangeListener{
        	private TextView txtTask;
			public chkCompletedListener(TextView txtTaskName) {
				this.txtTask = txtTaskName;
			}

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					txtTask.setPaintFlags(txtTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
				}
				else{
					txtTask.setPaintFlags(0);
				}
			}
        	
        }
        
    	class iconClickListener implements OnClickListener {
    		private int currentlist;
    		private int position;
    		private long taskID;
    		public iconClickListener(int listID, int pos) {
    			this.currentlist = listID;
    			this.position = pos;
    			this.taskID = 0;
    		}
			@Override
			public void onClick(View v) { 					
				switch (currentlist) {
					case R.id.list_full:
						taskID = getTaskIdFromList(tsk_full, position);
						break;
					case R.id.list_smart:
						taskID = getTaskIdFromList(tsk_smart, position);
						break;
				}	
				callTaskEditorActivity((int)taskID);
			}
    	}

		private String getItemParts(String pOriginal, int i) {
        	int iPos = 0;
        	String name = "";
        	String contexts = "";
        	
        	iPos = pOriginal.indexOf("##");
        	if (iPos > 0) {
        		name 		= pOriginal.substring(0, iPos - 1);
        		contexts 	= pOriginal.substring(iPos + 2);
        	}
        	else {
        		name 		= pOriginal;
        		contexts 	= "";
        	}
        	
        	if (i == 1) {
        		return name;
        	}
        	else {
        		return contexts;
        	}
        }
    }
}
