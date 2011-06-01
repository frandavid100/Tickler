package com.ticklergtd.android.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.ticklergtd.android.model.table.ContextRegions;
import com.ticklergtd.android.model.table.ContextTimes;

public class ContextTask {
	public static final long NEW_CONTEXT = -1;
	
	private long mId 				= NEW_CONTEXT;
	private String mName;
	private int mIcon 				= 1;
	private int mNotifications		= 0;
	private long mUsedNTasks		= 0;
	private ArrayList<ContextTimes> 	mContextsTimes;
	private ArrayList<ContextRegions> 	mContextsRegions;

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
		this.mId 			= mId;
		this.mName 			= mName;
		this.mIcon 			= mIcon;
		this.mNotifications = mNotifications;
		this.mUsedNTasks 	= 0;
	}
	
	/**
	 * @param mId
	 * @param mName
	 * @param mIcon
	 * @param mNotifications
	 * @param mContexts
	 * @param mUsedNTasks
	 */
	public ContextTask(long mId, String mName, int mIcon, int mNotifications, long mUsedNTasks) {
		this.mId 			= mId;
		this.mName 			= mName;
		this.mIcon 			= mIcon;
		this.mNotifications = mNotifications;
		this.mUsedNTasks 	= mUsedNTasks;
	}

	public ContextTask() {
		this.mId 				= 0;
		this.mName 				= "";
		this.mIcon 				= 0;
		this.mNotifications 	= 0;
		this.mContextsTimes 	= null;
		this.mContextsRegions	= null;
		this.mUsedNTasks		= 0;
	}

	public ContextTask(Context ctx) {
		this.mId 				= 0;
		this.mName 				= "";
		this.mIcon 				= 0;
		this.mNotifications 	= 0;
		this.mUsedNTasks		= 0;
		this.mContextsTimes		= null;
		this.mContextsRegions	= null;
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
	 * @return the mUsedNTasks
	 */
	public long getUsedNTasks() {
		return mUsedNTasks;
	}

	/**
	 * @param mUsedNTasks the mUsedNTasks to set
	 */
	public void setUsedNTasks(long mUsedNTasks) {
		this.mUsedNTasks = mUsedNTasks;
	}


	/**
	 * @return the mContexts
	 */
	public ArrayList<ContextTimes> getContextsTimes() {
		return mContextsTimes;
	}

	/**
	 * @return the mContexts
	 */
	public ArrayList<ContextRegions> getContextsRegions() {
		return mContextsRegions;
	}

	/**
	 * @param mContexts the mContextsTimes to set
	 */
	public void setContextsTimes(ArrayList<ContextTimes> mContexts) {
		this.mContextsTimes = mContexts;
	}
	
	/**
	 * @param mContexts the mContextsRegions to set
	 */
	public void setContextsRegions(ArrayList<ContextRegions> mContexts) {
		this.mContextsRegions = mContexts;
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
		ContextTask_helper_close();
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
		ContextTask_helper_close();
		return aux;		
	}

	public static ArrayList<ContextTask> getContextsTaskCount(Context ctx) {
		mCtx = ctx;
		ArrayList<ContextTask> aux = new ArrayList<ContextTask>();
		
		ContextTask_helper();
		Cursor c = tck.getContextsTasksCount();
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			ContextTask t = new ContextTask(mCtx);
			t.setId(c.getLong(0));
			t.setName(c.getString(1));
			t.setIcon(c.getInt(2) == 1);
			t.setUsedNTasks(c.getInt(3));
			aux.add(t);
			
			c.moveToNext();
		}
		c.close();
		ContextTask_helper_close();
		return aux;		
	}

	public boolean applyContext() {
		boolean bTimes 		= false;
		boolean bRegions 	= false;
		
		if (mContextsTimes != null) {
			for (int i=0; i<mContextsTimes.size(); i++) {
				if (applyContextTimes(mContextsTimes.get(i))) {
					bTimes = true;
				}
			}
		}
		
		if (mContextsRegions != null) {
			for (int i=0; i<mContextsRegions.size(); i++) {
				if (applyContextRegions(mContextsRegions.get(i))) {
					bTimes = true;
				}
			}
		}
		
		// return bTimes or bRegions
		return true; // TODO: CAMBIAR CUANDO SE TERMINE LA LOGICA DE NEGOCIO
	}
	
	public boolean applyContextTimes(ContextTimes ct) {
		return true; // TODO: CAMBIAR CUANDO SE TERMINE LA LOGICA DE NEGOCIO
	}
	
	public boolean applyContextRegions(ContextRegions ct) {
		return true; // TODO: CAMBIAR CUANDO SE TERMINE LA LOGICA DE NEGOCIO
	}
	
	private static void ContextTask_helper() {
		tck = new TicklerDBAdapter(mCtx);
		tck.open();
	}
		
	private static void ContextTask_helper_close() {
		try {
			tck.close();
		}
		catch (Exception e) {
			
		}
	}
		
}
