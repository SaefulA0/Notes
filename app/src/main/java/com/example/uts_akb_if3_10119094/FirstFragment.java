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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements AdapterView.OnItemClickListener{

//    ListView listView;
    DBHelper helper;
//    ListView notelist;
    ArrayList<String> ListItem;
    ArrayAdapter adapter;
    long id;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_first, container, false);

//        DBHelper db = new DBHelper(fragment.getContext());
//        Cursor notes = db.oneData(id);
//        RecyclerView recyclerView = fragment.findViewById(R.id.listNotes);
//        recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
//        CustomCursorAdapter adapter = new CustomCursorAdapter(fragment.getContext(),notes);
//
//        recyclerView.setAdapter(adapter);

        FloatingActionButton Addnote = fragment.findViewById(R.id.floatbtn);
        Addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    toAddnote();
            }
        });
        return fragment;

//        helper = new DBHelper(this);
//        listView = (ListView)findViewById(R.id.listNotes);
//        listView.setOnClickListener(this);
    }

    public void toAddnote(){
        Intent i = new Intent(getActivity(), AddActivity.class);
        startActivity(i);
    }
//    public void setListView(){
//        Cursor cursor = helper.allData();
//        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(getParentFragment().getActivity(), cursor, 1);
//        listView.setAdapter(customCursorAdapter);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ListView listView = (ListView)view.findViewById(R.id.listNotes);
//        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),R.layout.ctnnotes);
//
//        listView.setAdapter(adapter);
//        listView.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView)view.findViewById(androidx.appcompat.R.id.list_item);
        final long id = Long.parseLong(getId.getText().toString());
        Cursor cur = helper.oneData(id);
        cur.moveToFirst();

//        Intent idnotes = new Intent(FirstFragment.this, EditActivity.class);
//        idnotes.putExtra(DBHelper.row_id, id);
//        startActivity(idnotes);
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//        setListView();
//    }

}