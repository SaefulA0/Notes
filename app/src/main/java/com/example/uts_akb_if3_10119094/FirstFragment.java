
//10119094 IF-3 Saeful Anwar Oktariansah

package com.example.uts_akb_if3_10119094;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements AdapterView.OnItemClickListener{

    DBHelper helper;
    View fragment;
    Notes notes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = inflater.inflate(R.layout.fragment_first, container, false);

        DBHelper db = new DBHelper(fragment.getContext());
        List<Notes> notes = db.getNotes();
        RecyclerView recyclerView = fragment.findViewById(R.id.listNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        NoteAdapter adapter = new NoteAdapter(fragment.getContext(),notes);

        recyclerView.setAdapter(adapter);

        FloatingActionButton Addnote = fragment.findViewById(R.id.floatbtn);
        Addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    toAddnote();
            }
        });

        return fragment;

    }

    public void toAddnote(){
        Intent i = new Intent(getActivity(), AddActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = view.findViewById(R.id.listNotes);
        final long id = Long.parseLong(getId.getText().toString());
        Cursor cur = helper.oneData(id);
        cur.moveToFirst();

        Intent intent = new Intent(fragment.getContext(), EditActivity.class);
        intent.putExtra("id", notes.getId());
        Toast.makeText(fragment.getContext(), "ID :"+ id, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}