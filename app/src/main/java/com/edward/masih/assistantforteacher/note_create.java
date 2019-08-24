package com.edward.masih.assistantforteacher;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class note_create extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1,s2;
    ArrayAdapter<String> adp;
    EditText title,body;
    //Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_create);
        s1=findViewById(R.id.pinSpinner);
        s2=findViewById(R.id.subjectNote);
        adp=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,MainActivity.sem);
        s1.setAdapter(adp);
        assert s1 != null;
        s1.setOnItemSelectedListener(this);

        Button btn = (Button)findViewById(R.id.noteSaveButton);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        if(s1.getSelectedItem().toString()=="Select The Semester..." || s2.getSelectedItem().toString()=="Select Subject...")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }
        title = findViewById(R.id.noteTitle);
        body = findViewById(R.id.noteBody);
        s2 =  findViewById(R.id.subjectNote);
        s1 = findViewById(R.id.pinSpinner);

        String qu = " INSERT INTO NOTES(title,body,sem,sub,datex) VALUES('" + title.getText().toString() + "','" + body.getText().toString() +"'," +
                "'" + s1.getSelectedItem().toString() + "','" + s2.getSelectedItem().toString()+ "','"+getDateTime()+"');";
        if(MainActivity.handler.execAction(qu))
        {
            //MainActivity.handler.execAction(qu);
            Toast.makeText(getBaseContext(),"Note Saved",Toast.LENGTH_LONG).show();
            this.finish();
        }
        else   // not working
        {
            Toast.makeText(this, "Error: Note Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //String sp1= String.valueOf(s1.getSelectedItem());
        //Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
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

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Nothing Selected", Toast.LENGTH_SHORT).show();
    }
}