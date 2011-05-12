package com.ticklergtd.android.model;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;

import com.ticklergtd.android.Utilities;

public class ContextTask {
	public static final long NEW_CONTEXT = -1;
	
	private long mId 				= NEW_CONTEXT;
	private String mName;
	private int mIcon 				= 1;
	private int mNotifications		= 0;
	private ArrayList<ContextTask> mContexts;

	private static Context 			mCtx;
	private static TicklerDBAdapter tck;
	

	/**
	 * @param mId
	 * @param mName
	 * @param mIcon
	 * @param mNotifications
	 * @param mContexts
	 */
	public ContextTask(long mId, String mName, int mIcon, int mNotifications) {
		this.mId = mId;
		this.mName = mName;
		this.mIcon = mIcon;
		this.mNotifications = mNotifications;
	}
	
	public ContextTask() {
		this.mId = 0;
		this.mName = "";
		this.mIcon = 0;
		this.mNotifications = 0;
		this.mContexts = null;
	}

	public ContextTask(Context ctx) {
		this.mId = 0;
		this.mName = "";
		this.mIcon = 0;
		this.mNotifications = 0;
		this.mContexts = null;
		mCtx = ctx;
	}

	/**
	 * @return the mId
	 */
	public long getId() {
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setId(long mId) {
		this.mId = mId;
	}

	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}

	/**
	 * @return the mIcon
	 */
	public boolean isIcon() {
		return (mIcon==1?true:false);
	}

	/**
	 * @param mIcon the mIcon to set
	 */
	public void setIcon(boolean mIcon) {
		this.mIcon = (mIcon==true?1:0);
	}

	/**
	 * @return the mNotifications
	 */
	public boolean isNotifications() {
		return (mNotifications==1?true:false);
	}

	/**
	 * @param mNotifications the mNotifications to set
	 */
	public void setNotifications(boolean mNotifications) {
		this.mNotifications = (mNotifications==true?1:0);
	}

	/**
	 * @return the mContexts
	 */
	public ArrayList<ContextTask> getContexts() {
		return mContexts;
	}

	/**
	 * @param mContexts the mContexts to set
	 */
	public void setContexts(ArrayList<ContextTask> mContexts) {
		this.mContexts = mContexts;
	}
	
	public void Context(int context_id) {
		
	}
	
	public static ArrayList<ContextTask> getContextsTask(long task_id, Context ctx) {
		mCtx = ctx;
		ArrayList<ContextTask> aux = new ArrayList<ContextTask>();
		
		ContextTask_helper();
		Cursor c = tck.selectContextTasks(task_id);
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			ContextTask t = new ContextTask(mCtx);
			t.setId(c.getLong(1));
			t.setName(c.getString(2));
			aux.add(t);
			
			c.moveToNext();
		}
		c.close();
		return aux;		
	}

	public static ArrayList<ContextTask> getAllContextsTask(Context ctx) {
		mCtx = ctx;
		ArrayList<ContextTask> aux = new ArrayList<ContextTask>();
		
		ContextTask_helper();
		Cursor c = tck.getContextTasksList();
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			ContextTask t = new ContextTask(mCtx);
			t.setId(c.getLong(0));
			t.setName(c.getString(1));
			//t.setIcon(c.getInt(2) == 1);
			//t.setNotifications(c.getInt(3) == 1);
			aux.add(t);
			
			c.moveToNext();
		}
		c.close();
		return aux;		
	}

	public boolean applyContext() {
		return true;
	}
	
	private static void ContextTask_helper() {
		tck = new TicklerDBAdapter(mCtx);
		tck.open();
	}
		
}
