package com.ticklergtd.android.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;

import com.ticklergtd.android.Utilities;

public class Task {
	public static final int REPEAT_DAYS		= 1;
	public static final int REPEAT_WEEKS 	= 2;
	public static final int REPEAT_MONTHS 	= 3;
	public static final int REPEAT_YEARS 	= 4;
	
	public static final long NEW_TASK = -1;
	
	private long 			mId = NEW_TASK;
	private String 			mName;
	private String 			mNote;
	private int 			mPriority;
	private Date 			mCreationDate;
	private int 			mSomeday;
	private Date 			mStartDate;
	private Date 			mDeadline;
	private Date 			mCompleted;
	private Date 			mAbandoned;
	private int 			mRepeat;
	private int 			mRepeatUnits;
	private int 			mRepeatFrom;
	private int 			mSimultaneous;
	private Task 			mParent;
	private ArrayList<Task> mChildren;
	
	private static Context 			mCtx;
	private static TicklerDBAdapter tck;
	
	static final int SMART 		= 1;
	static final int FULL 		= 2;
	static final int SOMEDAY	= 3;

	/**
	 * The order when this task is a child of another task
	 */
	private int 					mOrder;
	private ArrayList<ContextTask> 	mContexts;
	
	public Task(Context ctx) {
		mCtx = ctx;
	}

	public Task(long task_id, Context ctx) {
		mId 	= task_id;
		mCtx	= ctx;
		
		Task_helper();
		Cursor c = tck.selectTask(task_id);

		c.moveToFirst();
		if (!c.isAfterLast()) {
			this.setId(c.getLong(0));
			this.setName(c.getString(1));
			this.setPriority(c.getInt(2));
			this.setNote(c.getString(3));
			this.setSomeday(c.getInt(5));
			this.setCreationDate(Utilities.string2Date(c.getString(4)));
			this.setStartDate(Utilities.string2Date(c.getString(6)));
			this.setDeadline(Utilities.string2Date(c.getString(7)));
			this.setCompleted(Utilities.string2Date(c.getString(8)));
			this.setAbandoned(Utilities.string2Date(c.getString(9)));			
			this.setContexts(ContextTask.getContextsTask(this.getId(),mCtx));
		}
		else {
			this.setId(-1);
			this.setName("");
			this.setPriority(2);
			this.setNote("");
			this.setSomeday(0);
			this.setCreationDate(new Date());
			this.setStartDate(new Date());
			this.setDeadline(null);
			this.setCompleted(null);
			this.setAbandoned(null);			
			this.setContexts(ContextTask.getContextsTask(this.getId(),mCtx));
		}
		c.close();
		Task_helper_close();
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
		if (mName == null)
			return "";
		else
			return mName;
	}

	/**
	 * @param mNote the mNote to set
	 */
	public void setNote(String mNote) {
		if (mNote != null)
			this.mNote = mNote;
	}

