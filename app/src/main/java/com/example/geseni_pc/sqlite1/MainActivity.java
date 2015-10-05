package com.example.geseni_pc.sqlite1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import Sqlite.Sqlite;

public class MainActivity extends AppCompatActivity {
    Sqlite cx;
    EditText edt1, edt2;
    ListView lista;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cx = new Sqlite(this, "bdusuario", null, 1);
        edt1 = (EditText) findViewById(R.id.editText1);
        edt2 = (EditText) findViewById(R.id.editText2);
        lista = (ListView) findViewById(R.id.listView);
        listar_usuario();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insert_ud(View V) {
        if (cx.getWritableDatabase() != null) {
            cx.getWritableDatabase().execSQL("INSERT INTO usuario(campo1, campo2)" + "values (' " + edt1.getText().toString() + "','" + edt2.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Insertado", Toast.LENGTH_SHORT).show();
            listar_usuario();
            limpiar();
        }
    }


    public void listar_usuario() {

        Cursor cursor = cx.getReadableDatabase().rawQuery("SELECT campo1||''||campo2  FROM usuario", null);
        final String[] data = new String[cursor.getCount()];
        int n = 0;
        if (cursor.moveToFirst()) {
            do {
                data[n] = cursor.getString(0).toString();
                n++;
            }
            while (cursor.moveToNext());
        }

        lista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, data));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Eliminar(data[position]);

                Toast.makeText(getApplicationContext(), "se elimino", Toast.LENGTH_LONG).show();

            }
        });
    }


    public void Eliminar(String campo) {
        cx.getWritableDatabase().delete("usuario","campo1='"+campo+"'",null);
        cx.close();
        listar_usuario();

    }
    public void limpiar() {
        edt1.setText(" ");
        edt2.setText("");
    }
}

