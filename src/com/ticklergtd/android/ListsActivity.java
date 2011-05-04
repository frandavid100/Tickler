package com.ticklergtd.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

public class ListsActivity extends Activity implements OnTouchListener {
	
	float downXvalue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_lists);
		// Set a touch listener on viewflipper
        ViewFlipper listsViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
        listsViewFlipper.setOnTouchListener((OnTouchListener) this);        
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
                      v.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                      // Flip!
                      ((ViewAnimator) v).showPrevious();
                }

                // going forwards: pushing stuff to the left
                if (downXvalue > currentX)
                {
                    // Get a reference to the ViewFlipper
                    //ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewFlipper_Lists_Container);
                     // Set the animation
                     v.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                      // Flip!
                     ((ViewAnimator) v).showNext();
                }         	
                break;
            }
        }
        // if you return false, these actions will not be recorded
        return true;
    }
	
}
