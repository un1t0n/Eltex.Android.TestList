/**
 * Основной класс
 * @author Vadim Chernyavsky
 * @version v1.0
 */
package ru.eltex.testlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * The class Main activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainList = (ListView)findViewById(R.id.main_list);

        User[] users = {
                new Manager("Ivan", "700"),
                new Manager("Mary", "800"),
                new Developer("Dmitriy", "900")
        };
        //Create adapter
        PhoneAdapter phoneAdapter = new PhoneAdapter(this, users);
        //Set adapter
        mainList.setAdapter(phoneAdapter);
    }
}