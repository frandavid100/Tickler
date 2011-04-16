package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:35
 * 
 * Modifications by ERL:
 * Date: 14/04/2011
 */
public class Families {

    /*
       Union table: Families

       parentid	integer	foreign key back to Tasks table
       childid	integer	foreign key back to Tasks table (may create new values)
       order	integer	order inside the group
    */
	public static final String KEY_FAMILIES_ID = "ID";
    public static final String KEY_FAMILIES_PARENTID = "parent_id";
    public static final String KEY_FAMILIES_CHILDID = "child_id";
    public static final String KEY_FAMILIES_ORDER = "no_order";
    public static final String DATABASE_TABLE_FAMILIES = "Tickler_Families";
    public static final String DATABASE_CREATE_FAMILIES = String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            "%s NOT NULL INT(5)," +
            "%s NOT NULL INT(5)," +
            "%s INT(3) DEFAULT 1)",
            new Object[]{
                    DATABASE_TABLE_FAMILIES,
                    KEY_FAMILIES_ID,
                    KEY_FAMILIES_PARENTID,
                    KEY_FAMILIES_CHILDID,
                    KEY_FAMILIES_ORDER
            });
}
