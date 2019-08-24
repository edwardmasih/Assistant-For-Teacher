package com.edward.masih.assistantforteacher;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static databaseHandler handler;
    public static Activity activity;
    public static ArrayList<String> sem = new ArrayList<>();
    public static ArrayList<String> sem3 = new ArrayList<>();
    public static ArrayList<String> sem4 = new ArrayList<>();
    public static ArrayList<String> sem5 = new ArrayList<>();
    public static ArrayList<String> sem6 = new ArrayList<>();
    public static ArrayList<String> sem7 = new ArrayList<>();
    public static ArrayList<String> sem8 = new ArrayList<>();


    ArrayList<String> basicFields;
    gridAdapter adapter;
    GridView gridView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basicFields = new ArrayList<>();
        handler = new databaseHandler(this);
        activity = this;


        android.support.v7.app.ActionBar ab = getSupportActionBar();
        //next line for changing the action bar color
        // ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        ab.show();



        sem.clear();
        sem.add("Select The Semester...");
        sem.add("3rd Sem");
        sem.add("4th Sem");
        sem.add("5th Sem");
        sem.add("6th Sem");
        sem.add("7th Sem");
        sem.add("8th Sem");

        sem3.clear();
        sem3.add("Select Subject...");
        sem3.add("Basic Electronics");
        sem3.add("CCWT");
        sem3.add("Computational Science and Fuzzy Logic");
        sem3.add("C Programming");
        sem3.add("DELD");
        sem3.add("M3");


        sem4.clear();
        sem4.add("Select Subject...");
        sem4.add("C++");
        sem4.add("CSA");
        sem4.add("Computational Mathematics");
        sem4.add("Data Structure");
        sem4.add("Discrete Mathematics");
        sem4.add("Operating System");

        sem5.clear();
        sem5.add("Select Subject...");
        sem5.add("ADA");
        sem5.add("DBMS");
        sem5.add("JAVA");
        sem5.add("MPI");
        sem5.add("TOP");
        sem5.add("UNIX");

        sem6.clear();
        sem6.add("Select Subject...");
        sem6.add("Compiler Design");
        sem6.add("Computer Graphics");
        sem6.add("Computer Network");
        sem6.add("ERP");
        sem6.add("SEPM");
        sem6.add("TCP/IP");

        sem7.clear();
        sem7.add("Select Subject...");
        sem7.add("Cloud Computing");
        sem7.add("Cryptography");
        sem7.add("Mobile Computing and Application");
        sem7.add("Network Programming");
        sem7.add("Parallel Processors and Computing");

        sem8.clear();
        sem8.add("Select Subject...");
        sem8.add("Artificial Intelligence and Expert System");
        sem8.add("Data Mining and Warehousing");
        sem8.add("Cyber Security");
        sem8.add("Decision Support System");
        sem8.add("Internet and Multimedia");


        gridView = findViewById(R.id.grid);
        basicFields.add("ATTENDANCE");
        basicFields.add("NOTES");
        basicFields.add("STUDENT PROFILE");
        basicFields.add("REGISTER & EDIT");
        basicFields.add("ATTENDANCE INFO");
        basicFields.add("SEND SMS");
        //basicFields.add("SCHEDULER");
        //basicFields.add("CGPA CALCULATOR");


        adapter = new gridAdapter(this, basicFields);
        gridView.setAdapter(adapter);

    }

    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this, SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this, About.class);
        startActivity(launchIntent);
    }
    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

    /*
    public void fillSubject_Sem3() {
        ArrayList<String> sub = new ArrayList<>();
        sub.clear();
        sub.add("Select Subject...");
        sub.add("Basic Electronics");
        sub.add("CCWT");
        sub.add("Computational Science and Fuzzy Logic");
        sub.add("C Programming");
        sub.add("DELD");
        sub.add("M3");

    }

    public void fillSubject_Sem4() {
        sub.clear();
        sub.add("Select Subject...");
        sub.add("C++");
        sub.add("CSA");
        sub.add("Computational Mathematics");
        sub.add("Data Structure");
        sub.add("Discrete Mathematics");
        sub.add("Operating System");
    }

    public void fillSubject_Sem5() {
        sub.clear();
        sub.add("Select Subject...");
        sub.add("ADA");
        sub.add("DBMS");
        sub.add("JAVA");
        sub.add("MPI");
        sub.add("TOP");
        sub.add("UNIX");
    }

    public void fillSubject_Sem6() {
        sub.clear();
        sub.add("Select Subject...");
        sub.add("Compiler Design");
        sub.add("Computer Graphics");
        sub.add("Computer Network");
        sub.add("ERP");
        sub.add("SEPM");
        sub.add("TCP/IP");
    }

    public void fillSubject_Sem7(){
        sub.clear();
        sub.add("Select Subject...");
        sub.add("Cloud Computing");
        sub.add("Cryptography");
        sub.add("Mobile Computing and Application");
        sub.add("Network Programming");
        sub.add("Parallel Processors and Computing");
    }

    public void fillSubject_Sem8() {
        sub.clear();
        sub.add("Select Subject...");
        sub.add("Artificial Intelligence and Expert System");
        sub.add("Data Mining and Warehousing");
        sub.add("Cyber Security");
        sub.add("Decision Support System");
        sub.add("Internet and Multimedia");
    }
    */



