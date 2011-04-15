package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 12:45
 * 
 * Modifications by ERL:
 * Date: 14/04/2011
 */
public class Tasks {
    /*
	Table: Tasks

	ID				integer primary key	first field in every tab
	name			text	name of the task
	priority		integer	priority (1, 2, 3)
	note 			varchar
	dt_creation		date	creation date
	dt_start		date	start date (only if someday = F), can be NULL
	dt_deadline		date	deadline date, can be NULL
	dt_completed	date	when it was completed, can be NULL
	dt_abandoned	date	when it was abandoned, can be NULL
	dt_begin_work 	date	when it was abandoned, can be NULL
	dt_end_work 	date	when it was abandoned, can be NULL
	mins_working	integer	diff between dt_end_working and dt_start_working
	someday			boolean	is it a �someday� task?
	repeat			integer	amount of units before repeating
	rp_units		integer	1 = days, 2 = weeks, 3 = months, 4 = years
	rp_from			boolean	if T, the new task�s dt_start is taken from the old one�s dt_start; if it doesn�t have one, it�s taken from its dt_creation.
							if F, the new task�s dt_start is taken from the old one�s dt_completed.
	rp_parent		integer	
	simultaneous	boolean	if T, children can be done simultaneously
*/
    public static final String KEY_TASKS_ID 			= "ID";
    public static final String KEY_TASKS_NAME 			= "name";
    public static final String KEY_TASKS_NOTE			= "note";
    public static final String KEY_TASKS_PRIORITY 		= "priority";
    public static final String KEY_TASKS_DATE_CREATION 	= "dt_creation";
    public static final String KEY_TASKS_DATE_START 	= "dt_start";
    public static final String KEY_TASKS_DATE_DEADLINE 	= "dt_deadline";
    public static final String KEY_TASKS_DATE_COMPLETED = "dt_completed";
    public static final String KEY_TASKS_DATE_ABANDONED = "dt_abandoned";
    public static final String KEY_TASKS_DATE_BEGINWORK	= "dt_begin_work";
    public static final String KEY_TASKS_DATE_ENDWORK	= "dt_end_work";
    public static final String KEY_TASKS_MINS_WORKING	= "dt_mins_working";
    public static final String KEY_TASKS_SOMEDAY 		= "someday";
    public static final String KEY_TASKS_REPEAT 		= "repeat";
    public static final String KEY_TASKS_REPEAT_UNITS 	= "rp_unit_id";
    public static final String KEY_TASKS_REPEAT_FROM 	= "rp_from";
    public static final String KEY_TASKS_REPEAT_PARENT	= "rp_parent";
    public static final String KEY_TASKS_SIMULTANEOUS 	= "simultaneous";
    public static final String DATABASE_TABLE_TASKS = "Tickler_Tasks";
    public static final String DATABASE_CREATE_TASKS = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s VARCHAR NOT NULL, " +
            "%s INT(1) NOT NULL DEFAULT 2, " +
            "%s VARCHAR, " +
            "%s NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
            "%s , " +
            "%s , " +
            "%s , " +
            "%s , " +
            "%s , " +
            "%s , " +
            "%s INT(5), " +
            "%s INT(1) NOT NULL DEFAULT 0, " +
            "%s INT(1) DEFAULT 0, " +
            "%s INT(2) DEFAULT 2, " +
            "%s INT(1) DEFAULT 0, " +
            "%s INT(5), " +
            "%s INT(1) DEFAULT 0)",
            new Object[]{
                    DATABASE_TABLE_TASKS, KEY_TASKS_ID, KEY_TASKS_NAME, 
                    KEY_TASKS_PRIORITY, KEY_TASKS_NOTE, KEY_TASKS_DATE_CREATION,
                    KEY_TASKS_DATE_START,KEY_TASKS_DATE_DEADLINE,
                    KEY_TASKS_DATE_COMPLETED, KEY_TASKS_DATE_ABANDONED,
                    KEY_TASKS_DATE_BEGINWORK, KEY_TASKS_DATE_ENDWORK,
                    KEY_TASKS_MINS_WORKING, KEY_TASKS_SOMEDAY, 
                    KEY_TASKS_REPEAT, KEY_TASKS_REPEAT_UNITS,
                    KEY_TASKS_REPEAT_FROM, KEY_TASKS_REPEAT_PARENT, KEY_TASKS_SIMULTANEOUS
            });
}

