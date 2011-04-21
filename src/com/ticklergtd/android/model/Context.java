package com.ticklergtd.android.model;

import java.util.ArrayList;
import java.util.Date;

public class Context {
	public static final long NEW_CONTEXT = -1;
	
	private long mId 				= NEW_CONTEXT;
	private String mName;
	private int mIcon 			= 1;
	private int mNotifications	= 0;
	private ArrayList<Context> mContexts;
	
	/**
	 * @param mId
	 * @param mName
	 * @param mIcon
	 * @param mNotifications
	 * @param mContexts
	 */
	public Context(long mId, String mName, int mIcon, int mNotifications) {
		this.mId = mId;
		this.mName = mName;
		this.mIcon = mIcon;
		this.mNotifications = mNotifications;
	}
	
	public Context() {
		this.mId = 0;
		this.mName = "";
		this.mIcon = 0;
		this.mNotifications = 0;
		this.mContexts = null;
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
	public ArrayList<Context> getContexts() {
		return mContexts;
	}

	/**
	 * @param mContexts the mContexts to set
	 */
	public void setContexts(ArrayList<Context> mContexts) {
		this.mContexts = mContexts;
	}
	
	public void Context(int context_id) {
		
	}
}
