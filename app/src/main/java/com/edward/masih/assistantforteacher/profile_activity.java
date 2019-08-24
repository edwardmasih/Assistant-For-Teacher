package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
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
import java.util.StringTokenizer;
import java.util.ArrayList;

public class profile_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        ArrayList<String> names_Roll,names,admno,contact,sem;
        ArrayList<Integer> rolls;
        ArrayAdapter adp, sem_adapter;
        Activity profile_activity = this;
        listAdapter adapter;

        Activity activity = this;
        Spinner s1;
        Button button;
        ListView studLV;

        public static String sem_naam, naam;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        s1=findViewById(R.id.profile_spinner);
        //fab=findViewById(R.id.profile_fab);
        button=findViewById(R.id.profile_find_button);
        names_Roll = new ArrayList<>();
        names = new ArrayList<>();
        rolls = new ArrayList<>();
        admno = new ArrayList<>();
        contact = new ArrayList<>();
        sem = new ArrayList<>();

        s1 = findViewById(R.id.profile_spinner);
        sem_adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,MainActivity.sem);
        s1.setAdapter(sem_adapter);
        assert s1 != null;
        s1.setOnItemSelectedListener(this);

        studLV=findViewById(R.id.profile_student_listView);
        assert studLV != null;
        studLV.setOnItemClickListener(this);
        studLV.setOnItemLongClickListener(this);

        Button findButton = findViewById(R.id.profile_find_button);
        assert findButton != null;
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNames();
            }
        });

    }

    private void loadNames() {
        if(s1.getSelectedItem().toString()=="Select The Semester...")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        names_Roll.clear();
        names.clear();
        rolls.clear();
        admno.clear();
        contact.clear();
        sem.clear();
        String qu = "SELECT * FROM STUDENT WHERE sem = '" + s1.getSelectedItem().toString() + "' " +
                "ORDER BY ROLL";
        Cursor cursor = MainActivity.handler.execQuery(qu);
        if(cursor==null||cursor.getCount()==0)
        {
            Toast.makeText(activity, "Error : No Data Yet", Toast.LENGTH_SHORT).show();
        }else
        {
            int ctr = 0;
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                names_Roll.add("Roll No. "+ cursor.getInt(4)+" - "+cursor.getString(0) );
                names.add(cursor.getString(0));
                rolls.add(cursor.getInt(4));
                admno.add(cursor.getString(2));
                contact.add(cursor.getString(3));
                sem.add(cursor.getString(1));
                cursor.moveToNext();
                ctr++;
            }
            if(ctr==0)
            {
                Toast.makeText(getBaseContext(),"No Students Found",Toast.LENGTH_LONG).show();
            }
            Log.d("Attendance", "Got " + ctr + " students");
        }

        adp = new ArrayAdapter(profile_activity,android.R.layout.simple_list_item_1,names);
        studLV.setAdapter(adp);
    }

    public void refreshProfile(MenuItem item) {
        loadNames();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(names_Roll.get(position).toString());
        alert.setMessage("Sem - "+sem.get(position).toString()+"\nAdmission No. - "+admno.get(position).toString()+"\n" +
                "Contact - "+contact.get(position).toString());
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Delete Student");
        alert.setMessage("Are you sure ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String qu = "DELETE FROM STUDENT WHERE name = '" +names.get(i).toString()+ "' AND sem='"+s1.getSelectedItem().toString()+"';";
                if (MainActivity.handler.execAction(qu)) {
                    Log.d("delete", "done from student");
                    String qa = "DELETE FROM ATTENDANCE WHERE name = '" +names.get(i).toString()+ "' AND sem='"+s1.getSelectedItem().toString()+"';";
                    if (MainActivity.handler.execAction(qa)) {
                        loadNames();
                        Toast.makeText(activity, "Deleted", Toast.LENGTH_LONG).show();
                        Log.d("delete", "done from attendance");
                    }
                    else{
                        Toast.makeText(activity, "Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(activity, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
        return  true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
