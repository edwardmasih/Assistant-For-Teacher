package com.edward.masih.assistantforteacher;

import android.app.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listAdapter2 extends BaseAdapter {
    ArrayList<String> dateList, admno;
    ArrayList<Integer> isPresentList;
    Activity activity;
    String naam;

    public listAdapter2(Activity activity,ArrayList<String> dates, ArrayList<Integer> isPresent, ArrayList<String> admno, String name) {
        this.activity = activity;
        this.dateList= dates;
        this.isPresentList = isPresent;
        this.admno = admno;
        this.naam = name;

    }


    @Override
    public int getCount() {
        return dateList.size();
    }

    @Override
    public Object getItem(int i) {
        return dateList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View v, ViewGroup viewGroup) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.attendance_info_list, null);
        }
        final int pos = position;
        TextView textView = v.findViewById(R.id.attendanceDate);
        textView.setText(""+dateList.get(position).toString());
        final CheckBox checkBox = v.findViewById(R.id.attMarker);
        checkBox.setClickable(false);
        if(isPresentList.get(position).equals(1))
        {
           checkBox.setChecked(true);
        }
        else{
            checkBox.setChecked(false);
        }

        checkBox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                alert.setTitle("Alter Student Attendance");
                alert.setMessage("Are you sure?");
                alert.setPositiveButton("Present", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkBox.setChecked(true);
                        String qu = "UPDATE ATTENDANCE SET isPresent = " + 1 + " WHERE " +
                                "name = '" + naam + "' AND datex = '" + dateList.get(position) + "';";
                        if (MainActivity.handler.execAction(qu)) {
                            Toast.makeText(activity,"Done",Toast.LENGTH_LONG).show();
                        }
                        Log.d("profile", qu);
                    }
                });
                alert.setNegativeButton("Absent", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkBox.setChecked(false);
                        String qu = "UPDATE ATTENDANCE SET isPresent = " + 0 + " WHERE " +
                                "name = '" + naam + "' AND datex = '" + dateList.get(position) + "';";
                        if (MainActivity.handler.execAction(qu)) {
                            Toast.makeText(activity,"Done",Toast.LENGTH_LONG).show();
                        }
                        Log.d("profile", qu);
                    }
                });
                alert.show();
                return true;
            }
        });

        return v;
    }

}
