package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:34
 * 
 * Modifications by ERL:
 * Date: 14/04/2011
 */
public class ContextsTasks {
    /*
	Union table: ContextsTasks

	ID			integer primary key	first field in every tab
	task_id		integer	foreign key back to Tasks table (may create new values)
	context_id	integer	foreign key back to Contexts table (may create new values)
 */
	public static final String KEY_TASKCONTEXTS_ID 			= "ID";
    public static final String KEY_TASKCONTEXTS_TASKID 		= "task_id";
    public static final String KEY_TASKCONTEXTS_CONTEXTID 	= "context_id";
    public static final String DATABASE_TABLE_TASKCONTEXTS 	= "Tickler_ContextsTasks";
    public static final String DATABASE_CREATE_TASKCONTEXTS = String.format("CREATE TABLE IF NOT EXISTS %s (" +
    		"%s INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"%s INT(5) NOT NULL," +
            "%s INT(5) NOT NULL)",
            new Object[]{
                    DATABASE_TABLE_TASKCONTEXTS,
                    KEY_TASKCONTEXTS_ID,
                    KEY_TASKCONTEXTS_TASKID,
                    KEY_TASKCONTEXTS_CONTEXTID
            });
}
