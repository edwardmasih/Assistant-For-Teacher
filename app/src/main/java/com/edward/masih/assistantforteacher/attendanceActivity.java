package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class attendanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView listView;
    listAdapter adapter;
    ArrayAdapter<String> adapterSpinner;
    //ArrayAdapter<String> adapterSubject;
    ArrayList<String> names;
    ArrayList<String> admno;
    ArrayList<Integer> rollNo;
    //ArrayList<String> subject;
    Activity thisActivity = this;
    public static Spinner spinner;
    public static Spinner attendanceSubject;
    public static String timeDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        timeDate = getDateTime();
       //Log.d("Attendance", time + " -- " + period);

        listView = findViewById(R.id.attendanceListView);

        names = new ArrayList<>();
        admno = new ArrayList<>();
        rollNo = new ArrayList<>();
        //subject = new ArrayList<>();

        //for sem
        spinner = findViewById(R.id.attendanceSpinner);
        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,MainActivity.sem);
        assert spinner != null;
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(this);

        Button btn = findViewById(R.id.loadButton);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadListView(v);
            }
        });



        attendanceSubject=findViewById(R.id.attendanceSubject);



        Button btnx = findViewById(R.id.buttonSaveAttendance);
        assert btnx != null;
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"Saving",Toast.LENGTH_LONG).show();
                if(spinner.getSelectedItem().toString()=="Select The Semester..." || attendanceSubject.getSelectedItem().toString()=="Select Subject...")
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(thisActivity);
                    alert.setTitle("Invalid");
                    alert.setMessage("Insufficient Data");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                    return;
                }
                adapter.saveAll();
            }
        });



    }

    public void loadListView(View view) {
        if(spinner.getSelectedItem().toString()=="Select The Semester..." || attendanceSubject.getSelectedItem().toString()=="Select Subject...")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }
        names.clear();
        admno.clear();
        rollNo.clear();
        //subject.clear();
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();        //only for testing subject spinner
        String qu = "SELECT * FROM STUDENT WHERE sem = '" + spinner.getSelectedItem().toString() + "' " +
                "ORDER BY roll;";
        Cursor cursor = MainActivity.handler.execQuery(qu);
        if(cursor==null||cursor.getCount()==0)
        {
            Toast.makeText(thisActivity, "Error : No Data Yet", Toast.LENGTH_SHORT).show();
        }else
        {
            int ctr = 0;
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                names.add(cursor.getString(0) );
                rollNo.add(cursor.getInt(4));
                admno.add(cursor.getString(2));
                //subject.add(cursor.getString());
                cursor.moveToNext();
                ctr++;
            }
            if(ctr==0)
            {
                Toast.makeText(getBaseContext(),"No Students Found",Toast.LENGTH_LONG).show();
            }
            Log.d("Attendance", "Got " + ctr + " students");
        }

        adapter = new listAdapter(thisActivity,names,admno,rollNo);
        listView.setAdapter(adapter);
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(spinner.getSelectedItem().toString().equals("3rd Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MainActivity.sem3);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
        if(spinner.getSelectedItem().toString().equals("4th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem4);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
        if(spinner.getSelectedItem().toString().equals("5th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem5);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
        if(spinner.getSelectedItem().toString().equals("6th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem6);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
        if(spinner.getSelectedItem().toString().equals("7th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MainActivity.sem7);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
        if(spinner.getSelectedItem().toString().equals("8th Sem")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,MainActivity.sem8);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
        if(spinner.getSelectedItem().toString().equals("Select The Semester...")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            attendanceSubject.setAdapter(dataAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
