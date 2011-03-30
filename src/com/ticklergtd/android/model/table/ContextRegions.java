package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:36
 */
public class ContextRegions {
    /*
	Union table: Contextregions

	contextid	integer	foreign key back to Context table
	regionid	integer	foreign key back to Activeregions table (may create new values, a context may have several activeregions)
	active	boolean	whether the Activeregion must be taken into account
 */
    public static final String KEY_CONTEXTREGIONS_CONTEXTID = "contextid";
    public static final String KEY_CONTEXTREGIONS_REGIONID = "regionid";
    public static final String KEY_CONTEXTREGIONS_ACTIVE = "active";
    public static final String DATABASE_TABLE_CONTEXTREGIONS = "contextregions";
    public static final String DATABASE_CREATE_CONTEXTREGIONS = String.format("create table %s (" +
            "%s integer," +
            "%s integer," +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_CONTEXTREGIONS,
                    KEY_CONTEXTREGIONS_CONTEXTID,
                    KEY_CONTEXTREGIONS_REGIONID,
                    KEY_CONTEXTREGIONS_ACTIVE
            });
}
