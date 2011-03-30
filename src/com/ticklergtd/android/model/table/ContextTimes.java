package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:36
 */
public class ContextTimes {
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
    public static final String DATABASE_CREATE_CONTEXTTIMES = String.format("create table %s (" +
            "%s integer," +
            "%s integer," +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_CONTEXTTIMES,
                    KEY_CONTEXTTIMES_CONTEXTID,
                    KEY_CONTEXTTIMES_TIMEID,
                    KEY_CONTEXTTIMES_ACTIVE
            });

}
