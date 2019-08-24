package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static Spinner s1,s2,s3;
    ListView alv;
    Button b;
    Activity thisActivity = this;
    ArrayAdapter<String> sem,naam;
    ArrayList<String> names, dates, admno;
    ArrayList<Integer> isPresent;
    listAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_info);

        alv = findViewById(R.id.attendance_info_listView);
        names = new ArrayList<>();
        admno = new ArrayList<>();
        dates = new ArrayList<>();
        isPresent = new ArrayList<>();

        s1 = findViewById(R.id.attendance_info_sem_spinner);
        sem = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,MainActivity.sem);
        assert s1 != null;
        s1.setAdapter(sem);
        s1.setOnItemSelectedListener(this);

        s2 = findViewById(R.id.attendance_info_subject);
        s3 = findViewById(R.id.attendance_info_name);

        b = findViewById(R.id.attendance_info_find_button);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                loadNames();
            }
        });

        Button generate = findViewById(R.id.generateINFO);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s1.getSelectedItem().toString()=="Select The Semester..." ||
                        s2.getSelectedItem().toString()=="Select Subject..." || s3.getSelectedItem().toString()==null)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(thisActivity);
                    alert.setTitle("Invalid");
                    alert.setMessage("Insufficient Data");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                    return;
                }
                dates.clear();
                isPresent.clear();
                String a = "SELECT datex,isPresent,subject,admno FROM ATTENDANCE WHERE name = '"+s3.getSelectedItem().toString()+"' " +
                        "AND sem ='"+s1.getSelectedItem().toString()+"' AND subject = '"+s2.getSelectedItem().toString()+"';";
                Cursor cursor = MainActivity.handler.execQuery(a);
                if(cursor==null||cursor.getCount()==0)
                {
                    Toast.makeText(thisActivity, "Error : No Data Yet", Toast.LENGTH_SHORT).show();
                }else
                {
                    int ctr = 0;
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast())
                    {
                        dates.add(cursor.getString(0) );
                        isPresent.add(cursor.getInt(1));  //line 79....3 columns are selected , second one is fucking present_list
                        //Toast.makeText(thisActivity, "Present Value - "+getBoolean(cursor,1), Toast.LENGTH_SHORT).show();
                        admno.add(cursor.getString(3));
                        cursor.moveToNext();
                        ctr++;
                    }
                    if(ctr==0)
                    {
                        Toast.makeText(getBaseContext(),"No Students Found",Toast.LENGTH_LONG).show();
                    }
                }
                adapter = new listAdapter2(thisActivity,dates,isPresent,admno,s3.getSelectedItem().toString());
                alv.setAdapter(adapter);

            }
        });

        Button save_exit = findViewById(R.id.save_exit);
        save_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(thisActivity, "Task Done", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void loadNames()
    {
        if(s1.getSelectedItem().toString()=="Select The Semester..." || s2.getSelectedItem().toString()=="Select Subject...")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }
        names.clear();

        String qu = "SELECT * FROM STUDENT WHERE sem = '" + s1.getSelectedItem().toString() + "' " +
                "ORDER BY roll;";
        Cursor cursor = MainActivity.handler.execQuery(qu);
        if(cursor==null||cursor.getCount()==0)
        {
            Toast.makeText(this, "Error : No Data Yet", Toast.LENGTH_SHORT).show();
        }else
        {
            int ctr = 0;
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                names.add(cursor.getString(0) );
                cursor.moveToNext();
                ctr++;
            }
            if(ctr==0)
            {
                Toast.makeText(getBaseContext(),"No Students Found",Toast.LENGTH_LONG).show();
            }
        }
        ArrayAdapter<String> naam=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,names);
        naam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(naam);
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

    }

    public boolean getBoolean(Cursor cursor, int columnIndex) {
        if (cursor.isNull(columnIndex) || cursor.getShort(columnIndex) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
