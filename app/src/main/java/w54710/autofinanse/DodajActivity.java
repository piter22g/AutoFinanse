package w54710.autofinanse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Piotrek on 2016-07-22.
 */
public class DodajActivity extends AppCompatActivity {

    private DbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj);
        setTitle(R.string.dodaj_wydatki);
        findViewById(R.id.sendButton).setOnClickListener(handleClick);

        mHelper = new DbHelper(this);
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

    private View.OnClickListener handleClick = new View.OnClickListener(){
        public void onClick(View arg) {
            TextView title = (TextView)findViewById(R.id.tytul);
            EditText price = (EditText)findViewById(R.id.koszt);
            EditText content = (EditText)findViewById(R.id.opis);

            if(title.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), R.string.tytul_wymagany, Toast.LENGTH_LONG).show();
            } else if(price.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), R.string.koszt_wymagany, Toast.LENGTH_LONG).show();
            } else if(content.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), R.string.opis_wymagany, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sukces!", Toast.LENGTH_LONG).show();

                SQLiteDatabase db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(Contract.ContractEntry.COL_TITLE, title.getText().toString());
                values.put(Contract.ContractEntry.COL_PRICE, price.getText().toString());
                values.put(Contract.ContractEntry.COL_CONTENT, content.getText().toString());

                db.insertWithOnConflict(Contract.ContractEntry.TABLE,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE
                );
                db.close();

                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                startActivity(intent);
            }
        }
    };
}
