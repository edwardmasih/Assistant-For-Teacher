package com.edward.masih.assistantforteacher;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import  android.Manifest;

public class smsActivity extends AppCompatActivity {

    EditText _txtmess, _txtsender, _txtnum;
    Button _btnsend;

    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_activity);

        _txtmess = findViewById(R.id.smsbody);
        _txtsender = findViewById(R.id.txtsender);
        _txtnum = findViewById(R.id.txtphone);
        _btnsend = findViewById(R.id.btnsend);


        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);

        _btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg =  "From - "+_txtsender.getText().toString()+"\n\n"+ _txtmess.getText().toString();
                String phoneNumber = _txtnum.getText().toString();

                if (checkPermission(Manifest.permission.SEND_SMS))
                {
                    if (!TextUtils.isEmpty(msg) && !TextUtils.isEmpty(phoneNumber))
                    {
                        if (phoneNumber.length()<10 || phoneNumber.length()>10)
                        {
                            Toast.makeText(smsActivity.this, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber,null,msg,null,null);
                            Toast.makeText(smsActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    else
                    {
                        Toast.makeText(smsActivity.this, "Enter Message Body & Phone Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(smsActivity.this, "Permission Denied.\nPlease give SMS Permissions.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkPermission(String permission)
    {
        int checkPermission = ContextCompat.checkSelfPermission(this,permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case SEND_SMS_PERMISSION_REQUEST_CODE:
                if (grantResults.length>0 && (grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    _btnsend.setEnabled(true);
                }
                break;
        }
    }

}
