/**
 * Адаптер списка
 * @author Vadim Chernyavsky
 * @version v1.0
 */

package ru.eltex.testlist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * The class phone adapter.
 */
public class PhoneAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> users;
    /**
     * The string of log tag.
     */
    String LOG_TAG = "TEST_DEBUG";

    /**
     * Instantiates a new Phone adapter.
     *
     * @param context the context
     * @param users   the users
     */
    public PhoneAdapter(Context context, List<User> users) {
        super(context, R.layout.item, users);
        this.context = context;
        this.users = users;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater - Сервис отдачи Layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Запрос к XML item'а из группы, непривязанно к корню
        View view = inflater.inflate(R.layout.item, parent, false);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(this.users.get(position).getName());

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Test name", Toast.LENGTH_SHORT).show();
                Intent newContact = new Intent(Intent.ACTION_INSERT);
                newContact.setType(ContactsContract.Contacts.CONTENT_TYPE);
                newContact.putExtra(ContactsContract.Intents.Insert.NAME, users.get(position).getName());
                newContact.putExtra(ContactsContract.Intents.Insert.PHONE, users.get(position).getPhone());
                context.startActivity(newContact);
            }
        });

        TextView phone = (TextView) view.findViewById(R.id.phone);
        phone.setText(this.users.get(position).getPhone());

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + users.get(position).getPhone()));
                context.startActivity(call);

            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.avatar);
        if (this.users.get(position) instanceof Developer){ //Можно через GetClass, но через методы
            imageView.setImageResource(R.drawable.img);
            Log.d(LOG_TAG + "_IMAGE1", "Drawable image developer");
        } else {
            imageView.setImageResource(R.drawable.img2);
            Log.d(LOG_TAG + "_IMAGE2", "Drawable image manager");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG + "_ON_CLICK", "itemClick: position = " + position);
                Intent toInfo = null;
                if (users.get(position) instanceof Developer)
                    toInfo = new Intent(context, DevActivity.class);
                if (users.get(position) instanceof Manager)
                    toInfo = new Intent(context, ManagerActivity.class);
                toInfo.putExtra("name", users.get(position).getName());
                toInfo.putExtra("phone", users.get(position).getPhone());
                context.startActivity(toInfo);
                //Показ имени
                //Toast.makeText(context, users[position].getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
