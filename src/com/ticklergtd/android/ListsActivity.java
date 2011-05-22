package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;

public class ListsActivity extends Activity implements OnClickListener,Runnable {
	
	private ArrayList<String> mStrings_full;
	private ArrayList<String> mStrings_smart;
	private ArrayList<Task> tsk_full 		= new ArrayList<Task>();
	private ArrayList<Task> tsk_smart 		= new ArrayList<Task>();
	private ArrayList<Task> tsk_someday		= new ArrayList<Task>();
	private ArrayList<Task> tsk_current 	= new ArrayList<Task>();
	private RelativeLayout listsLayout;
	private ViewFlipper listsViewFlipper 	= null;
	private ListView lvSmart;
	private ListView lvFull;
	private TextView txtView;
	private TextView lbInboxSmartLabel;
	private TextView txtInboxSmartLabel;
	private TextView lbInboxFullLabel;
	private TextView txtInboxFullLabel;
	private TextView txtSomedayLabel;
	private View addNewTask;
	private Thread workerThread =new Thread(this);
	
	static final int SMART 		= 1;
	static final int FULL 		= 2;
	static final int SOMEDAY	= 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_lists);
		
        findViews();
        setListeners();
        initDataset();     
	}
	
	protected void onResume(Bundle savedInstanceState) {
		super.onResume();
		
        initDataset();     
	}
	
	@Override
	public void onDetachedFromWindow() {
        Log.d("Dash","OnDetachedFromWindow()");

        try {
	        super.onDetachedFromWindow();
	    }
	    catch (Exception e) {
	        ViewFlipper v = (ViewFlipper)findViewById(R.id.viewFlipper_Lists_Container);
	        if (v != null) {
	                Log.d("Dash","De-Bug hit. e=" + e.getMessage());
	                v.stopFlipping();
	        }
	    }
	}

	protected void onStart(Bundle savedInstanceState) {
		super.onStart();
		
        initDataset();     
	}
	
	protected void onRestart(Bundle savedInstanceState) {
		super.onRestart();
		
        initDataset();     
	}
	
	// Some tests with viewFlipper in order to switch between Smart List and Full List
	// TODO: Remove this code and upgrade to something similar to Launcher. Drag and slide views by using gestures.
	@Override
	public void onClick(View v) {
		String sLabel1 = "";
		String sLabel2 = "";
		String sLabel3 = "";
		
        switch (v.getId())
        {
            case R.id.textView_View_Title:
            {
            	if (isFullList()) {
            		listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                    ((ViewAnimator) listsViewFlipper).showPrevious();
                    
                    tsk_current = tsk_smart;
                    
                    txtView.setText(R.string.smart_list_view_title);        
                    sLabel1 = getResources().getString(R.string.smart_list_inbox_label);
                    
                    lbInboxSmartLabel.setText(sLabel1);
                    
                	sLabel2 = String.format(getResources().getString(R.string.lists_inbox_tasks,tsk_current.size()));
                	txtInboxSmartLabel.setText(sLabel2);
                }
            	else if (isSmartList()) {
            		listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                    ((ViewAnimator) listsViewFlipper).showNext();
                    
                    tsk_current = tsk_full;
                    
                    txtView.setText(R.string.full_list_view_title);
                    sLabel1 = getResources().getString(R.string.full_list_inbox_label);
                    
                    lbInboxFullLabel.setText(sLabel1);
                    
                	sLabel2 = String.format(getResources().getString(R.string.lists_inbox_tasks,tsk_current.size()));
                	txtInboxFullLabel.setText(sLabel2);

                	sLabel3 = String.format(getResources().getString(R.string.full_list_someday_tasks,tsk_someday.size()));
                	txtSomedayLabel.setText(sLabel3);
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
	
	@Override
	public void run() {
		
		tsk_full 	= Task.getTasks(ListsActivity.this,FULL);
        tsk_smart 	= Task.getTasks(ListsActivity.this,SMART);
        tsk_someday	= Task.getTasks(ListsActivity.this,SOMEDAY);
        handler.sendEmptyMessage(0);
	}
	
	/* **************************************
	 * PRIVATE FUNCTIONS 
	 * **************************************/
	
	private void findViews() {
		lvSmart 			= (ListView) findViewById(R.id.list_smart);
		lvFull 				= (ListView) findViewById(R.id.list_full);
		listsViewFlipper 	= (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
		listsViewFlipper.setDisplayedChild(2);
		txtView 			= (TextView)findViewById(R.id.textView_View_Title);
		addNewTask 			= findViewById(R.id.button_Title_Bar_Add_New_Task);
		lbInboxSmartLabel 	= (TextView)findViewById(R.id.textView_Smart_List_Inbox_Label);
		txtInboxSmartLabel 	= (TextView)findViewById(R.id.textView_Smart_List_Inbox_Tasks);
		lbInboxFullLabel 	= (TextView)findViewById(R.id.textView_Full_List_Inbox_Label);
		txtInboxFullLabel 	= (TextView)findViewById(R.id.textView_Full_List_Inbox_Tasks);
		txtSomedayLabel 	= (TextView)findViewById(R.id.TextView_Full_List_Someday_Tasks);
	}
	
	private void initDataset() {
		// Recupera una lista de tareas 
		workerThread.start();
	}
	
	private Handler handler = new Handler() {
    	@Override
    	public void handleMessage(Message msg) {
    		String sLabel1 = "";
    		String sLabel2 = "";
    		
    		listsViewFlipper.setDisplayedChild(0);
    		//listsViewFlipper.setDisplayedChild(1);
    		tsk_current = tsk_smart;

            // Y ya en una función local, compongo los strings como se necesite
            mStrings_full = getStringsFromTasks(tsk_full);
            mStrings_smart = getStringsFromTasks(tsk_smart);
            
            lvSmart.setAdapter(new IconicAdapter(mStrings_smart));
            lvSmart.setTextFilterEnabled(true);
            
            lvFull.setAdapter(new IconicAdapter(mStrings_full));
            lvFull.setTextFilterEnabled(true);
            
            txtView.setText(R.string.smart_list_view_title);
            
            sLabel1 = getResources().getString(R.string.smart_list_inbox_label);
            lbInboxSmartLabel.setText(sLabel1);
            
        	sLabel2 = String.format(getResources().getString(R.string.lists_inbox_tasks,tsk_current.size()));
        	txtInboxSmartLabel.setText(sLabel2);
    	}
    };
    
	private void setListeners() {
		txtView.setOnClickListener(this);
		addNewTask.setOnClickListener(this);
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
    	
    	if (res.length() > 0) {
    		res = res.substring(0,res.length() - 1);
    	}
    	
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
    	IconicAdapter(ArrayList<String> mStrings) {
        	super(ListsActivity.this, R.layout.task_lists_row, R.id.textView_lists_row_task_name, mStrings);
        }
        
        public View getView(int position, View convertView, ViewGroup parent) {
        	
        	String sName		= "";
        	String sContexts	= "";
        	int iPriority		= 2;
        	int currentListID;
        	
        	View row=super.getView(position, convertView, parent);
        	
        	currentListID = parent.getId();
        	iPriority	= tsk_current.get(position).getPriority();
        	sName 		= tsk_current.get(position).getName();
        	sContexts 	= getNameContexts(tsk_current.get(position));
        	
        	ColorDrawable cd = new ColorDrawable(getPriorityColor(iPriority));
        	ImageView priorityLevel 	= (ImageView)row.findViewById(R.id.imageView_lists_row_priority_level);
        	CheckBox chkTaskCompleted 	= (CheckBox)row.findViewById(R.id.checkBox_lists_row_task_completed);
        	TextView txtTaskName 		= (TextView)row.findViewById(R.id.textView_lists_row_task_name);
        	TextView txtTaskContexts 	= (TextView)row.findViewById(R.id.textView_lists_row_task_contexts);
        	ImageView icon				= (ImageView)row.findViewById(R.id.imageView_lists_row_edit_task);
        	
        	priorityLevel.setBackgroundDrawable(cd);
        	txtTaskName.setText(sName);
        	txtTaskContexts.setText(sContexts);
        	
        	//Checkbox listener. Strikestrough this task name when checked.
        	chkTaskCompleted.setOnCheckedChangeListener(new chkCompletedListener(txtTaskName));
        	
        	//ImageView listener. Calls task editor passing this task ID through extras. 
        	icon.setOnClickListener(new iconClickListener(currentListID,position));
        	return(row);       	
        }
        
        private int getPriorityColor(int iPriority) {
			int color=0;
        	switch(iPriority) {
			case 1:
				color = getResources().getInteger(R.color.priority1);
				break;
			case 2:
				color = getResources().getInteger(R.color.priority2);
				break;
			case 3:
				color = getResources().getInteger(R.color.priority3);
				break;
			}
        	return color;
        }
        
        private void getContextIcons(String context){
        	
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
    }
    
	
}
