package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;

public class ViewFlipTest extends Activity implements OnTouchListener {
	
	float downXvalue;
	private ArrayList<String> mStrings_full;
	private ArrayList<String> mStrings_smart;
	private ArrayList<Task> tsk_full = new ArrayList<Task>();
	private ArrayList<Task> tsk_smart = new ArrayList<Task>();
	private ViewFlipper listsViewFlipper = null;
	static int flipView = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_lists);
		
		// Set a touch listener on viewflipper
        listsViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
        //listsViewFlipper.setOnTouchListener((OnTouchListener) this);

        // Recupera una lista de tareas
        tsk_full = Task.getTasks(ViewFlipTest.this,1);
        tsk_smart = Task.getTasks(ViewFlipTest.this,2);

        // Y ya en una función local, compongo los strings como se necesite
        mStrings_full = getStringsFromTasks(tsk_full);
        mStrings_smart = getStringsFromTasks(tsk_smart);

        ListView lvSmart = (ListView) findViewById(R.id.list_smart);
        lvSmart.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings_smart));
        lvSmart.setTextFilterEnabled(true);
        lvSmart.setOnTouchListener((OnTouchListener) this);
        
        ListView lvFull = (ListView) findViewById(R.id.list_full);
        lvFull.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings_full));
        lvFull.setTextFilterEnabled(true);
        lvFull.setOnTouchListener((OnTouchListener) this);
	}
	
	
	// Event handler for buttons OnClick method, defined in task_lists.xml
	public void addNewTaskListener(View v){
		callTaskEditorActivity();
	}
	
	// Calls to each optional settings dialog.
	private void callTaskEditorActivity() {
		Intent taskEditorIntent = new Intent(this,TaskEditorActivity.class);
		startActivity(taskEditorIntent);	
	}
	
	// Some tests with viewFlipper in order to switch between Smart List and Full List
	// TODO: Remove this code and upgrade to something similar to Launcher. Drag and slide views by using gestures.
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// Get the action that was done on this touch event
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                // store the X value when the user's finger was pressed down
                downXvalue = event.getX();
                break;
            }

            case MotionEvent.ACTION_UP:
            {
                // Get the X value when the user released his/her finger
                float currentX = event.getX();            

                // going backwards: pushing stuff to the right
                if (downXvalue < currentX)
                {
                    // Get a reference to the ViewFlipper
                    // ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
                     // Set the animation
                	listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                      // Flip!
                      ((ViewAnimator) listsViewFlipper).showPrevious();
                }

                // going forwards: pushing stuff to the left
                if (downXvalue > currentX)
                {
                    // Get a reference to the ViewFlipper
                    //ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
                     // Set the animation
                	listsViewFlipper.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                      // Flip!
                     ((ViewAnimator) listsViewFlipper).showNext();
                }    
                
                if (flipView == 1) 
                	flipView = 2;
                else 
                	flipView = 1;
                
                TextView txtView = (TextView)findViewById(R.id.textView_View_Title);
                if (flipView == 1) txtView.setText("Smart List");
                if (flipView == 2) txtView.setText("Full List");
                break;
            }
        }
        // if you return false, these actions will not be recorded
        return true;
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
