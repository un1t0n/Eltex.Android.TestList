/**
 * Основной класс
 * @author Vadim Chernyavsky
 * @version v1.0
 */
package ru.eltex.testlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * The class Main activity.
 */
public class MainActivity extends AppCompatActivity {
    static String DEVELOPER_PROFESSION = "Developer";
    static List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainList = (ListView)findViewById(R.id.main_list);

        users = new LinkedList<>();

        SQLiteDatabase database = new DBHelper(this).getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            System.out.println("User: " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
            if (cursor.getString(3).equals(DEVELOPER_PROFESSION)) {
                users.add(new Developer(cursor.getString(1), cursor.getString(2)));
            } else {
                users.add(new Manager(cursor.getString(1), cursor.getString(2)));
            }
            cursor.moveToNext();
        }
        cursor.close();

        //users.add(new Manager("Ivan", "700"));
        //users.add(new Manager("Mary", "800"));
        //users.add(new Developer("Dmitriy", "900"));

        //Create adapter
        PhoneAdapter phoneAdapter = new PhoneAdapter(this, users);
        //Set adapter
        mainList.setAdapter(phoneAdapter);

        Developer dev1 = new Developer("Dmitry", "900");
        System.out.println(dev1.toString());

        ((Button)findViewById(R.id.addButton)).setOnClickListener(view -> {
            Intent toAdd = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(toAdd);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("INFO",MODE_PRIVATE);
        Toast.makeText(this, preferences.getString("APP_STATUS", "STOP"), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}