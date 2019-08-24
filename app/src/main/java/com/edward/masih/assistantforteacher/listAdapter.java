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

public class listAdapter extends BaseAdapter {
    ArrayList<String> nameList;
    ArrayList<String> admissionNumber;
    ArrayList<Integer> rollList;
    Activity activity;
    ArrayList<Integer> attendanceList;
    String sub = attendanceActivity.attendanceSubject.getSelectedItem().toString();
    String sem = attendanceActivity.spinner.getSelectedItem().toString();



    public listAdapter(Activity activity,ArrayList<String> nameList,ArrayList<String> admno, ArrayList<Integer> roll) {
        this.nameList = nameList;
        this.activity = activity;
        this.admissionNumber = admno;
        this.rollList = roll;

        attendanceList = new ArrayList<>();
        for(int i=0; i<nameList.size(); i++)
        {
            attendanceList.add(1);
        }
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.list_ele, null);
        }
        final int pos = position;
        TextView textView = (TextView) v.findViewById(R.id.attendanceName);
        textView.setText("Roll No. "+rollList.get(position)+" - "+nameList.get(position).toString());
        final CheckBox checkBox = (CheckBox)v.findViewById(R.id.attMarker);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = BooleanToInt(checkBox.isChecked());
                attendanceList.set(pos,a); //method to convert boolean of tick into integer --below
                Log.d("Attendance", nameList.get(position).toString() + " is absent " + attendanceList.get(position));
            }
        });
        return v;
    }

    public int BooleanToInt(boolean b)
    {
        int a=1,bb=0;
        return b ?  a: bb;
    }

    public void saveAll()
    {
        for(int i=0; i<nameList.size(); i++)
        {
            int status ;
            if(attendanceList.get(i) == 1)
                status = 1;
            else status = 0;
            String qu = "INSERT INTO ATTENDANCE VALUES('" +attendanceActivity.timeDate+ "'," +
                    "'"+nameList.get(i).toString()+"','"+sem.toString()+"','" +admissionNumber.get(i).toString()+"'," +
                    ""+rollList.get(i)+",'"+sub.toString()+"',"+status+");";
            //MainActivity.handler.execAction(qu);
            if (MainActivity.handler.execAction(qu))
            {
                Toast.makeText(activity,"Saving",Toast.LENGTH_LONG).show();
                activity.finish();
            }
            else
                Toast.makeText(activity, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}

