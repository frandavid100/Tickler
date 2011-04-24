/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;


/**
 * A list view example where the 
 * data for the list comes from an array of strings.
 */
public class ListTest extends ListActivity {

	private ArrayList<String> mStrings;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Task> tsk = Task.getTasks(ListTest.this);
        mStrings = getStringsFromTasks(tsk);
        
        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        getListView().setTextFilterEnabled(true);
    }

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