	/**
	 * @return the mNote
	 */
	public String getNote() {
		if (mNote != null)
			return mNote;
		else
			return "";
	}

	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
		if (mName != null)
			this.mName = mName;
	}

	/**
	 * @return the mPriority
	 */
	public int getPriority() {
		return mPriority;
	}

	/**
	 * @param mPriority the mPriority to set
	 */
	public void setPriority(int mPriority) {
		this.mPriority = mPriority;
	}

	/**
	 * @return the mCreationDate
	 */
	public Date getCreationDate() {
		return mCreationDate;
	}

	/**
	 * @param mCreationDate the mCreationDate to set
	 */
	public void setCreationDate(Date mCreationDate) {
		if (mCreationDate != null)
			this.mCreationDate = mCreationDate;
	}

	/**
	 * @return the mSomeday
	 */
	public int getSomeday() {
		return mSomeday;
	}

	/**
	 * @param mSomeday the mSomeday to set
	 */
	public void setSomeday(int mSomeday) {
		this.mSomeday = mSomeday;
	}

	/**
	 * @return the mStartDate
	 */
	public Date getStartDate() {
		return mStartDate;
	}

	/**
	 * @param mStartDate the mStartDate to set
	 */
	public void setStartDate(Date mStartDate) {
		//if (mStartDate != null)
			this.mStartDate = mStartDate;
	}

	/**
	 * @return the mDeadline
	 */
	public Date getDeadline() {
		return mDeadline;
	}

	/**
	 * @param mDeadline the mDeadline to set
	 */
	public void setDeadline(Date mDeadline) {
		//if (mDeadline != null)
			this.mDeadline = mDeadline;
	}

	/**
	 * @return the mCompleted
	 */
	public Date getCompleted() {
		return mCompleted;
	}

	/**
	 * @param mCompleted the mCompleted to set
	 */
	public void setCompleted(Date mCompleted) {
		if (mCompleted != null)
			this.mCompleted = mCompleted;
	}

	/**
	 * @return the mAbandoned
	 */
	public Date getAbandoned() {
		return mAbandoned;
	}

	/**
	 * @param mAbandoned the mAbandoned to set
	 */
	public void setAbandoned(Date mAbandoned) {
		if (mAbandoned != null)
			this.mAbandoned = mAbandoned;
	}

	/**
	 * @return the mRepeat
	 */
	public int getRepeat() {
		return mRepeat;
	}

	/**
	 * @param mRepeat the mRepeat to set
	 */
	public void setRepeat(int mRepeat) {
		this.mRepeat = mRepeat;
	}

	/**
	 * @return the mRepeatUnits
	 */
	public int getRepeatUnits() {
		return mRepeatUnits;
	}

	/**
	 * @param mRepeatUnits the mRepeatUnits to set
	 */
	public void setRepeatUnits(int mRepeatUnits) {
		this.mRepeatUnits = mRepeatUnits;
	}

	/**
	 * @return the mRepeatFrom
	 */
	public int getRepeatFrom() {
		return mRepeatFrom;
	}

	/**
	 * @param mRepeatFrom the mRepeatFrom to set
	 */
	public void setRepeatFrom(int mRepeatFrom) {
		this.mRepeatFrom = mRepeatFrom;
	}

	/**
	 * @return the mSimultaneous
	 */
	public int isSimultaneous() {
		return mSimultaneous;
	}

	/**
	 * @param mSimultaneous the mSimultaneous to set
	 */
	public void setSimultaneous(int mSimultaneous) {
		this.mSimultaneous = mSimultaneous;
	}

	/**
	 * @return the mParent
	 */
	public Task getParent() {
		return mParent;
	}

	/**
	 * @param mParent the mParent to set
	 */
	public void setParent(Task mParent) {
		this.mParent = mParent;
	}

	/**
	 * @return the mChildren
	 */
	public ArrayList<Task> getChildren() {
		if(mChildren == null) {
			mChildren = new ArrayList<Task>();
		}
		return mChildren;
	}
	
	/**
	 * @param position
	 * @return child at the specified position
	 */
	public Task getChild(int position) {
		if(mChildren.size() > position) {
			return mChildren.get(position);
		} else {
			return null;
		}
	}

	/**
	 * @param mChildren the mChildren to set
	 */
	public void setChildren(ArrayList<Task> mChildren) {
		this.mChildren = mChildren;
	}

	public void setOrder(int mOrder) {
		this.mOrder = mOrder;
	}

	public int getOrder() {
		return mOrder;
	}

	public void setContexts(ArrayList<ContextTask> mContexts) {
		this.mContexts = mContexts;
	}

	public ArrayList<ContextTask> getContexts() {
		if(mContexts == null) {
			mContexts = new ArrayList<ContextTask>();
		}
		return mContexts;
	}
	
	public ContextTask getContext(int position) {
		if(mContexts.size() > position) {
			return null;
		} else {
			return mContexts.get(position);
		}
	}
	
	public static ArrayList<Task> getTasks(Context ctx, int flipView) {
		mCtx = ctx;
		ArrayList<Task> aux = new ArrayList<Task>();
		
		Task_helper();
		Cursor c = null;
		if (flipView == SMART)
			c = tck.selectTasks_Smart();
		if (flipView == FULL)
			c = tck.selectTasks_Full();
		if (flipView == SOMEDAY)
			c = tck.selectTasks_Someday();
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Task t = new Task(mCtx);
			t.setId(c.getLong(0));
			t.setName(c.getString(1));
			t.setPriority(c.getInt(2));
			t.setNote(c.getString(3));
			t.setSomeday(c.getInt(5));
			t.setCreationDate(Utilities.string2Date(c.getString(4)));
			t.setStartDate(Utilities.string2Date(c.getString(6)));
			t.setDeadline(Utilities.string2Date(c.getString(7)));
			t.setCompleted(Utilities.string2Date(c.getString(8)));
			t.setAbandoned(Utilities.string2Date(c.getString(9)));			
			t.setContexts(ContextTask.getContextsTask(t.getId(),mCtx));
			aux.add(t);
			
			c.moveToNext();
		}
		c.close();
		Task_helper_close();
		
		return aux;
	}
	
	public long save() {
		long lRes = 0;
		
		Task_helper();
		lRes = tck.addTask(this);
		Task_helper_close();
		
		return lRes;
	}
	
	private static void Task_helper() {
		tck = new TicklerDBAdapter(mCtx);
		tck.open();
	}
	
	private static void Task_helper_close() {
		try {
			tck.close();
		}
		catch (Exception e) {
			
		}
	}
}
