package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:33
 * 
 * Modifications by ERL:
 * Date: 14/04/2011
 */
public class Contexts {
    /*
        Table: Tickler_Contexts

        ID				integer primary key	first field in every tab
        name			text	name of the context
        icon			integer	icon of the context
        notifications	integer	show notifications?    ERL 14/04/2011
    */
    public static final String KEY_CONTEXTS_ID 				= "ID";
    public static final String KEY_CONTEXTS_NAME 			= "name";
    public static final String KEY_CONTEXTS_ICON 			= "icon";
    public static final String KEY_CONTEXTS_NOTIFICATIONS 	= "notifications";
    public static final String DATABASE_TABLE_CONTEXTS 		= "Tickler_Contexts";
    public static final String DATABASE_CREATE_CONTEXTS 	= String.format("CREATE TABLE IF NOT EXISTS %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s VARCHAR, " +
            "%s INT(3) NOT NULL DEFAULT 1, " + 
            "%s INT(1) NOT NULL DEFAULT 0)",
            new Object[]{
                    DATABASE_TABLE_CONTEXTS,
                    KEY_CONTEXTS_ID,
                    KEY_CONTEXTS_NAME,
                    KEY_CONTEXTS_ICON,
                    KEY_CONTEXTS_NOTIFICATIONS
            });
}
