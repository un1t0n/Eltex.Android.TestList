package ru.eltex.testlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "PHONEBOOK", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, profession TEXT);");
        db.execSQL("INSERT INTO users VALUES (1, \"Ivan\", \"900\", \"Manager\"), (2, \"Mary\", \"800\", \"Manager\"), (3, \"Anton\",\"700\", \"Developer\");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users;");
        onCreate(db);
    }
}
