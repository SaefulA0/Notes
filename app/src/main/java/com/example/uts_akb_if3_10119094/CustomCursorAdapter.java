
//10119094 IF-3 Saeful Anwar Oktariansah

package com.example.uts_akb_if3_10119094;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroupiew) {
        View v = layoutInflater.inflate(R.layout.ctnnotes, viewGroupiew, false);
        MyHolder holder = new MyHolder();
        holder.ListID = (TextView)v.findViewById(R.id.ListID);
        holder.ListTitle = (TextView)v.findViewById(R.id.ListTitle);
        holder.ListDescription = (TextView)v.findViewById(R.id.ListDescription);
        holder.ListCreate = (TextView)v.findViewById(R.id.ListCreate);
        v.setTag(holder);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();

        holder.ListID.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.row_id)));
        holder.ListTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.row_title)));
        holder.ListDescription.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.row_note)));
        holder.ListCreate.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.row_created)));
    }
    class MyHolder{
        TextView ListID;
        TextView ListTitle;
        TextView ListDescription;
        TextView ListCreate;
    }
}
