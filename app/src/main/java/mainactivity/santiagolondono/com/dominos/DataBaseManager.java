package mainactivity.santiagolondono.com.dominos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by asus-pc on 26/05/2015.
 */
public class DataBaseManager {

    public static final String TABLE_NAME ="sedes";
    public static final String CN_ID ="_id";
    public static final String CN_NAME ="nombre";
    public static final String CN_LATITUD ="latitud";
    public static final String CN_LONGITUD ="longitud";

    public static final String CREATE_TABLE = " create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_LATITUD + " text not null,"
            + CN_LONGITUD + " text);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        DbHelper helper = new DbHelper(context);
        //  SQLiteDatabase db = helper.getWritableDatabase();
        db = helper.getWritableDatabase();
    }

    public  ContentValues generarContentValues(String nombre, String latitud, String longitud){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME, nombre);
        valores.put(CN_LATITUD, latitud);
        valores.put(CN_LONGITUD, longitud);

        return valores;

    }

    public void ingresar(String nombre, String latitud, String longitud){


        db.insert(TABLE_NAME, null, generarContentValues(nombre,latitud,longitud));
       // Toast.makeText(this, "Sede: " + nombre + latitud + longitud + " agregada", Toast.LENGTH_LONG).show();

    }


    public void eliminar_id(String _id){


        db.delete(TABLE_NAME,CN_ID+"=?", new String[] {_id});
    }

    public void eliminar_nombre(String nombre){


        db.delete(TABLE_NAME,CN_NAME+"=?", new String[] {nombre});
    }


    public void actualizar(String nombre, String nuevoLatitud, String nuevoLongitud){

        db.update(TABLE_NAME,generarContentValues(nombre,nuevoLatitud,nuevoLongitud),CN_NAME + "=?", new String[] {nombre});

    }

    public Cursor cargarCursor(){
        //query(Table,columns,String selection, String selection args,String Group BY,String Having,String OrderBy)
        String [] columnas= new String[] {CN_ID, CN_NAME, CN_LATITUD , CN_LONGITUD};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public Cursor buscarSede(String nombre){

        String[] columnas = new String[] {CN_ID,CN_NAME,CN_LATITUD, CN_LONGITUD};
        return db.query(TABLE_NAME,columnas,CN_NAME + "=?", new String[] {nombre},null,null,null);
    }


}
