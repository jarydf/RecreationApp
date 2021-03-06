package ca.ubco.cosc341.projectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CalendarActivity extends AppCompatActivity {
    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);


        //count.setText(((ArrayList) matrix.get(0)).get(0).toString());
        //studentnumber.setText(((ArrayList) matrix.get(0)).get(1).toString());
        //name.setText(((ArrayList) matrix.get(0)).get(3).toString() + " " + ((ArrayList) matrix.get(0)).get(2).toString());
        //gender.setText(((ArrayList) matrix.get(0)).get(4).toString());
        //division.setText(((ArrayList) matrix.get(0)).get(5).toString());


        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                //int date1 = year + month + dayOfMonth;
                //String date = Integer.toString(date1);
                //String program = ((ArrayList) matrix.get(date)).get(0).toString();
                int monthactual = month + 1;
                String date = year +""+  monthactual +""+  dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: yyyymmdd:" + date);
                Intent intent = new Intent(CalendarActivity.this,ShowCalendar.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });
    }
    public void home(View view){
        Intent intent2 = new Intent(CalendarActivity.this,MainActivity.class);
        startActivity(intent2);
    }
}