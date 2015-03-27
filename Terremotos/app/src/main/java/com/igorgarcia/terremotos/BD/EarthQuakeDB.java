package com.igorgarcia.terremotos.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.igorgarcia.terremotos.R;

/**
 * Created by cursomovil on 27/03/15.
 */
public class EarthQuakeDB {


    private EarthQuakeOpenHelper helper;
    private SQLiteDatabase db;

    public EarthQuakeDB(Context context) {
        this.helper = new EarthQuakeOpenHelper(context, EarthQuakeOpenHelper.DATABASE_TABLE, null, 1);
        this.db = helper.getWritableDatabase();
    }


    private static class EarthQuakeOpenHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "Terremotos.db"; //getString(R.string.DATABASE_NAME);
        private static final String DATABASE_TABLE = "Terremotos";
        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_CREATE = "CREATE Table " + DATABASE_TABLE +
                "(_id  TEXT PRIMARY KEY, place TEXT, magnitude REAL, lat REAL , Long REAL, url TEXT,depth REAL, time INTEGER)";


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private EarthQuakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

    }


}
