package com.example.julian.gastos;

import android.app.ListActivity;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {
    private GastosOperations gastoDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gastoDBoperation = new GastosOperations(this);

        gastoDBoperation.open();
        List<String> values = gastoDBoperation.getAllGastos();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);


    }

    public void addUser(View view) {

        final ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

        final EditText text = (EditText) findViewById(R.id.editText1);
        final EditText text2 = (EditText) findViewById(R.id.editText2);

        Gasto stud = gastoDBoperation.addGasto(text.getText().toString(), text2.getText().toString());

        adapter.add( stud.getDesc() + " - S/." + stud.getMonto());


    }

    public void deleteFirstUser(View view) {

        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        String stud = "";

        if (getListAdapter().getCount() > 0) {
            stud = (String) getListAdapter().getItem(0);
            gastoDBoperation.deleteStudent(stud);
            adapter.remove(stud);
        }

    }

    public void truncateTable(View view) {
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        gastoDBoperation.truncateTable();
        adapter.clear();
    }

    @Override
    protected void onResume() {
        gastoDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gastoDBoperation.close();
        super.onPause();
    }

}
