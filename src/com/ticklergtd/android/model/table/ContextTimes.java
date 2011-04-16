package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:36
 * 
 * Modifications by ERL:
 * Date: 14/04/2011
 */
public class ContextTimes {
    /*
       	Union table: ContextActivetimes

		ID			integer primary key	first field in every tab
       	context_id	integer	foreign key back to Context table
       	time_id		integer	foreign key back to Activetimes table (may create new values, a context may have several activetimes)
       	enaled		boolean	whether the Activetime must be taken into account
    */
	public static final String KEY_CONTEXTTIMES_ID 			= "ID";
    public static final String KEY_CONTEXTTIMES_CONTEXTID 	= "context_id";
    public static final String KEY_CONTEXTTIMES_TIMEID 		= "activetime_id";
    public static final String KEY_CONTEXTTIMES_ACTIVE 		= "enabled";
    public static final String DATABASE_TABLE_CONTEXTTIMES	= "Tickler_ContextActivetimes";
    public static final String DATABASE_CREATE_CONTEXTTIMES = String.format("CREATE TABLE IF NOT EXISTS %s (" +
    		"%s INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"%s INT(5) NOT NULL," +
            "%s INT(5) NOT NULL," +
            "%s INT(1) DEFAULT 1)",
            new Object[]{
                    DATABASE_TABLE_CONTEXTTIMES,
                    KEY_CONTEXTTIMES_ID,
                    KEY_CONTEXTTIMES_CONTEXTID,
                    KEY_CONTEXTTIMES_TIMEID,
                    KEY_CONTEXTTIMES_ACTIVE
            });

}
