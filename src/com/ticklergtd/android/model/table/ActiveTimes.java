package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:30
 */
public class ActiveTimes {
    /*
       Table: Activetimes

       ID		integer primary key	first field in every tab
       name		text	schedule name
       start	date	start time
       end		date	end date
       day		integer	day of the week
    */
    public static final String KEY_ACTIVETIMES_ID 			= "ID";
    public static final String KEY_ACTIVETIMES_NAME 		= "name";
    public static final String KEY_ACTIVETIMES_START 		= "start";
    public static final String KEY_ACTIVETIMES_END 			= "end";
    public static final String KEY_ACTIVETIMES_DAY 			= "day";
    public static final String DATABASE_TABLE_ACTIVETIMES 	= "Tickler_Activetimes";
    public static final String DATABASE_CREATE_ACTIVETIMES 	= String.format("create table %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s VARCHAR NOT NULL, " +
            "%s NOT NULL, " +
            "%s NOT NULL, " +
            "%s VARCHAR NOT NULL DEFAULT '1234567')",
            new Object[]{
                    DATABASE_TABLE_ACTIVETIMES, KEY_ACTIVETIMES_ID,
                    KEY_ACTIVETIMES_NAME, KEY_ACTIVETIMES_START,
                    KEY_ACTIVETIMES_END, KEY_ACTIVETIMES_DAY
            });

}
