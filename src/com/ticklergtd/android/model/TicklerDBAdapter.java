package com.ticklergtd.android.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TicklerDBAdapter {
/*
	Table: Contexts

	_id	integer primary key	first field in every tab
	name	text	name of the context
	icon	integer	icon of the context
	activetime	integer	foreign key back to Contextimes union table (may create new values)
	activeregion	integer	foreign key back to Contextregions union table (may create new values)
*/
	public static final String KEY_CONTEXTS_ID = "_id";
	public static final String KEY_CONTEXTS_NAME = "name";
	public static final String KEY_CONTEXTS_ICON = "icon";
//	public static final String KEY_CONTEXTS_ACTIVE_TIME = "activetime"; //Unneded?
//	public static final String KEY_CONTEXTS_ACTIVE_REGION = "activeregion"; //Unneeded?
	public static final String DATABASE_TABLE_CONTEXTS = "contexts"; 
	private static final String DATABASE_CREATE_CONTEXTS = String.format(	"create table %s (" +
																			"%s integer primary key autoincrement, " +
																			"%s text, " +
																			"%s integer)",
																			new Object[] {
																				DATABASE_TABLE_CONTEXTS,
																				KEY_CONTEXTS_ID,
																				KEY_CONTEXTS_NAME,
																				KEY_CONTEXTS_ICON
																			});

	
/*
	Table: Tasks

	_id	integer primary key	first field in every tab
	name	text	name of the task
	priority	integer	priority (1, 2, 3)
	dt_creation	date	creation date
	someday	boolean	is it a “someday” task?
	dt_start	date	start date (only if someday = F), can be NULL
	dt_deadline	date	deadline date, can be NULL
	dt_completed	date	when it was completed, can be NULL
	dt_abandoned	date	when it was abandoned, can be NULL
	repeat	integer	amount of units before repeating
	rp_units	integer	1 = days, 2 = weeks, 3 = months, 4 = years
	rp_from	boolean	if T, the new task’s dt_start is taken from the old one’s dt_start; if it doesn’t have one, it’s taken from its dt_creation.
	if F, the new task’s dt_start is taken from the old one’s dt_completed.
	simultaneous	boolean	if T, children can be done simultaneously
	contexts	integer	foreign key back to Taskcontexts union table (may create new values, a task may have several contexts)
	family	integer	foreign key back to Family union table (may create new values, a task may have several children but only one parent)
*/
	public static final String KEY_TASKS_ID = "_id";
	public static final String KEY_TASKS_NAME = "name";
	public static final String KEY_TASKS_PRIORITY = "priority";
	public static final String KEY_TASKS_DATE_CREATION = "dt_creation";
	public static final String KEY_TASKS_SOMEDAY = "someday";
	public static final String KEY_TASKS_DATE_START = "dt_start";
	public static final String KEY_TASKS_DATE_DEADLINE = "dt_deadline";
	public static final String KEY_TASKS_DATE_COMPLETED = "dt_completed";
	public static final String KEY_TASKS_DATE_ABANDONED = "dt_abandoned";
	public static final String KEY_TASKS_REPEAT = "repeat";
	public static final String KEY_TASKS_REPEAT_UNITS = "rp_units";
	public static final String KEY_TASKS_REPEAT_FROM = "rp_from";
	public static final String KEY_TASKS_SIMULTANEOUS = "simultaneous";
	//public static final String KEY_TASKS_CONTEXTS = "contexts"; //Maybe unneeded
	//public static final String KEY_TASKS_FAMILY = "family"; //Maybe unneeded
	public static final String DATABASE_TABLE_TASKS = "tasks";
	public static final String DATABASE_CREATE_TASKS = String.format(	"create table %s (" +
																		"%s integer primary key autoincrement, " +
																		"%s text, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer, " +
																		"%s integer)",
																		new Object[] {
																			DATABASE_TABLE_TASKS, KEY_TASKS_ID, KEY_TASKS_NAME,
																			KEY_TASKS_PRIORITY, KEY_TASKS_DATE_CREATION,
																			KEY_TASKS_SOMEDAY, KEY_TASKS_DATE_START,
																			KEY_TASKS_DATE_START, KEY_TASKS_DATE_DEADLINE,
																			KEY_TASKS_DATE_COMPLETED, KEY_TASKS_DATE_ABANDONED,
																			KEY_TASKS_REPEAT, KEY_TASKS_REPEAT_UNITS,
																			KEY_TASKS_REPEAT_FROM, KEY_TASKS_SIMULTANEOUS
																		});
	
/*
	Table: Activetimes
	
	_id	integer primary key	first field in every tab
	name	text	schedule name
	start	date	start time
	end	date	end date
	day	integer	day of the week
 */
	public static final String KEY_ACTIVETIMES_ID = "_id";
	public static final String KEY_ACTIVETIMES_NAME = "name";
	public static final String KEY_ACTIVETIMES_START = "start";
	public static final String KEY_ACTIVETIMES_END = "end";
	public static final String KEY_ACTIVETIMES_DAY = "day";
	public static final String DATABASE_TABLE_ACTIVETIMES = "activetimes";
	public static final String DATABASE_CREATE_ACTIVETIMES = String.format(	"create table %s (" +
																			"%s integer primary key autoincrement, " +
																			"%s text, " +
																			"%s integer, " +
																			"%s integer, " +
																			"%s integer)",
																			new Object[] {
																				DATABASE_TABLE_ACTIVETIMES, KEY_ACTIVETIMES_ID,
																				KEY_ACTIVETIMES_NAME, KEY_ACTIVETIMES_START,
																				KEY_ACTIVETIMES_END, KEY_ACTIVETIMES_DAY
																			});
	
/*
	Table: Activeregions

	_id	integer primary key	first field in every tab
	name	text	region name
	location	float	geographical position
	radius	float	radius around location (in km)
 */
	public static final String KEY_ACTIVEREGIONS_ID = "_id";
	public static final String KEY_ACTIVEREGIONS_NAME = "name";
	public static final String KEY_ACTIVEREGIONS_LOCATION = "location";
	public static final String KEY_ACTIVEREGIONS_RADIUS = "radius";
	public static final String DATABASE_TABLE_ACTIVEREGIONS = "activeregions";
	public static final String DATABASE_CREATE_ACTIVEREGIONS = String.format(	"create table %s (" +
																				"%s integer primary key autoincrement, " +
																				"%s text, " +
																				"%s integer, " +
																				"%s integer)",
																				new Object[] {
																					DATABASE_TABLE_ACTIVEREGIONS, KEY_ACTIVEREGIONS_ID,
																					KEY_ACTIVEREGIONS_NAME, KEY_ACTIVEREGIONS_LOCATION,
																					KEY_ACTIVEREGIONS_RADIUS
																				});
	
/*
	Union table: Taskcontexts

	taskid	integer	foreign key back to Tasks table (may create new values)
	contextid	integer	foreign key back to Contexts table (may create new values)
 */
	public static final String KEY_TASKCONTEXTS_TASKID = "taskid";
	public static final String KEY_TASKCONTEXTS_CONTEXTID = "contextid";
	public static final String DATABASE_TABLE_TASKCONTEXTS = "taskcontexts";
	public static final String DATABASE_CREATE_TASKCONTEXTS = String.format(	"create table %s (" +
																				"%s integer," +
																				"%s integer)",
																				new Object[] {
																					DATABASE_TABLE_TASKCONTEXTS,
																					KEY_TASKCONTEXTS_TASKID,
																					KEY_TASKCONTEXTS_CONTEXTID
																				});
	
/*
	Union table: Families

	parentid	integer	foreign key back to Tasks table
	childid	integer	foreign key back to Tasks table (may create new values)
	order	integer	order inside the group
 */
	public static final String KEY_FAMILIES_PARENTID = "parentid";
	public static final String KEY_FAMILIES_CHILDID = "childid";
	public static final String KEY_FAMILIES_ORDER = "order";
	public static final String DATABASE_TABLE_FAMILIES = "families";
	public static final String DATABASE_CREATE_FAMILIES = String.format(	"create table %s (" +
																			"%s integer," +
																			"%s integer," +
																			"%s integer)",
																			new Object[] {
																				DATABASE_TABLE_FAMILIES,
																				KEY_FAMILIES_PARENTID,
																				KEY_FAMILIES_CHILDID,
																				KEY_FAMILIES_ORDER
																			});
	
/*
	Union table: Contexttimes

	contextid	integer	foreign key back to Context table
	timeid	integer	foreign key back to Activetimes table (may create new values, a context may have several activetimes)
	active	boolean	whether the Activetime must be taken into account
 */
	public static final String KEY_CONTEXTTIMES_CONTEXTID = "contextid";
	public static final String KEY_CONTEXTTIMES_TIMEID = "timeid";
	public static final String KEY_CONTEXTTIMES_ACTIVE = "active";
	public static final String DATABASE_TABLE_CONTEXTTIMES = "contexttimes";
	public static final String DATABASE_CREATE_CONTEXTTIMES = String.format(	"create table %s (" +
																				"%s integer," +
																				"%s integer," +
																				"%s integer)",
																				new Object[] {
																					DATABASE_TABLE_CONTEXTTIMES,
																					KEY_CONTEXTTIMES_CONTEXTID,
																					KEY_CONTEXTTIMES_TIMEID,
																					KEY_CONTEXTTIMES_ACTIVE
																				});
	
/*
	Union table: Contextregions

	contextid	integer	foreign key back to Context table
	regionid	integer	foreign key back to Activeregions table (may create new values, a context may have several activeregions)
	active	boolean	whether the Activeregion must be taken into account
 */
	public static final String KEY_CONTEXTREGIONS_CONTEXTID = "contextid";
	public static final String KEY_CONTEXTREGIONS_REGIONID = "regionid";
	public static final String KEY_CONTEXTREGIONS_ACTIVE = "active";
	public static final String DATABASE_TABLE_CONTEXTREGIONS = "contextregions";
	public static final String DATABASE_CREATE_CONTEXTREGIONS = String.format(	"create table %s (" +
																				"%s integer," +
																				"%s integer," +
																				"%s integer)",
																				new Object[] {
																					DATABASE_TABLE_CONTEXTREGIONS,
																					KEY_CONTEXTREGIONS_CONTEXTID,
																					KEY_CONTEXTREGIONS_REGIONID,
																					KEY_CONTEXTREGIONS_ACTIVE
																				});

	

	private static final String TAG = "TicklerDBAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private boolean isOpen = false;

	private static final String DATABASE_NAME = "tickler";
	private static final int DATABASE_VERSION = 1;

	private Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_ACTIVEREGIONS);
			db.execSQL(DATABASE_CREATE_ACTIVETIMES);
			db.execSQL(DATABASE_CREATE_CONTEXTREGIONS);
			db.execSQL(DATABASE_CREATE_CONTEXTS);
			db.execSQL(DATABASE_CREATE_CONTEXTTIMES);
			db.execSQL(DATABASE_CREATE_FAMILIES);
			db.execSQL(DATABASE_CREATE_TASKCONTEXTS);
			db.execSQL(DATABASE_CREATE_TASKS);
			//TODO: Create needed indexes
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i(TAG, "Upgrading from " + oldVersion + " to " + newVersion);
			//TODO: Add updgrade script
		}
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx the Context within which to work
	 */
	public TicklerDBAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public TicklerDBAdapter open() throws SQLException {
		if(!isOpen) {
			mDbHelper = new DatabaseHelper(mCtx);
			mDb = mDbHelper.getWritableDatabase();
		}
		isOpen = true;
		return this;
	}

	public void close() {
		if(isOpen) mDbHelper.close();
		isOpen = false;
	}

	public Cursor selectTasks(){
		return mDb.query(
				DATABASE_TABLE_TASKS,
				new String[] { KEY_TASKS_ID, KEY_TASKS_NAME,
						KEY_TASKS_PRIORITY, KEY_TASKS_DATE_CREATION,
						KEY_TASKS_SOMEDAY, KEY_TASKS_DATE_START,
						KEY_TASKS_DATE_START, KEY_TASKS_DATE_DEADLINE,
						KEY_TASKS_DATE_COMPLETED, KEY_TASKS_DATE_ABANDONED,
						KEY_TASKS_REPEAT, KEY_TASKS_REPEAT_UNITS,
						KEY_TASKS_REPEAT_FROM, KEY_TASKS_SIMULTANEOUS}, 
				null,
				null,
				null,
				null,
				KEY_TASKS_DATE_START + " ASC"
		);
	}
	
	public long addTask(Task task) {
		long rowId = addTask(task.getName(), task.getPriority(), task.getCreationDate().getTime(), task.isSomeday(), task.getStartDate().getTime(), task.getDeadline().getTime(),
				task.getCompleted().getTime(), task.getAbandoned().getTime(), task.getRepeat(), task.getRepeatUnits(), task.isRepeatFrom(), task.isSimultaneous());
		task.setId(rowId); //TODO: Check if addTask's returned rowId is the correct table's row id
		
		//Check if this task is children of some other task
		Task parent = task.getParent();
		if(parent != null) {
			//Add it to the family
			addTaskChild(parent, task);
		}
		
		//When creating a new task, it will probably have no children at all... but just in case, we check it
		ArrayList<Task> children = task.getChildren();
		for(Task child : children) {
			addTaskChild(task, child);
		}
		
		return rowId;
	}

	private long addTask(String name, int priority, long date_creation, boolean someday, long date_start, long date_deadline, long date_completed,
			long date_abandoned, int repeat, int repeat_units, boolean repeat_from, boolean simultaneous) {
		int int_someday = 0;
		if(someday) int_someday = 1;
		int int_repeat_from = 0;
		if(repeat_from) int_repeat_from = 1;
		int int_simultaneous = 0;
		if(simultaneous) int_simultaneous = 1;
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TASKS_NAME, name);
		initialValues.put(KEY_TASKS_PRIORITY, priority);
		initialValues.put(KEY_TASKS_DATE_CREATION, date_creation);
		initialValues.put(KEY_TASKS_SOMEDAY, int_someday);
		initialValues.put(KEY_TASKS_DATE_START, date_start);
		initialValues.put(KEY_TASKS_DATE_COMPLETED, date_completed);
		initialValues.put(KEY_TASKS_DATE_ABANDONED, date_abandoned);
		initialValues.put(KEY_TASKS_REPEAT, repeat);
		initialValues.put(KEY_TASKS_REPEAT_UNITS, repeat_units);
		initialValues.put(KEY_TASKS_REPEAT_FROM, int_repeat_from);
		initialValues.put(KEY_TASKS_SIMULTANEOUS, int_simultaneous);
		initialValues.put(KEY_TASKS_DATE_START, date_start);
		try{
			return mDb.insert(DATABASE_TABLE_TASKS, null, initialValues);
		}catch (Exception ex) {
			return -1;
		}
	}
	
	public void addTaskChild(Task parent, Task child) {
		//Check if the task is already on the database. If not, add it.
		if(child.getId() == Task.NEW_TASK) {
			long rowId = addTask(child);
			child.setId(rowId);
		}

		//Check if the task is already on the database. If not, add it.
		if(parent.getId() == Task.NEW_TASK) {
			long rowId = addTask(parent);
			parent.setId(rowId);
		}
		
		//Both tasks should already be on the database, link them.
		addTaskChild(parent.getId(), child.getId(), child.getOrder());
	}
	
	private void addTaskChild(long parentId, long childId, int order) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_FAMILIES_PARENTID, parentId);
		cv.put(KEY_FAMILIES_CHILDID, childId);
		cv.put(KEY_FAMILIES_ORDER, order);
		try{
			mDb.insert(DATABASE_TABLE_FAMILIES, null, cv);
		}catch (Exception ex) {
			
		}
	}
	
	public void beginTransaction() {
		mDb.execSQL("BEGIN");
	}
	
	public void commit() {
		mDb.execSQL("COMMIT");
	}
}
