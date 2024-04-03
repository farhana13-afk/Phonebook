package com.example.phonebook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    Context mainContext;
    int adapterXML;
    List<Contact> items;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        mainContext = context;
        adapterXML = resource;
        items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(adapterXML, null);

        TextView name = adapterView.findViewById(R.id.id_name);
        TextView description = adapterView.findViewById(R.id.id_description);
        ImageView image = adapterView.findViewById(R.id.charcter_image);

        image.setImageResource(items.get(position).getImage());
        name.setText(items.get(position).getName());
        description.setText(items.get(position).getDescription());

        return adapterView;

    }
}
