package com.example.julian.gastos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class GastosOperations {

    //Database fields
    private DataBaseWrapper dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapper.GASTOS_ID, DataBaseWrapper.GASTOS_DESC,DataBaseWrapper.GASTOS_MONTO };
    private SQLiteDatabase database;

    public GastosOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Gasto addGasto(String name,String monto) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.GASTOS_DESC, name);
        values.put(DataBaseWrapper.GASTOS_MONTO, monto);

        long gastoId = database.insert(DataBaseWrapper.GASTOS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.GASTOS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.GASTOS_ID + " = "
                        + gastoId, null, null, null, null);

        cursor.moveToFirst();

        Gasto newComment = parseGasto(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(String gasto) {
        long id = Long.parseLong(gasto.split(" ")[0]);
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapper.GASTOS, DataBaseWrapper.GASTOS_ID
                + " = " + id, null);
    }

    public List<String> getAllGastos() {
        List gastos = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.GASTOS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Gasto gasto = parseGasto(cursor);
            gastos.add(gasto.getDesc()+" - S/."+gasto.getMonto());
            cursor.moveToNext();
        }

        cursor.close();
        return gastos;
    }

    public void truncateTable(){
        //database.execSQL(DataBaseWrapper.DATABASE_CREATE);
        database.delete(DataBaseWrapper.GASTOS,null,null);
        database.execSQL("drop table "+DataBaseWrapper.GASTOS);
        database.execSQL(DataBaseWrapper.DATABASE_CREATE);
    }

    private Gasto parseGasto(Cursor cursor) {
        Gasto gasto = new Gasto();
        gasto.setId((cursor.getInt(0)));
        gasto.setDesc(cursor.getString(1));
        gasto.setMonto(cursor.getString(2));
        return gasto;
    }
}
