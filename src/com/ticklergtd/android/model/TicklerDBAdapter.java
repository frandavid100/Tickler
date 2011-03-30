package com.ticklergtd.android.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.ticklergtd.android.model.table.Tasks;

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
			db.execSQL(Tasks.DATABASE_CREATE_TASKS);
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
				Tasks.DATABASE_TABLE_TASKS,
				new String[] { Tasks.KEY_TASKS_ID, Tasks.KEY_TASKS_NAME,
						Tasks.KEY_TASKS_PRIORITY, Tasks.KEY_TASKS_DATE_CREATION,
						Tasks.KEY_TASKS_SOMEDAY, Tasks.KEY_TASKS_DATE_START,
						Tasks.KEY_TASKS_DATE_START, Tasks.KEY_TASKS_DATE_DEADLINE,
						Tasks.KEY_TASKS_DATE_COMPLETED, Tasks.KEY_TASKS_DATE_ABANDONED,
						Tasks.KEY_TASKS_REPEAT, Tasks.KEY_TASKS_REPEAT_UNITS,
						Tasks.KEY_TASKS_REPEAT_FROM, Tasks.KEY_TASKS_SIMULTANEOUS},
				null,
				null,
				null,
				null,
				Tasks.KEY_TASKS_DATE_START + " ASC"
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
		initialValues.put(Tasks.KEY_TASKS_NAME, name);
		initialValues.put(Tasks.KEY_TASKS_PRIORITY, priority);
		initialValues.put(Tasks.KEY_TASKS_DATE_CREATION, date_creation);
		initialValues.put(Tasks.KEY_TASKS_SOMEDAY, int_someday);
		initialValues.put(Tasks.KEY_TASKS_DATE_START, date_start);
		initialValues.put(Tasks.KEY_TASKS_DATE_COMPLETED, date_completed);
		initialValues.put(Tasks.KEY_TASKS_DATE_ABANDONED, date_abandoned);
		initialValues.put(Tasks.KEY_TASKS_REPEAT, repeat);
		initialValues.put(Tasks.KEY_TASKS_REPEAT_UNITS, repeat_units);
		initialValues.put(Tasks.KEY_TASKS_REPEAT_FROM, int_repeat_from);
		initialValues.put(Tasks.KEY_TASKS_SIMULTANEOUS, int_simultaneous);
		initialValues.put(Tasks.KEY_TASKS_DATE_START, date_start);
		try{
			return mDb.insert(Tasks.DATABASE_TABLE_TASKS, null, initialValues);
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
