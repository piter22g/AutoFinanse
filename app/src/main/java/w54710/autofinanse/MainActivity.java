package w54710.autofinanse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Piotrek on 2016-07-22.
 */
public class MainActivity extends AppCompatActivity {

    private DbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.podsumowanie);

        mHelper = new DbHelper(this);

        SQLiteDatabase db = mHelper.getReadableDatabase();
        String query = "SELECT SUM("+ Contract.ContractEntry.COL_PRICE +") AS sum FROM " + Contract.ContractEntry.TABLE;
        Cursor cur = db.rawQuery(query, null);

        Integer sum = 0;

        if(cur.moveToFirst()){
            int index = cur.getColumnIndex("sum");
            sum = cur.getInt(index);
        }

        TextView textViewToChange = (TextView) findViewById(R.id.podsumowanie_wydatkow);
        textViewToChange.setText(sum.toString() + "zł");

        cur.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.lista:
                Intent lista_open = new Intent(this, ListaActivity.class);
                startActivity(lista_open);
                return true;

            case R.id.dodaj:
                Intent dodaj_open = new Intent(this, DodajActivity.class);
                startActivity(dodaj_open);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addWydatek(View view)
    {
        Intent dodaj_open = new Intent(this, DodajActivity.class);
        startActivity(dodaj_open);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }
}
