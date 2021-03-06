package ca.ubco.cosc341.projectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void viewcalendar(View view){
        Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
        startActivity(intent);

    }
    public void contact(View view){
        Intent intent = new Intent(MainActivity.this,EmailForm.class);
        startActivity(intent);
    }
    public void vernonrec(View view){
        Intent intent = new Intent(MainActivity.this,VernonRecCentre.class);
        intent.putExtra("reccentre","VernonRec");
        startActivity(intent);
    }public void kelownarec(View view){
        Intent intent = new Intent(MainActivity.this,KelownaRecCentre.class);
        intent.putExtra("reccentre","KelownaRec");
        startActivity(intent);
    }public void kelownaymca(View view){
        Intent intent = new Intent(MainActivity.this,KelownaYMCA.class);
        intent.putExtra("reccentre","KelownaYMCA");
        startActivity(intent);
    }
}
