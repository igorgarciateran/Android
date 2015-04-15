package com.igorgarcia.terremotos.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.igorgarcia.terremotos.Model.Coordinate;
import com.igorgarcia.terremotos.Model.EarthQuake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cursomovil on 27/03/15.
 */
public class EarthQuakeDB {


   // private EarthQuakeOpenHelper helper;
    private SQLiteDatabase db;

    private Context mycontext;

/*
    //	Create	a	new	row	of	values	to	insert.
    public static final String Key_Terremoto_id = "_id";
    public static final String Key_Terremoto_Place = "place";
    public static final String Key_Terremoto_Magnitud = "magnitude";
    public static final String Key_Terremoto_Latitud = "lat";
    public static final String Key_Terremoto_Longitud = "Long";
    public static final String Key_Terremoto_Url = "url";
    public static final String Key_Terremoto_Profundidad = "depth";
    public static final String Key_Terremoto_time = "time";

*/
    public static final String[] ALL_COLUMNS = {
        EarthsQuakeProvider.Columns.Key_Terremoto_id,
        EarthsQuakeProvider.Columns.Key_Terremoto_Place,
        EarthsQuakeProvider.Columns.Key_Terremoto_Magnitud,
        EarthsQuakeProvider.Columns.Key_Terremoto_Latitud,
        EarthsQuakeProvider.Columns.Key_Terremoto_Longitud,
        EarthsQuakeProvider.Columns.Key_Terremoto_Url,
        EarthsQuakeProvider.Columns.Key_Terremoto_Profundidad,
        EarthsQuakeProvider.Columns.Key_Terremoto_time
    };

    HashMap<String, Integer> indexes = new HashMap<String, Integer>();

    private int getColumnIndex(Cursor cursor, String columnName) {
        //lo utilizamos para rellenar el HashMap
        return cursor.getColumnIndexOrThrow(columnName);
    }

    private void rellenaHashMap(Cursor cursor) {
        if (indexes.isEmpty()) {
            for (int i = 0; i < ALL_COLUMNS.length; i++) {
                indexes.put(ALL_COLUMNS[i], getColumnIndex(cursor, ALL_COLUMNS[i]));
            }
        }
    }


    public EarthQuakeDB(Context context) {
        /*this.helper = new EarthQuakeOpenHelper(context, EarthQuakeOpenHelper.DATABASE_TABLE, null, EarthQuakeOpenHelper.DATABASE_VERSION);
        this.db = helper.getWritableDatabase();*/
        mycontext=context ;
    }


    public void insertNewEarthQuake(EarthQuake Terremoto) {

        ContentValues newValues = new ContentValues();

        //	Assign	values	for	each	row.
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_id, Terremoto.get_id());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_Place, Terremoto.getPlace());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_Magnitud, Terremoto.getMagnitud());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_Latitud, Terremoto.getCoords().getLat());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_Longitud, Terremoto.getCoords().getLng());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_Url, Terremoto.getUrl().toString());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_Profundidad, Terremoto.getCoords().getDepth());
        newValues.put(EarthsQuakeProvider.Columns.Key_Terremoto_time, Terremoto.getTime().getTime());

        ContentResolver cr=mycontext.getContentResolver();
        cr.insert(EarthsQuakeProvider.CONTENT_URI,newValues);

    }


    public List<EarthQuake> listado() {
        return query(null, null);
    }

    public List<EarthQuake> ListadoXID(String ID) {

        String where = EarthsQuakeProvider.Columns.Key_Terremoto_id + " = ?";
        String[] whereArgs = {
                String.valueOf(ID)
        };

        return query(where , whereArgs);
    }



    public List<EarthQuake> listadoXMagnitud(int magnitud) {



        String where =EarthsQuakeProvider.Columns.Key_Terremoto_Magnitud + " >= ?";
        String[] whereArgs = {
                String.valueOf(magnitud)
        };

        //String orderby= EarthsQuakeProvider.Columns.Key_Terremoto_time+ " DESC";


        return query(where,whereArgs );
    }



    private List<EarthQuake> query(String where, String[] whereArgs) {

        //	Specify	the	result	column	projection.	Return	the	minimum	set
        //	of	columns	required	to	satisfy	your	requirements.
        String[] result_columns = ALL_COLUMNS;

        String groupBy = null;
        String having = null;
        String order = EarthsQuakeProvider.Columns.Key_Terremoto_time + " DESC";

        List<EarthQuake> terremotos = new ArrayList<EarthQuake>();

        ContentResolver cr=mycontext.getContentResolver();

        try {
            Cursor cursor= cr.query(EarthsQuakeProvider.CONTENT_URI, ALL_COLUMNS, where,whereArgs, order  );
            //Cursor cursor = db.query(EarthsQuakeProvider.DBTable_Name, result_columns, where, whereArgs, groupBy, having, order);

            rellenaHashMap(cursor);

            //	Iterate	over	the	cursors	rows.
            while (cursor.moveToNext()) {
                EarthQuake terremoto = rellenaTerremoto(cursor);

                terremotos.add(terremoto);
            }

            cursor.close();

            return terremotos;

        } catch (Exception e) {
            Log.d("EARTHQUAKE", e.getMessage());
            return null;
        }
    }


    private EarthQuake rellenaTerremoto(Cursor cursor) {
        //Devuelve un objeto EarthQuake rellenado con el contenido del cursor
        EarthQuake Terremoto = new EarthQuake();

        Terremoto.set_id(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_id)));
        Terremoto.setPlace(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_Place)));
        Terremoto.setUrl(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_Url)));
        Terremoto.setMagnitud(Double.parseDouble(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_Magnitud))));
        Terremoto.setTime(Long.parseLong(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_time))));

        Coordinate Coordenadas = new Coordinate(0, 0, 0);
        Coordenadas.setDepth(Double.parseDouble(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_Profundidad))));
        Coordenadas.setLng(Double.parseDouble(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_Longitud))));
        Coordenadas.setLat(Double.parseDouble(cursor.getString(indexes.get(EarthsQuakeProvider.Columns.Key_Terremoto_Latitud))));
        Terremoto.setCoords(Coordenadas);

        return Terremoto;
    }

/*

    private static class EarthQuakeOpenHelper extends SQLiteOpenHelper {
        //Al principio se crea la BD
        private static final String DATABASE_NAME = "Terremotos.db"; //getString(R.string.DATABASE_NAME);
        private static final String DATABASE_TABLE = "Terremotos";
        private static final int DATABASE_VERSION = 2;

        private static final String DATABASE_CREATE = "CREATE Table " + DATABASE_TABLE +
                "(" + Key_Terremoto_id + "  TEXT PRIMARY KEY," + Key_Terremoto_Place + " TEXT, " + Key_Terremoto_Magnitud + " REAL, " + Key_Terremoto_Latitud + " REAL , " + Key_Terremoto_Longitud + " REAL, " + Key_Terremoto_Url + " TEXT," + Key_Terremoto_Profundidad + " REAL, " + Key_Terremoto_time + " INTEGER)";


   */
/*     @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE " + DATABASE_TABLE);
            onCreate(db);
        }*//*


      */
/*  private EarthQuakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }*//*


    }

*/

}
