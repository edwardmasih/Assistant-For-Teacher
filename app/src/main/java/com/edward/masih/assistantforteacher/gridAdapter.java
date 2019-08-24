package com.edward.masih.assistantforteacher;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class gridAdapter extends BaseAdapter {
    ArrayList names;
    public static Activity activity;

    public gridAdapter(Activity activity, ArrayList names) {
        this.activity = activity;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.grid_layout, null);
        }
        TextView textView = (TextView)v.findViewById(R.id.namePlacer);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageHolder);
        if(names.get(position).toString().equals("ATTENDANCE"))
        {
            imageView.setImageResource(R.drawable.ic_attendance);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent attend = new Intent(activity, attendanceActivity.class);
                    activity.startActivity(attend);
                }
            });
        }

        else if(names.get(position).toString().equals("NOTES"))
        {
            imageView.setImageResource(R.drawable.ic_notes);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent note = new Intent(activity, noteActivity.class);
                    activity.startActivity(note);
                }
            });
        }

        else if(names.get(position).toString().equals("STUDENT PROFILE"))
        {
            imageView.setImageResource(R.drawable.ic_profile);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profileIntent = new Intent(activity, profile_activity.class);
                    activity.startActivity(profileIntent);
                }
            });
        }
        else if(names.get(position).toString().equals("REGISTER & EDIT"))
        {
            imageView.setImageResource(R.drawable.ic_register);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profileIntent = new Intent(activity, Student_Registration.class);
                    activity.startActivity(profileIntent);
                }
            });
        }
        else if(names.get(position).toString().equals("ATTENDANCE INFO"))
        {
            imageView.setImageResource(R.drawable.ic_stat);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profileIntent = new Intent(activity, StudentAttendanceInfo.class);
                    activity.startActivity(profileIntent);
                }
            });
        }
        else if(names.get(position).toString().equals("SEND SMS"))
        {
            imageView.setImageResource(R.drawable.ic_message);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profileIntent = new Intent(activity, smsActivity.class);
                    activity.startActivity(profileIntent);
                }
            });
        }
        /*
        else if(names.get(position).toString().equals("CGPA CALCULATOR"))
        {
            imageView.setImageResource(R.drawable.ic_cgpa);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cgpaIntent = new Intent(activity,cgpa_activity.class);
                    activity.startActivity(cgpaIntent);
                }
            });
        }
        */
        /*
        else if(names.get(position).toString().equals("SCHEDULER"))
        {
            imageView.setImageResource(R.drawable.ic_schedule);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent schedulerIntent = new Intent(activity, scheduler.class);
                    activity.startActivity(schedulerIntent);
                }
            });
        }
        */
        textView.setText(names.get(position).toString());
        return v;
    }

}
