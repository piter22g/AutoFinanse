package w54710.autofinanse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Piotrek on 2016-07-22.
 */
public class ListaActivity extends AppCompatActivity {

    private DbHelper mHelper;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_wydatkow);
        setTitle(R.string.lista);

        mHelper = new DbHelper(this);
        mListView = (ListView) findViewById(R.id.lista);

        updateList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.back:
                Intent back_to_home = new Intent(this, MainActivity.class);
                startActivity(back_to_home);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sublist, menu);
        return true;
    }

    private void updateList() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(Contract.ContractEntry.TABLE,
                new String[]{Contract.ContractEntry._ID, Contract.ContractEntry.COL_TITLE},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(Contract.ContractEntry.COL_TITLE);
            list.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.element_listy,
                    R.id.tytul_elementu,
                    list);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(list);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }
}
