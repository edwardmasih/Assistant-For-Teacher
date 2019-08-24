package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Student_Registration extends AppCompatActivity {

    Activity activity = this;
    EditText name,roll,admno,contact;
    Spinner spinner;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registration);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,MainActivity.sem);
        spinner.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.buttonSAVE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });

        Button login = findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivityForResult(intent,0);
            }
        });

        Button login1 = findViewById(R.id.Signup);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent,0);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = findViewById(R.id.edit_name);
        EditText roll = findViewById(R.id.roll);
        EditText admno = findViewById(R.id.admno);
        EditText contact = findViewById(R.id.contact);
        String semSelected = spinner.getSelectedItem().toString();

        if(name.getText().length()<2||roll.getText().length()==0||admno.getText().toString().length()<2||
                contact.getText().toString().length()<10|| contact.getText().toString().length()>10 || spinner.getSelectedItem().toString()=="Select The Semester...")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        String qu = "INSERT INTO STUDENT VALUES('" +name.getText().toString()+ "'," +
                "'" + semSelected.toString() +"',"+
                "'" + admno.getText().toString().toUpperCase() +"',"+
                "'" + contact.getText().toString() +"',"+
                "" + Integer.parseInt(roll.getText().toString()) +");";
        Log.d("Student Reg" , qu);
        MainActivity.handler.execAction(qu);
        qu = "SELECT * FROM STUDENT WHERE admno = '" + admno.getText().toString() +  "';";
        Log.d("Student Reg" , qu);
        try{
            if(MainActivity.handler.execQuery(qu)!=null)
            {
                Toast.makeText(getBaseContext(),"Student Added", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }
        catch(Exception e){
            Toast.makeText(this, "Error!\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void editStudent(MenuItem item) {
        Intent launchIntent  = new Intent(this,Edit_Student.class);
        startActivity(launchIntent);
    }
}

