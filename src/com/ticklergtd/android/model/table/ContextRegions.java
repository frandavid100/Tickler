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
public class ContextRegions {
    /*
	Union table: Contextregions

	ID			integer primary key	first field in every tab
	context_id	integer	foreign key back to Context table
	region_id	integer	foreign key back to Activeregions table (may create new values, a context may have several activeregions)
	enabled		boolean	whether the Activeregion must be taken into account
 */
	public static final String KEY_CONTEXTREGIONS_ID 			= "ID";
    public static final String KEY_CONTEXTREGIONS_CONTEXTID 	= "context_id";
    public static final String KEY_CONTEXTREGIONS_REGIONID 		= "region_id";
    public static final String KEY_CONTEXTREGIONS_ACTIVE 		= "enabled";
    public static final String DATABASE_TABLE_CONTEXTREGIONS	= "Tickler_ContextsActiveregions";
    public static final String DATABASE_CREATE_CONTEXTREGIONS 	= String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            "%s NOT NULL INT(5)," +
            "%s NOT NULL INT(5)," +
            "%s INT(1) DEFAULT 1)",
            new Object[]{
                    DATABASE_TABLE_CONTEXTREGIONS,
                    KEY_CONTEXTREGIONS_ID,
                    KEY_CONTEXTREGIONS_CONTEXTID,
                    KEY_CONTEXTREGIONS_REGIONID,
                    KEY_CONTEXTREGIONS_ACTIVE
            });
}
