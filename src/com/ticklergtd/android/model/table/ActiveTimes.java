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
    public static final String DATABASE_CREATE_ACTIVETIMES = String.format("create table %s (" +
            "%s integer primary key autoincrement, " +
            "%s text, " +
            "%s integer, " +
            "%s integer, " +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_ACTIVETIMES, KEY_ACTIVETIMES_ID,
                    KEY_ACTIVETIMES_NAME, KEY_ACTIVETIMES_START,
                    KEY_ACTIVETIMES_END, KEY_ACTIVETIMES_DAY
            });

}
