package com.example.guest.messageme;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Guest on 7/14/16.
 */
public class SpinnerAdapter extends ArrayAdapter<Users>{
    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private Users[] usersList;

    public SpinnerAdapter(Context context, int textViewResourceId, Users[] usersList) {
        super(context, textViewResourceId, usersList);
        this.context = context;
        this.usersList = usersList;
    }

    public int getCount(){
        return usersList.length;
    }

    public Users getItem(int position){
        return usersList[position];
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(usersList[position].getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(usersList[position].getName());

        return label;
    }
}

//TODO LOOK THIS OVER
//http://stackoverflow.com/questions/1625249/android-how-to-bind-spinner-to-custom-object-list