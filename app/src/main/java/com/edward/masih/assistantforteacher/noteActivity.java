package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class noteActivity extends AppCompatActivity implements ListView.OnItemClickListener,ListView.OnItemLongClickListener, AdapterView.OnItemSelectedListener{

    ListView listView;
    ArrayAdapter adapter, spinner_adp;
    ArrayList titles;
    ArrayList contents;
    Activity activity =this;
    Spinner s1,s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titles = new ArrayList();
        contents = new ArrayList();
        s1 = findViewById(R.id.spinnerSemester);
        spinner_adp=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,MainActivity.sem);
        s1.setAdapter(spinner_adp);
        assert s1 != null;
        s1.setOnItemSelectedListener(this);

        s2 = findViewById(R.id.spinnerSubject);

        Button btn = (Button) findViewById(R.id.loadButton);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadNotes();
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab_Note);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = new Intent(activity,note_create.class);
                startActivity(launchIntent);
            }
        });

        listView = findViewById(R.id.noteList);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void loadNotes() {
        if(s1.getSelectedItem().toString()=="Select The Semester..." || s2.getSelectedItem().toString()=="Select Subject...")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        titles.clear();
        contents.clear();
        String qu = "SELECT * FROM NOTES WHERE sem='"+s1.getSelectedItem().toString()+"' AND sub='"+s2.getSelectedItem().toString()+"' ORDER BY datex DESC;";
        Cursor cursor = MainActivity.handler.execQuery(qu);
        if(cursor==null||cursor.getCount()==0)
        {
            Toast.makeText(getBaseContext(),"No Notes Found",Toast.LENGTH_LONG).show();
        }
        else
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                titles.add(cursor.getString(0));
                contents.add(cursor.getString(1));
                cursor.moveToNext();
            }
            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titles.get(position).toString());
        alert.setMessage(contents.get(position).toString());
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        final String title = titles.get(position).toString();
        final String body = contents.get(position).toString();
        alert.setTitle("Delete ?");
        alert.setMessage("Do you want to delete this note ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String qu = "DELETE FROM NOTES WHERE title = '" + title + "' AND body = '" + body + "';";
                if (MainActivity.handler.execAction(qu)) {
                    loadNotes();
                    Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG).show();
                } else {
                    loadNotes();
                    Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("No",null);
        alert.show();
        return true;
    }


    public void refreshNote(MenuItem item) {
        loadNotes();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(s1.getSelectedItem().toString().equals("3rd Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MainActivity.sem3);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(s1.getSelectedItem().toString().equals("4th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem4);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(s1.getSelectedItem().toString().equals("5th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem5);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(s1.getSelectedItem().toString().equals("6th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem6);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(s1.getSelectedItem().toString().equals("7th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MainActivity.sem7);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(s1.getSelectedItem().toString().equals("8th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem8);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(s1.getSelectedItem().toString().equals("Select The Semester...")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Nothing Selected", Toast.LENGTH_SHORT).show();
    }


}
