package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:31
 * 
 * Modifications by ERL:
 * Date: 14/04/2011
 */
public class ActiveRegions {

    /*
       Table: Activeregions

       ID				integer primary key	first field in every tab
       name				text	region name
       location_long	float	geographical position
       location_lat		float	geographical position
       radius			float	radius around location (in km)
    */
    public static final String KEY_ACTIVEREGIONS_ID 			= "ID";
    public static final String KEY_ACTIVEREGIONS_NAME 			= "name";
    public static final String KEY_ACTIVEREGIONS_LOCATION_LAT 	= "location_lat";
    public static final String KEY_ACTIVEREGIONS_LOCATION_LONG	= "location_long";
    public static final String KEY_ACTIVEREGIONS_RADIUS 		= "radius_km";
    public static final String DATABASE_TABLE_ACTIVEREGIONS 	= "Tickler_Activeregions";
    public static final String DATABASE_CREATE_ACTIVEREGIONS 	= String.format("create table %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s VARCHAR NOT NULL, " +
            "%s REAL, " +
            "%s REAL, " +
            "%s REAL DEFAULT 0.5)",
            new Object[]{
                    DATABASE_TABLE_ACTIVEREGIONS, KEY_ACTIVEREGIONS_ID,
                    KEY_ACTIVEREGIONS_NAME, KEY_ACTIVEREGIONS_LOCATION_LONG,
                    KEY_ACTIVEREGIONS_LOCATION_LAT, KEY_ACTIVEREGIONS_RADIUS
            });

}
