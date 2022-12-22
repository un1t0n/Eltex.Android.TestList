/**
 * Основной класс
 * @author Vadim Chernyavsky
 * @version v1.0
 */
package ru.eltex.testlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_PHONE_STATE
                },
                42
        );

//        ContentResolver contentResolver = getContentResolver();
//        Cursor cursorContacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//        if (cursorContacts != null)
//        {
//            cursorContacts.moveToFirst();
//            while (!cursorContacts.isAfterLast()) {
//                int index = cursorContacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//                if (index >= 0) {
//                    String name = cursorContacts.getString(index);
//                    System.out.println(name);
//                }
//                cursorContacts.moveToNext();
//            }
//        }

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

                Uri rawContactUri = this.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
                System.out.println(rawContactUri);
                long id = ContentUris.parseId(rawContactUri);

                ContentValues value = new ContentValues();
                value.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, id);
                value.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                value.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, cursor.getString(1));
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, value);

                value.clear();
                value.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, id);
                value.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                value.put(ContactsContract.CommonDataKinds.Phone.NUMBER, cursor.getString(2));
                value.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MAIN);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, value);
            } else {
                users.add(new Manager(cursor.getString(1), cursor.getString(2)));
                Uri rawContactUri = this.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
                System.out.println(rawContactUri);
                long id = ContentUris.parseId(rawContactUri);

                ContentValues value = new ContentValues();
                value.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, id);
                value.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                value.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, cursor.getString(1));
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, value);

                value.clear();
                value.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, id);
                value.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                value.put(ContactsContract.CommonDataKinds.Phone.NUMBER, cursor.getString(2));
                value.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MAIN);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, value);
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