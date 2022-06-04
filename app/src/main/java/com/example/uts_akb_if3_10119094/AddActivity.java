package com.example.uts_akb_if3_10119094;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class AddActivity extends AppCompatActivity {

    DBHelper helper;
    EditText NotesTitle,NotesDesc;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        NotesTitle = (EditText)findViewById(R.id.NotesTitleAdd);
        NotesDesc = (EditText)findViewById(R.id.NotesDescAdd);

        Button addbtn = findViewById(R.id.SaveAdd);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = NotesTitle.getText().toString().trim();
                String desc = NotesDesc.getText().toString().trim();

                //get calender
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
                String created = simpleDateFormat.format(calendar.getTime());

                ContentValues values = new ContentValues();
                values.put(DBHelper.row_title, title);
                values.put(DBHelper.row_note,desc);
                values.put(DBHelper.row_created, created);

                //Create Condition
                if(title.equals("") && desc.equals("")){
                    Toast.makeText(AddActivity.this, "Nothing to save", Toast.LENGTH_LONG).show();
                }else {
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}