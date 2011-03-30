package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 12:45
 */
public class Tasks {
    /*
	Table: Tasks

	_id	integer primary key	first field in every tab
	name	text	name of the task
	priority	integer	priority (1, 2, 3)
	dt_creation	date	creation date
	someday	boolean	is it a �someday� task?
	dt_start	date	start date (only if someday = F), can be NULL
	dt_deadline	date	deadline date, can be NULL
	dt_completed	date	when it was completed, can be NULL
	dt_abandoned	date	when it was abandoned, can be NULL
	repeat	integer	amount of units before repeating
	rp_units	integer	1 = days, 2 = weeks, 3 = months, 4 = years
	rp_from	boolean	if T, the new task�s dt_start is taken from the old one�s dt_start; if it doesn�t have one, it�s taken from its dt_creation.
	if F, the new task�s dt_start is taken from the old one�s dt_completed.
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
    public static final String DATABASE_CREATE_TASKS = String.format("create table %s (" +
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
            new Object[]{
                    DATABASE_TABLE_TASKS, KEY_TASKS_ID, KEY_TASKS_NAME,
                    KEY_TASKS_PRIORITY, KEY_TASKS_DATE_CREATION,
                    KEY_TASKS_SOMEDAY, KEY_TASKS_DATE_START,
                    KEY_TASKS_DATE_START, KEY_TASKS_DATE_DEADLINE,
                    KEY_TASKS_DATE_COMPLETED, KEY_TASKS_DATE_ABANDONED,
                    KEY_TASKS_REPEAT, KEY_TASKS_REPEAT_UNITS,
                    KEY_TASKS_REPEAT_FROM, KEY_TASKS_SIMULTANEOUS
            });
}
