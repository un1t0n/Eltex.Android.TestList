/**
 * Адаптер списка
 * @author Vadim Chernyavsky
 * @version v1.0
 */

package ru.eltex.testlist;

import android.content.Context;
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

/**
 * The class phone adapter.
 */
public class PhoneAdapter extends ArrayAdapter<User> {

    private Context context;
    private Object[] users;
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
    public PhoneAdapter(Context context, User[] users) {
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
        name.setText(((User)this.users[position]).getName());

        TextView phone = (TextView) view.findViewById(R.id.phone);
        phone.setText(((User)this.users[position]).getPhone());

        ImageView imageView = (ImageView) view.findViewById(R.id.avatar);
        if (this.users[position] instanceof Developer){ //Можно через GetClass, но через методы
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
                //Показ имени
                Toast.makeText(context, ((User)users[position]).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
