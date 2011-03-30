package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:34
 */
public class TasksContexts {
    /*
	Union table: Taskcontexts

	taskid	integer	foreign key back to Tasks table (may create new values)
	contextid	integer	foreign key back to Contexts table (may create new values)
 */
    public static final String KEY_TASKCONTEXTS_TASKID = "taskid";
    public static final String KEY_TASKCONTEXTS_CONTEXTID = "contextid";
    public static final String DATABASE_TABLE_TASKCONTEXTS = "taskcontexts";
    public static final String DATABASE_CREATE_TASKCONTEXTS = String.format("create table %s (" +
            "%s integer," +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_TASKCONTEXTS,
                    KEY_TASKCONTEXTS_TASKID,
                    KEY_TASKCONTEXTS_CONTEXTID
            });
}
