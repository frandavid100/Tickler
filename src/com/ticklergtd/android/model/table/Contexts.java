package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:33
 */
public class Contexts {
    /*
        Table: Contexts

        _id	integer primary key	first field in every tab
        name	text	name of the context
        icon	integer	icon of the context
        activetime	integer	foreign key back to Contextimes union table (may create new values)
        activeregion	integer	foreign key back to Contextregions union table (may create new values)
    */
    public static final String KEY_CONTEXTS_ID = "_id";
    public static final String KEY_CONTEXTS_NAME = "name";
    public static final String KEY_CONTEXTS_ICON = "icon";
    //	public static final String KEY_CONTEXTS_ACTIVE_TIME = "activetime"; //Unneded?
//	public static final String KEY_CONTEXTS_ACTIVE_REGION = "activeregion"; //Unneeded?
    public static final String DATABASE_TABLE_CONTEXTS = "contexts";
    public static final String DATABASE_CREATE_CONTEXTS = String.format("create table %s (" +
            "%s integer primary key autoincrement, " +
            "%s text, " +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_CONTEXTS,
                    KEY_CONTEXTS_ID,
                    KEY_CONTEXTS_NAME,
                    KEY_CONTEXTS_ICON
            });

}
