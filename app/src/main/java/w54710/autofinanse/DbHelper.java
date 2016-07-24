package w54710.autofinanse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Piotrek on 2016-07-22.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Contract.DB_NAME, null, Contract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                Contract.ContractEntry.TABLE + " ( " +
                Contract.ContractEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.ContractEntry.COL_TITLE + " TEXT NOT NULL, " +
                Contract.ContractEntry.COL_PRICE + " INTEGER NOT NULL, " +
                Contract.ContractEntry.COL_CONTENT + " TEXT NOT NULL);";

/*        String createTable = "CREATE TABLE " + Contract.ContractEntry.TABLE + " ( " +
                Contract.ContractEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.ContractEntry.COL_TITLE + " TEXT NOT NULL);";*/

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.ContractEntry.TABLE);
        onCreate(db);
    }
}