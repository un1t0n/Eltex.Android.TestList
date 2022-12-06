package ru.eltex.testlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        SharedPreferences preferences = getSharedPreferences("INFO",MODE_PRIVATE);
        Toast.makeText(this, preferences.getString("APP_STATUS", "STOP"), Toast.LENGTH_LONG).show();

        ((Button)findViewById(R.id.addButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String developerProfession = "Developer";
                String managerProfession = "Manager";
                RadioButton developerButton = (RadioButton)findViewById(R.id.rbtn_developer);
                RadioButton managerButton = (RadioButton)findViewById(R.id.rbtn_manager);
                if (developerButton.isChecked() || managerButton.isChecked()) {
                    if (developerButton.isChecked()) {
                        String name = ((EditText) findViewById(R.id.name)).getText().toString().trim();
                        String phone = ((EditText) findViewById(R.id.phone)).getText().toString().trim();
                        System.out.println(name + " " + phone + " developer");
                        SQLiteDatabase database = new DBHelper(getApplicationContext()).getWritableDatabase();
                        database.execSQL("INSERT INTO users(name, phone, profession) VALUES ('" + name + "', '" + phone + "', '" + developerProfession + "');");
                        database.close();
                        Developer developer = new Developer(name, phone);
                        MainActivity.users.add(developer);
                    }
                    if (managerButton.isChecked()) {
                        String name = ((EditText) findViewById(R.id.name)).getText().toString().trim();
                        String phone = ((EditText) findViewById(R.id.phone)).getText().toString().trim();
                        System.out.println(name + " " + phone + " Manager");
                        SQLiteDatabase database = new DBHelper(getApplicationContext()).getWritableDatabase();
                        database.execSQL("INSERT INTO users(name, phone, profession) VALUES ('" + name + "', '" + phone + "', '" + managerProfession + "');");
                        database.close();
                        Manager manager = new Manager(name, phone);
                        MainActivity.users.add(manager);
                    }
                    Intent toMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(toMain);
                } else {
                    Toast.makeText(getApplicationContext(), "Not selected of profession", Toast.LENGTH_SHORT).show();
                }
                for(User user : MainActivity.users) {
                    System.out.println(user.toString());
                }
            }
        });
    }
}