package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

public class SettingsActivity extends AppCompatPreferenceActivity {
    Activity base = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();

        addPreferencesFromResource(R.xml.pref_general);
        Preference myPref;


        myPref = findPreference("clear_attendance");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(base);
                alert.setTitle("Are you sure ?");
                alert.setMessage("This will wipe your entire attendance data");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String qu = "DROP TABLE ATTENDANCE";
                        if(MainActivity.handler.execAction(qu))
                        {
                            Toast.makeText(getBaseContext(),"Done",Toast.LENGTH_LONG).show();
                            MainActivity.handler.createTable();
                            dialog.dismiss();
                        }
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                alert.show();
                return true;
            }
        });

        myPref = findPreference("clear_notes");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(base);
                alert.setTitle("Are you sure ?");
                alert.setMessage("This will wipe your entire Notes data");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String qu = "DROP TABLE NOTES";
                        if(MainActivity.handler.execAction(qu))
                        {
                            Toast.makeText(getBaseContext(),"Done",Toast.LENGTH_LONG).show();
                            MainActivity.handler.createTable();
                            dialog.dismiss();
                        }
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                alert.show();
                return true;
            }
        });

        myPref = findPreference("clear_student");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(base);
                alert.setTitle("Are you sure ?");
                alert.setMessage("This will wipe your entire Student data");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String qu = "DROP TABLE STUDENT";
                        if(MainActivity.handler.execAction(qu))
                        {
                            Toast.makeText(getBaseContext(),"Done",Toast.LENGTH_LONG).show();
                            MainActivity.handler.createTable();
                            dialog.dismiss();
                        }
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                alert.show();
                return true;
            }
        });
        /*
        myPref = findPreference("clear_schedule");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(base);
                alert.setTitle("Are you sure ?");
                alert.setMessage("This will wipe your entire scheduling data");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String qu = "DROP TABLE SCHEDULE";
                        if (MainActivity.handler.execAction(qu)) {
                            Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
                            MainActivity.handler.createTable();
                            dialog.dismiss();
                        }
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                alert.show();
                return true;
            }
        });
        */
    }
    private void setupActionBar() {
        android.app.ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Settings");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
