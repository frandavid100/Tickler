package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:31
 */
public class ActiveRegions {

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
    public static final String DATABASE_CREATE_ACTIVEREGIONS = String.format("create table %s (" +
            "%s integer primary key autoincrement, " +
            "%s text, " +
            "%s integer, " +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_ACTIVEREGIONS, KEY_ACTIVEREGIONS_ID,
                    KEY_ACTIVEREGIONS_NAME, KEY_ACTIVEREGIONS_LOCATION,
                    KEY_ACTIVEREGIONS_RADIUS
            });

}
