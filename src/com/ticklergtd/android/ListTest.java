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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ticklergtd.android.model.ContextTask;
import com.ticklergtd.android.model.Task;


/**
 * A list view example where the 
 * data for the list comes from an array of strings.
 */
public class ListTest extends ListActivity  implements OnClickListener {

	private ArrayList<String> mStrings;
	private ArrayList<Task> tsk = new ArrayList<Task>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recupera una lista de tareas
        tsk = Task.getTasks(ListTest.this);
        
        // Y ya en una función local, compongo los strings como se necesite
        mStrings = getStringsFromTasks(tsk);
        
        // Y esa lista de string se pasa al adaptador de la lista para que lo muestre
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        getListView().setTextFilterEnabled(true);
    }

	public void onListItemClick(ListView parent, View v, int position,long id) {
		//
		Task tskNew = new Task(ListTest.this);
		Task tskAux = tsk.get(position);
		tskNew.setName(tskAux.getName() + ".");
		tskNew.setPriority(tskAux.getPriority());
		tskNew.setNote(tskAux.getNote());
		tskNew.setAbandoned(tskAux.getAbandoned());
		tskNew.setCompleted(tskAux.getCompleted());
		tskNew.setContexts(tskAux.getContexts());
		tskNew.setCreationDate(tskAux.getCreationDate());
		tskNew.setDeadline(tskAux.getDeadline());
		tskNew.setOrder(tskAux.getOrder());
		tskNew.setRepeat(tskAux.getRepeat());
		tskNew.setRepeatFrom(tskAux.getRepeatFrom());
		tskNew.setSimultaneous(tskAux.isSimultaneous());
		tskNew.setSomeday(tskAux.isSomeday());
		tskNew.setStartDate(tskAux.getStartDate());
		tskNew.setRepeatUnits(tskAux.getRepeatUnits());
		tskNew.save();
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
