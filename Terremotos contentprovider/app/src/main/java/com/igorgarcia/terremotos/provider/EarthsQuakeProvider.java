package com.igorgarcia.terremotos.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.widget.Switch;

public class EarthsQuakeProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://com.igorgarcia.provider.terremotos/terremotos");
    public static final String DBTable_Name = EarthQuakeOpenHelper.DATABASE_TABLE;

    private static final int ALL_ROWS = 1;
    private static final int SINGLE_ROW = 2;

    private static final UriMatcher uriMatcher;

    private EarthQuakeOpenHelper earthQuakeOpenHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.igorgarcia.provider.terremotos", "elements", ALL_ROWS);
        uriMatcher.addURI("com.igorgarcia.provider.terremotos", "elements/#", SINGLE_ROW);
    }


    public static class Columns implements BaseColumns {
        //	Create	a	new	row	of	values	to	insert.
        public static final String Key_Terremoto_id = "_id";
        public static final String Key_Terremoto_Place = "place";
        public static final String Key_Terremoto_Magnitud = "magnitude";
        public static final String Key_Terremoto_Latitud = "lat";
        public static final String Key_Terremoto_Longitud = "Long";
        public static final String Key_Terremoto_Url = "url";
        public static final String Key_Terremoto_Profundidad = "depth";
        public static final String Key_Terremoto_time = "time";
    }

    public String GetDbName() {
        return earthQuakeOpenHelper.getDatabaseName();
    }

    public String GetDBDTName() {
        return "Terremotos";
    }

    @Override
    public String getType(Uri uri) {

        switch(uriMatcher.match(uri)) {
            case ALL_ROWS:
                return "vnd.android.cursor.dir/vnd.igorgarcia.provider.terremotos";
            case SINGLE_ROW:
                return "vnd.android.cursor.item/vnd.igorgarcia.provider.terremotos";
            default:
                throw new IllegalArgumentException("Unsupported URI:" + uri);

        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = earthQuakeOpenHelper.getWritableDatabase();
/*        //	If	this	is	a	row	query,	limit	the	result	set	to	the	passed	in	row.
        switch (uriMatcher.match(uri)) {
            case ALL_ROWS:
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(Columns.Key_Terremoto_id + "=?");
                selectionArgs = new String[]{rowID};
            default:
                break;
        }*/

//	Insert	the	values	into	the	table

        long id = db.insert(EarthQuakeOpenHelper.DATABASE_TABLE, null, values);

        //	Construct	and	return	the	URI	of	the	newly	inserted	row.
        if (id > -1) {

            //	Construct	and	return	the	URI	of	the	newly	inserted	row.
            Uri insertedId = ContentUris.withAppendedId(CONTENT_URI, id);

            //	Notify	any	observers	of	the	change	in	the	data	set.
            getContext().getContentResolver().notifyChange(insertedId, null);
            //ESTA LINEA DE ARRIBA ES EL QUE HQCE LQ MQGIQ DE QVISQR A TODOS EL MUNDO, QUE SE HA INSERTADO ALGUN VALOR
            return insertedId;
        } else {
            return null;
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        earthQuakeOpenHelper = new EarthQuakeOpenHelper(getContext(), EarthQuakeOpenHelper.DATABASE_NAME,null,EarthQuakeOpenHelper.DATABASE_VERSION);

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db;
        try {
            db = earthQuakeOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = earthQuakeOpenHelper.getReadableDatabase();
        }


        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();


//	If	this	is	a	row	query,	limit	the	result	set	to	the	passed	in	row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(Columns.Key_Terremoto_id + "=?");
                selectionArgs = new String[]{rowID};
            default:
                break;
        }

        queryBuilder.setTables(earthQuakeOpenHelper.DATABASE_TABLE);
        //	Execute	the	query.
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        return cursor;

    }


    private static class EarthQuakeOpenHelper extends SQLiteOpenHelper {
        //Al principio se crea la BD
        private static final String DATABASE_NAME = "Terremotos.db"; //getString(R.string.DATABASE_NAME);
        private static final String DATABASE_TABLE = "Terremotos";
        private static final int DATABASE_VERSION = 2;

        private static final String DATABASE_CREATE = "CREATE Table " + DATABASE_TABLE +
                "(" + Columns.Key_Terremoto_id + "  TEXT PRIMARY KEY," + Columns.Key_Terremoto_Place + " TEXT, " + Columns.Key_Terremoto_Magnitud + " REAL, " + Columns.Key_Terremoto_Latitud + " REAL , " + Columns.Key_Terremoto_Longitud + " REAL, " + Columns.Key_Terremoto_Url + " TEXT," + Columns.Key_Terremoto_Profundidad + " REAL, " + Columns.Key_Terremoto_time + " INTEGER)";


        public static String getDatabaseTable() {
            return DATABASE_TABLE;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE " + DATABASE_TABLE);
            onCreate(db);
        }


        private EarthQuakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

    }


    //NO UTILIZAMOS!!!!!!!
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
