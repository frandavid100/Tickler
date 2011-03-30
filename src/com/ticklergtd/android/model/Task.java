package com.ticklergtd.android.model;

import java.util.ArrayList;
import java.util.Date;

public class Task {
	public static final int REPEAT_DAYS = 1;
	public static final int REPEAT_WEEKS = 2;
	public static final int REPEAT_MONTHS = 3;
	public static final int REPEAT_YEARS = 4;
	public static final long NEW_TASK = -1;
	private long mId = NEW_TASK;
	private String mName;
	private int mPriority;
	private Date mCreationDate;
	private boolean mSomeday;
	private Date mStartDate;
	private Date mDeadline;
	private Date mCompleted;
	private Date mAbandoned;
	private int mRepeat;
	private int mRepeatUnits;
	private boolean mRepeatFrom;
	private boolean mSimultaneous;
	private Task mParent;
	private ArrayList<Task> mChildren;
	/**
	 * The order when this task is a child of another task
	 */
	private int mOrder;
	private ArrayList<TaskContext> mContexts;
	
	public Task() {}

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
		this.mCreationDate = mCreationDate;
	}

	/**
	 * @return the mSomeday
	 */
	public boolean isSomeday() {
		return mSomeday;
	}

	/**
	 * @param mSomeday the mSomeday to set
	 */
	public void setSomeday(boolean mSomeday) {
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
	public boolean isRepeatFrom() {
		return mRepeatFrom;
	}

	/**
	 * @param mRepeatFrom the mRepeatFrom to set
	 */
	public void setRepeatFrom(boolean mRepeatFrom) {
		this.mRepeatFrom = mRepeatFrom;
	}

	/**
	 * @return the mSimultaneous
	 */
	public boolean isSimultaneous() {
		return mSimultaneous;
	}

	/**
	 * @param mSimultaneous the mSimultaneous to set
	 */
	public void setSimultaneous(boolean mSimultaneous) {
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

	public void setContexts(ArrayList<TaskContext> mContexts) {
		this.mContexts = mContexts;
	}

	public ArrayList<TaskContext> getContexts() {
		if(mContexts == null) {
			mContexts = new ArrayList<TaskContext>();
		}
		return mContexts;
	}
	
	public TaskContext getContext(int position) {
		if(mContexts.size() > position) {
			return null;
		} else {
			return mContexts.get(position);
		}
	}
}
