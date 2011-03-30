package com.ticklergtd.android.model.table;

/**
 * Created by IntelliJ IDEA.
 * User: yeradis
 * Date: 30/03/11
 * Time: 13:35
 */
public class Families {

    /*
       Union table: Families

       parentid	integer	foreign key back to Tasks table
       childid	integer	foreign key back to Tasks table (may create new values)
       order	integer	order inside the group
    */
    public static final String KEY_FAMILIES_PARENTID = "parentid";
    public static final String KEY_FAMILIES_CHILDID = "childid";
    public static final String KEY_FAMILIES_ORDER = "order";
    public static final String DATABASE_TABLE_FAMILIES = "families";
    public static final String DATABASE_CREATE_FAMILIES = String.format("create table %s (" +
            "%s integer," +
            "%s integer," +
            "%s integer)",
            new Object[]{
                    DATABASE_TABLE_FAMILIES,
                    KEY_FAMILIES_PARENTID,
                    KEY_FAMILIES_CHILDID,
                    KEY_FAMILIES_ORDER
            });

}
