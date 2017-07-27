package com.example.julian.gastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julian on 26/07/2017.
 */

public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String GASTOS = "Gastos";
    public static final String GASTOS_ID = "_id";
    public static final String GASTOS_DESC = "_descripcion";
    public static final String GASTOS_MONTO = "_monto";

    private static final String DATABASE_NAME = "Gastos.db";
    private static final int DATABASE_VERSION = 2;

    // creation SQLite statement
    public static final String DATABASE_CREATE = "create table " + GASTOS
            + "(" + GASTOS_ID + " integer primary key autoincrement, "
            + GASTOS_DESC + " text not null, "
            + GASTOS_MONTO + " text not null);";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + GASTOS);
        onCreate(db);
    }
}
