package com.example.remainder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("My NOTIFICATION","notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    public void OnButtonClick(View view){
        EditText numberHours=findViewById(R.id.editTextHour);
        EditText numberofminutes=findViewById(R.id.editTextMinute);
        EditText numberseconds=findViewById(R.id.editTextSecond);
        EditText nameOfRemainder=findViewById(R.id.editTextNameOfRemainder);
        String stringhours=numberHours.getText().toString();
        String stringminutes=numberofminutes.getText().toString();
        String stringseconds=numberseconds.getText().toString();
        String nameOfTheReaminderEntered=nameOfRemainder.getText().toString();
        int hours=Integer.parseInt(stringhours);
        int minutes=Integer.parseInt(stringminutes);
        int seconds=Integer.parseInt(stringseconds);
        int totaltime=(hours*3600+minutes*60+seconds)*1000;
        MyIntentService.setTime(totaltime);
        MyIntentService.setNameofRemainder(nameOfTheReaminderEntered);


        Intent intent=new Intent(this,MyIntentService.class);
        //intent.putExtra(MyIntentService.MESSAGE,MyIntentService.MESSAGE);
        startService(intent);


    }
}