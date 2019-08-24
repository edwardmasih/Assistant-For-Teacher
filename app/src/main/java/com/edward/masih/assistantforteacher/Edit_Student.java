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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Student extends AppCompatActivity {
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__student);

        Button loadButton = (Button) findViewById(R.id.loadForEdit);
        assert loadButton != null;
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadkar();
            }
        });


        Button saveEdit = (Button) findViewById(R.id.buttonSAVEEDITS);
        assert saveEdit != null;
        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.edit_name_);
                EditText adm = (EditText) findViewById(R.id.register_);
                EditText roll = (EditText) findViewById(R.id.roll_);
                EditText contact = (EditText) findViewById(R.id.contact_);
                if (name.getText().toString() == "" || roll.getText().toString() == "" || contact.getText().toString() == "" || adm.getText().toString() == "") {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setTitle("Invalid");
                    alert.setMessage("Insufficient Data");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                    return;
                }
                else{
                    savekar();
                }

            }
        });

        Button deleteStudent = (Button) findViewById(R.id.delete_student);
        assert deleteStudent != null;
        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletekar();
            }
        });
    }


    public void loadkar() {
        EditText adm = (EditText) findViewById(R.id.register_);
        String qu = "SELECT * FROM STUDENT WHERE admno = '" + adm.getText().toString().toUpperCase() + "'";
        if (adm.getText().toString() == "") {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        Cursor cr = MainActivity.handler.execQuery(qu);
        if (cr == null || cr.getCount() == 0) {
            Toast.makeText(getBaseContext(), "No Such Student", Toast.LENGTH_LONG).show();
        } else {
            cr.moveToFirst();
            try {
                EditText name = (EditText) findViewById(R.id.edit_name_);
                EditText roll = (EditText) findViewById(R.id.roll_);
                EditText contact = (EditText) findViewById(R.id.contact_);
                assert name != null;
                name.setText(cr.getString(0));
                assert roll != null;
                roll.setText(cr.getString(4));
                assert contact != null;
                contact.setText(cr.getString(3));
            } catch (Exception e) {
            }
        }
    }

    public void deletekar()
    {
        final EditText name =  findViewById(R.id.edit_name_);
        final EditText roll =  findViewById(R.id.roll_);
        final EditText contact =  findViewById(R.id.contact_);
        final EditText adm = findViewById(R.id.register_);

        if(name.getText().toString()=="" || roll.getText().toString()=="" || contact.getText().toString()=="" || contact.getText().toString().length()<10|| contact.getText().toString().length()>10 || adm.getText().toString()=="")
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Delete Student");
        alert.setMessage("Are you sure ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String qu = "DELETE FROM STUDENT WHERE name = '" + name.getText().toString() +"' AND admno='"+ adm.getText().toString().toUpperCase()+"';";
                if (MainActivity.handler.execAction(qu)) {
                    Log.d("delete", "done from student");
                    String qa = "DELETE FROM ATTENDANCE WHERE name = '" + name.getText().toString() + "' AND admno = '" + adm.getText().toString().toUpperCase() + "';";
                    if (MainActivity.handler.execAction(qa)) {
                        Toast.makeText(activity, "Deleted", Toast.LENGTH_LONG).show();
                        name.setText("");
                        roll.setText("");
                        contact.setText("");
                        adm.setText("");
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
    }

    public void savekar()
    {
        EditText name = (EditText) findViewById(R.id.edit_name_);
        EditText adm = (EditText) findViewById(R.id.register_);
        EditText roll = (EditText) findViewById(R.id.roll_);
        EditText contact = (EditText) findViewById(R.id.contact_);


        String qu = "UPDATE STUDENT SET name = '" + name.getText().toString() + "' , " +
                " roll = " + roll.getText().toString() + " , contact = '" + contact.getText().toString() + "' " +
                "WHERE admno = '" + adm.getText().toString().toUpperCase() + "'";
        Log.d("Edit_Student", qu);
        if (MainActivity.handler.execAction(qu)) {
            Toast.makeText(getBaseContext(), "Edit Saved", Toast.LENGTH_LONG).show();
            activity.finish();

        } else
            Toast.makeText(getBaseContext(), "Save Error Occured", Toast.LENGTH_LONG).show();

    }
}
