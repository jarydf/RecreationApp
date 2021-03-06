package ca.ubco.cosc341.projectprototype;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.io.FileOutputStream;
import java.util.Calendar;
import android.content.Intent;

public class RegisterEvent extends AppCompatActivity{
    private Button selectDate;
    private Button register;
    private DatePickerDialog.OnDateSetListener selectDateListener;
    private TextView type;
    private TextView setPrice;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private String price;
    private String date = null;
    private String finalDate = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);

        spinner1 = findViewById(R.id.enterProgram);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.programs, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        type = findViewById(R.id.type);
        spinner2 = findViewById(R.id.enterType);
        setPrice = findViewById(R.id.price);

        spinner3 = findViewById(R.id.enterTime);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4 = findViewById(R.id.enterLocation);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        type.setVisibility(View.VISIBLE);
                        spinner2.setVisibility(View.VISIBLE);
                        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(RegisterEvent.this, R.array.sports, android.R.layout.simple_spinner_item);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter1);
                        break;
                    case 2:
                        type.setVisibility(View.VISIBLE);
                        spinner2.setVisibility(View.VISIBLE);
                        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(RegisterEvent.this, R.array.music, android.R.layout.simple_spinner_item);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter2);
                        break;
                    case 3:
                        type.setVisibility(View.VISIBLE);
                        spinner2.setVisibility(View.VISIBLE);
                        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(RegisterEvent.this, R.array.fairs, android.R.layout.simple_spinner_item);
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter3);
                        break;
                    default:
                        price = "$0.00";
                        setPrice.setText(price);
                        type.setVisibility(View.GONE);
                        spinner2.setVisibility(View.GONE);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    //do nothing
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = spinner2.getSelectedItem().toString();
                switch(type){
                    case "Basketball":
                        price = "$49.99";
                        break;
                    case "Swimming":
                        price = "$39.99";
                        break;
                    case "Tennis":
                        price = "$99.99";
                        break;
                    case "Yoga":
                        price = "$19.99";
                        break;
                    case "Volleyball":
                        price = "$49.99";
                        break;
                    case "Concert":
                        price = "$149.99";
                        break;
                    case "Lesson":
                        price = "$79.99";
                        break;
                    case "Craft":
                        price = "$49.99";
                        break;
                    case "Book":
                        price = "$9.99";
                        break;
                    case "Career":
                        price = "$19.99";
                        break;
                    default:
                        price = "$0.00";
                }
                setPrice.setText(price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

        selectDate = findViewById(R.id.enterDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        selectDateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        selectDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                if(month < 10 && day <10){
                    date = year+"/0"+month+"/0"+day;
                    finalDate = year+"0"+month+"0"+day;
                }else if(month < 10){
                    date = year+"/0"+month+"/"+day;
                    finalDate = year+"0"+month+day;
                }
                else if(day < 10){
                    date = year+"/"+month+"/0"+day;
                    finalDate = year+month+"0"+day;
                }else{
                    date = year+"/"+month+"/"+day;
                    finalDate = ""+year+month+day;
                }

                selectDate.setText(date);
            }
        };


        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String program = null;
                String type = null;
                String startTime = null;
                String endTime = null;
                String location = null;
                String info = null;
                double price2;
                if (spinner1.getSelectedItem() != null) {
                    program = spinner1.getSelectedItem().toString();
                }
                if (spinner2.getSelectedItem() != null) {
                    type = spinner2.getSelectedItem().toString();
                }
                if (spinner3.getSelectedItem() != null) {
                    startTime = spinner3.getSelectedItem().toString();
                    char checkTime = startTime.charAt(0);
                    int finalTime;
                    if ('1' == checkTime) {
                        char secondTime = startTime.charAt(1);
                        switch (secondTime) {
                            case '0':
                                finalTime = 10;
                                break;
                            case '1':
                                finalTime = 11;
                                break;
                            case '2':
                                finalTime = 12;
                                break;
                            default:
                                finalTime = 1;
                                break;
                        }
                    } else {
                        finalTime = Character.getNumericValue(checkTime);
                    }
                    switch (finalTime) {
                        case 8:
                            endTime = "9:00";
                            break;
                        case 9:
                            endTime = "10:00";
                            break;
                        case 10:
                            endTime = "11:00";
                            break;
                        case 11:
                            endTime = "12:00";
                            break;
                        case 12:
                            endTime = "1:00";
                            break;
                        case 1:
                            endTime = "2:00";
                            break;
                        case 2:
                            endTime = "4:00";
                            break;
                        case 3:
                            endTime = "4:00";
                            break;
                        case 4:
                            endTime = "5:00";
                            break;
                    }
                }
                if (spinner4.getSelectedItem() != null) {
                    location = spinner4.getSelectedItem().toString();
                }
                boolean checkProgram = ("Select Program".equalsIgnoreCase(program));
                boolean checkType = ("Select Type".equalsIgnoreCase(type));
                boolean checkLocation = ("Select Location".equalsIgnoreCase(location));
                boolean checkTime = ("Select Time".equalsIgnoreCase(startTime));
                boolean checkDate = (date == null);
                if (!checkProgram && !checkLocation && !checkType && !checkTime && !checkDate) {
                    try {
                        price2 = Double.parseDouble(price.substring(1));
                    } catch (NumberFormatException e) {
                        price2 = 0;
                    }
                    info = type + "," + startTime + "," + endTime + "," + date + "," + location + "," + price2+"\n";

                    String filename = "output.txt";   // file to be written in
                    FileOutputStream outputStream;

                    try {
                        // opening file to be written
                        outputStream = openFileOutput(filename, RegisterEvent.MODE_APPEND);
                        outputStream.write(info.getBytes());
                        outputStream.close();
                        Log.d("test", info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    String program = null;
//                    double price2;
                    Intent intent= new Intent(v.getContext(),ShoppingCart.class);
                    Bundle bundle = new Bundle();
                    bundle.putDouble("price", price2);
                    bundle.putString("program", program);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    if (checkProgram) {
                        Toast.makeText(getApplicationContext(), "Select Program", Toast.LENGTH_SHORT).show();
                    }
                    if (checkType) {
                        Toast.makeText(getApplicationContext(), "Select Type", Toast.LENGTH_SHORT).show();
                    }
                    if (checkLocation) {
                        Toast.makeText(getApplicationContext(), "Select Location", Toast.LENGTH_SHORT).show();
                    }
                    if (checkDate) {
                        Toast.makeText(getApplicationContext(), "Select Date", Toast.LENGTH_SHORT).show();
                    }
                    if (checkTime) {
                        Toast.makeText(getApplicationContext(), "Select Time", Toast.LENGTH_SHORT).show();
                    }

                }
            }
       });
    }
}
