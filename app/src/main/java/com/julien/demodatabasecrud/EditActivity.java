package com.julien.demodatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        tvID = (TextView) findViewById(R.id.tvID);
        etContent = (EditText) findViewById(R.id.etContent);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");
        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());

        btnUpdate.setOnClickListener(v -> {
            DBHelper dbh = new DBHelper(EditActivity.this);
            data.setNoteContent(etContent.getText().toString());
            dbh.updateNote(data);
            dbh.close();
            this.finish();
        });

        btnDelete.setOnClickListener(v -> {
            DBHelper dbh = new DBHelper(EditActivity.this);
            dbh.deleteNote(data.getId());
            dbh.close();
            this.finish();
        });
    }
}