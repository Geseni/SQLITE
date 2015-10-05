package Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GESENI-PC on 28/09/2015.
 */
public class Sqlite extends SQLiteOpenHelper {
    // llamado al constructor

    String table_usuario="CREATE TABLE usuario(id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, campo1 TEXT, campo2 TEXT)";
    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); //BDusuario es el esqueleto que va a manejar a todas las tablas
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_usuario);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVerion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        this.onCreate(db);


    }
}
