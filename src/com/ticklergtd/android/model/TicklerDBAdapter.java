package com.ticklergtd.android.model;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ticklergtd.android.Utilities;
import com.ticklergtd.android.model.table.ActiveRegions;
import com.ticklergtd.android.model.table.ActiveTimes;
import com.ticklergtd.android.model.table.ContextRegions;
import com.ticklergtd.android.model.table.ContextTimes;
import com.ticklergtd.android.model.table.Contexts;
import com.ticklergtd.android.model.table.ContextsTasks;
import com.ticklergtd.android.model.table.Families;
import com.ticklergtd.android.model.table.Tasks;

public class TicklerDBAdapter {

	private static final String TAG = "TicklerDBAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private boolean isOpen = false;

	private static final String DATABASE_NAME = "ticklerDB.sqlite";
	private static final int DATABASE_VERSION = 1;

	private Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(ActiveRegions.DATABASE_CREATE_ACTIVEREGIONS);
			db.execSQL(ActiveTimes.DATABASE_CREATE_ACTIVETIMES);
			db.execSQL(ContextRegions.DATABASE_CREATE_CONTEXTREGIONS);
			db.execSQL(Contexts.DATABASE_CREATE_CONTEXTS);
			db.execSQL(ContextTimes.DATABASE_CREATE_CONTEXTTIMES);
			db.execSQL(Families.DATABASE_CREATE_FAMILIES); 
			db.execSQL(ContextsTasks.DATABASE_CREATE_TASKCONTEXTS);
			db.execSQL(Tasks.DATABASE_CREATE_TASKS);
			//TODO: Create needed indexes
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i(TAG, "Upgrading from " + oldVersion + " to " + newVersion);
			//TODO: Add updgrade script
		}
		
		public void loadTest(SQLiteDatabase db) {
			String sSql = "";
			
			sSql = "DELETE FROM Tickler_ContextsTasks";
			db.execSQL(sSql);
			
			sSql = "DELETE FROM Tickler_Contexts";
			db.execSQL(sSql);
			
			sSql = "DELETE FROM Tickler_Tasks";
			db.execSQL(sSql);
			
			sSql = "INSERT INTO Tickler_Contexts (id,name,icon) values (1,'Casa',1)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_Contexts (id,name,icon) values (2,'Oficina',1)";
			db.execSQL(sSql);
			
			sSql = "INSERT INTO Tickler_Tasks (id,name,dt_start,dt_deadline,priority,someday) values (1,'TASK 1','2011-04-24 14:00','2011-04-26 14:00',1,0)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_Tasks (id,name,dt_start,dt_deadline,priority,someday) values (2,'TASK 2','2011-04-25 15:00',null,2,0)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_Tasks (id,name,dt_start,dt_deadline,priority,someday) values (3,'TASK 3',null,null,3,1)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_Tasks (id,name,dt_start,dt_deadline,priority,someday) values (4,'TASK 4','2011-04-01 09:00','2011-05-07 09:00',3,0)";
			db.execSQL(sSql);
			
			sSql = "INSERT INTO Tickler_ContextsTasks (task_id,context_id) values (1,1)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_ContextsTasks (task_id,context_id) values (1,2)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_ContextsTasks (task_id,context_id) values (2,2)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_ContextsTasks (task_id,context_id) values (3,2)";
			db.execSQL(sSql);
			sSql = "INSERT INTO Tickler_ContextsTasks (task_id,context_id) values (4,1)";
			db.execSQL(sSql);
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
			mDbHelper.loadTest(mDb);
			
		}
		isOpen = true;
		return this;
	}

	public void close() {
		if(isOpen) mDbHelper.close();
		isOpen = false;
	}

	// TAREAS
	public Cursor selectTasks_Smart(){
		return mDb.query(
				Tasks.DATABASE_TABLE_TASKS,
				new String[] { Tasks.KEY_TASKS_ID, Tasks.KEY_TASKS_NAME,
						Tasks.KEY_TASKS_PRIORITY, Tasks.KEY_TASKS_NOTE,
						Tasks.KEY_TASKS_DATE_CREATION, Tasks.KEY_TASKS_SOMEDAY,
						Tasks.KEY_TASKS_DATE_START, Tasks.KEY_TASKS_DATE_DEADLINE,
						Tasks.KEY_TASKS_DATE_COMPLETED, Tasks.KEY_TASKS_DATE_ABANDONED,
						Tasks.KEY_TASKS_REPEAT, Tasks.KEY_TASKS_REPEAT_UNITS,
						Tasks.KEY_TASKS_REPEAT_FROM, Tasks.KEY_TASKS_SIMULTANEOUS},
				Tasks.KEY_TASKS_ID + " IN (" + getActiveContexts() + ")",
				null,
				null,
				null,
				Tasks.KEY_TASKS_DATE_START + " ASC"
		);
	}
	
	public Cursor selectTasks_Full(){
		return mDb.query(
				Tasks.DATABASE_TABLE_TASKS,
				new String[] { Tasks.KEY_TASKS_ID, Tasks.KEY_TASKS_NAME,
						Tasks.KEY_TASKS_PRIORITY, Tasks.KEY_TASKS_NOTE,
						Tasks.KEY_TASKS_DATE_CREATION, Tasks.KEY_TASKS_SOMEDAY,
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
	
	public Cursor selectTask(long task_id) {
		return mDb.query(
				Tasks.DATABASE_TABLE_TASKS,
				new String[] { Tasks.KEY_TASKS_ID, Tasks.KEY_TASKS_NAME,
						Tasks.KEY_TASKS_PRIORITY, Tasks.KEY_TASKS_NOTE,
						Tasks.KEY_TASKS_DATE_CREATION, Tasks.KEY_TASKS_SOMEDAY,
						Tasks.KEY_TASKS_DATE_START, Tasks.KEY_TASKS_DATE_DEADLINE,
						Tasks.KEY_TASKS_DATE_COMPLETED, Tasks.KEY_TASKS_DATE_ABANDONED,
						Tasks.KEY_TASKS_REPEAT, Tasks.KEY_TASKS_REPEAT_UNITS,
						Tasks.KEY_TASKS_REPEAT_FROM, Tasks.KEY_TASKS_SIMULTANEOUS},
				Tasks.KEY_TASKS_ID + "=" + task_id,
				null,
				null,
				null,
				Tasks.KEY_TASKS_DATE_START + " ASC"
		);
	}
	
	public long addTask(Task task) {
		long rowId = addTask(task.getId(),task.getName(), task.getPriority(), task.getNote(), task.getCreationDate(), task.getSomeday(), task.getStartDate(), task.getDeadline(),
				task.getCompleted(), task.getAbandoned(), task.getRepeat(), task.getRepeatUnits(), task.getRepeatFrom(), task.isSimultaneous());
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

	private long addTask(long id, String name, int priority, String note, Date date_creation, int someday, Date date_start, Date date_deadline, Date date_completed,
			Date date_abandoned, int repeat, int repeat_units, int repeat_from, int simultaneous) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(Tasks.KEY_TASKS_NAME, name);
		initialValues.put(Tasks.KEY_TASKS_PRIORITY, priority);
		initialValues.put(Tasks.KEY_TASKS_DATE_CREATION, Utilities.date2String(date_creation,1));
		initialValues.put(Tasks.KEY_TASKS_SOMEDAY, someday);
		initialValues.put(Tasks.KEY_TASKS_DATE_START, Utilities.date2String(date_start,1));
		initialValues.put(Tasks.KEY_TASKS_DATE_COMPLETED, Utilities.date2String(date_completed,1));
		initialValues.put(Tasks.KEY_TASKS_DATE_ABANDONED, Utilities.date2String(date_abandoned,1));
		initialValues.put(Tasks.KEY_TASKS_REPEAT, repeat);
		initialValues.put(Tasks.KEY_TASKS_REPEAT_UNITS, repeat_units);
		initialValues.put(Tasks.KEY_TASKS_REPEAT_FROM, repeat_from);
		initialValues.put(Tasks.KEY_TASKS_SIMULTANEOUS, simultaneous);

		long lRes = -1;
		if (id > 0) {
			lRes = mDb.update(Tasks.DATABASE_TABLE_TASKS, initialValues, "id=" + id, null);
		}
		else {
			lRes = mDb.insert(Tasks.DATABASE_TABLE_TASKS, null, initialValues);
		}
		return lRes;

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
		cv.put(Families.KEY_FAMILIES_PARENTID, parentId);
		cv.put(Families.KEY_FAMILIES_CHILDID, childId);
		cv.put(Families.KEY_FAMILIES_ORDER, order);
		try{
			mDb.insert(Families.DATABASE_TABLE_FAMILIES, null, cv);
		}catch (Exception ex) {
			
		}
	}
	
	private String getActiveContexts() {
		String lsRes = "";
		
		ArrayList<ContextTask> tcs = ContextTask.getAllContextsTask(this.mCtx);
		
		for (int i=0; i<tcs.size(); i++) {
			if (tcs.get(i).applyContext()) {
				lsRes += "," + tcs.get(i).getId();
			}
		}

		if (lsRes.charAt(0) == ',') {
			lsRes = lsRes.substring(1);
		}

		return lsRes;
	}

	public void beginTransaction() {
		mDb.execSQL("BEGIN");
	}
	
	public void commit() {
		mDb.execSQL("COMMIT");
	}
	
	//***********************************************
	public Cursor selectContextTasks(long task_id){
		String sSql = "";
		
		sSql = "select ContextsTasks.task_id, ContextsTasks.context_id, Contexts.name " + 
				" from Tickler_ContextsTasks as ContextsTasks inner join Tickler_Contexts as Contexts on ContextsTasks.context_id = Contexts.id " +
				" where ContextsTasks.task_id=" + task_id;
		
		return mDb.rawQuery(sSql, null);
		
	}

	public Cursor getContextTasksList(){
		String sSql = "";
		
		sSql = "select Contexts.id, Contexts.name, Contexts.icon, Contexts.name , Contexts.notifications " + 
				" from Tickler_Contexts as Contexts ";
		return mDb.rawQuery(sSql, null);
	}
}
