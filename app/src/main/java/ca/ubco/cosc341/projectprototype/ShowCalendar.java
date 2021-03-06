package ca.ubco.cosc341.projectprototype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShowCalendar extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView thedate;
    private TextView program0;
    private TextView program1;
    private TextView program2;
    private TextView time0;
    private TextView time1;
    private TextView time2;
    private TextView location0;
    private TextView location1;
    private TextView location2;

    private TextView events;

    private TextView header1;
    private TextView header2;
    private TextView header3;


    private Button btngocalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = (TextView) findViewById(R.id.textView2);

        header1 = (TextView) findViewById(R.id.activityheaders);
        header2 = (TextView) findViewById(R.id.timeheaders);
        header3 = (TextView) findViewById(R.id.locationheader);


        thedate = (TextView) findViewById(R.id.date);
        program0 = (TextView) findViewById(R.id.activity0);
        program1 = (TextView) findViewById(R.id.activity1);
        program2 = (TextView) findViewById(R.id.activity2);

        time0 = (TextView) findViewById(R.id.time0);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);

        location0 = (TextView) findViewById(R.id.location0);
        location1 = (TextView) findViewById(R.id.location1);
        location2 = (TextView) findViewById(R.id.location2);


        btngocalendar = (Button) findViewById(R.id.btngocalendar);

        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");

        String file = "output.txt";//initializing file to be written into later
        String line = "";
        String data = "";

        //File read operation
        try {
            FileInputStream fis = openFileInput(file);  //A FileInputStream obtains input bytes from a file in a file system
            InputStreamReader isr = new InputStreamReader(fis); //An InputStreamReader is a bridge from byte streams to character streams
            BufferedReader br = new BufferedReader(isr);    //Reads text from a character-input stream,
            int i = 0;

            while ((line = br.readLine()) != null) {// while there are still lines to read
                String[] array = line.split(",");//creating an array and store the comma delimited line in it
                if(array[3].equals(date)){
                    if (i == 0) {
                        program0.setText(array[0]);
                        time0.setText(array[1] + " " + array[2]);
                        location0.setText(array[4]);
                        events.setText("Events For ");

                        i++;
                    } else if (i == 1) {
                        program1.setText(array[0]);
                        time1.setText(array[1] + " " + array[2]);
                        location1.setText(array[4]);
                        events.setText("Events For ");



                        i++;
                    }else if (i == 2) {
                        program2.setText(array[0]);
                        time2.setText(array[1] + " " + array[2]);
                        location2.setText(array[4]);
                        events.setText("Events For ");

                    }
                }
                else{

                }
            }
        } catch(FileNotFoundException e){//handling our exceptions
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        thedate.setText(date);

        btngocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String filename = "output.txt";
//                String fileContents = "Basketball" + "," + "7:00 PM" + "," + "9:00 PM" + ","  + 20181119 +  "," + "Vernon Rec Centre" + "\n";//creating the file which each value separated by a comma which we can use later to get each value
//                fileContents += "Soccer" + "," + "7:00 PM" + "," + "8:00 PM" +","  + 20181120 +  "," + "Kelowna Rec Centre" + "\n";//creating the file which each value separated by a comma which we can use later to get each value
//                fileContents += "Football" + "," + "4:00 PM" + "," + "8:00 PM" +","  + 20181119 +  "," + "Vernon Rec Centre" + "\n";//creating the file which each value separated by a comma which we can use later to get each value
//                fileContents += "Dance" + "," + "5:00 PM" + "," + "8:00 PM" +","  + 20181119 +  "," + "Kelowna YMCA" + "\n";//creating the file which each value separated by a comma which we can use later to get each value
//                fileContents += "Basketball" + "," + "6:00 PM" + "," + "8:00 PM" +","  + 20181120 +  "," + "Kelowna Rec Centre" + "\n";//creating the file which each value separated by a comma which we can use later to get each value
//
//                FileOutputStream outputStream;  //Allow a file to be opened for writing
//
//                try {
//                    outputStream = openFileOutput(filename, Context.MODE_APPEND);
//                    outputStream.write(fileContents.getBytes());    //FileOutputStream is meant for writing streams of raw bytes.
//                    outputStream.close();
//                } catch (Exception e) {//checking for exceptions
//                    e.printStackTrace();
//                }

                Intent intent = new Intent(ShowCalendar.this,CalendarActivity.class);
                startActivity(intent);
            }

        });


    }
    public void email(View view){
        Intent intent1 = new Intent(ShowCalendar.this,EmailForm.class);
        startActivity(intent1);
    }
    public void home(View view){
        Intent intent2 = new Intent(ShowCalendar.this,MainActivity.class);
        startActivity(intent2);
    }
}