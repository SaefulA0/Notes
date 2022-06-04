package com.example.uts_akb_if3_10119094;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    DBHelper helper;
    EditText NotesTitle,NotesDesc;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        NotesTitle = (EditText)findViewById(R.id.NotesTitleEdit);
        NotesDesc = (EditText)findViewById(R.id.NotesDescEdit);

        getData();

        Button sveditbtn = findViewById(R.id.SaveEdit);
        sveditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = NotesTitle.getText().toString().trim();
                String desc = NotesDesc.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DBHelper.row_title, title);
                values.put(DBHelper.row_note, desc);

                if(title.equals("") && desc.equals("")){
                    Toast.makeText(EditActivity.this, "Nothing to save Edit", Toast.LENGTH_SHORT).show();
                }else {
                    helper.updateData(values, id);
                    Toast.makeText(EditActivity.this, "Saved", Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });

        Button deleditbtn = findViewById(R.id.DeleteEdit);
        deleditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("This note will delete");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void getData(){
        Cursor cursor = helper.oneData(id);
        if (cursor.moveToFirst()){
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.row_title));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.row_note));

            NotesTitle.setText(title);
            NotesDesc.setText(desc);
        }
    }
}