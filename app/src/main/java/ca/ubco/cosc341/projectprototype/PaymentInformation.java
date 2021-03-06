package ca.ubco.cosc341.projectprototype;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PaymentInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        Intent intentget = getIntent();
        Bundle bundle = intentget.getExtras();
        final Double totalAmount = bundle.getDouble("totalAmount");
        Spinner spinner1 = (Spinner) findViewById(R.id.info);
//        if (spinner1.getSelectedItem() != null) {
//
//        }
        String filename = "output1.txt";
        String line="";
        String data="";
        int counter=0;
        int length=0;
        String[] stringArray={};
        Button payNow = findViewById(R.id.payNow);
        payNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {payNow(totalAmount); }
        });

        try {
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                data += (counter + 1) + "\t" + line + "\n";
                counter++;
                stringArray = line.split(",");
            }
            length=stringArray.length;
            saveInfo(stringArray[length-7]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveInfo(String cardName){
        Spinner spinner1 = (Spinner) findViewById(R.id.info);
        List<String> list = new ArrayList<String>();
        list.add(cardName);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    public void payNow(Double totalAmount){
        RadioGroup radioGroup = findViewById(R.id.cardType);
        int radio_id = radioGroup.getCheckedRadioButtonId();

        EditText cardNumberEdit=findViewById(R.id.cardNumber);
        String cardNumber=cardNumberEdit.getText().toString();

        EditText nameEdit=findViewById(R.id.name);
        String name=nameEdit.getText().toString();

        EditText billingAddressEdit=findViewById(R.id.billingAddress);
        String billingAddress=billingAddressEdit.getText().toString();

        EditText cvvEdit=findViewById(R.id.cvv);
        String cvv=cvvEdit.getText().toString();

        EditText expiryEdit=findViewById(R.id.expiry);
        String expiry=expiryEdit.getText().toString();

        String cardType="";
        switch (radio_id){
            case R.id.visa:
                cardType = "Visa";
                break;
            case R.id.mastercard:
                cardType = "Mastercard";
                break;
            case R.id.other:
                cardType = "Other";
                break;
            default:cardType="unknown";
        }
        if (!cardNumber.matches("\\d+(?:\\.\\d+)?")){
            Toast.makeText(this, "card number is incorrect", Toast.LENGTH_SHORT).show();
        }
        else if (!cvv.matches("\\d+(?:\\.\\d+)?")){
            Toast.makeText(this, "cvv is incorrect", Toast.LENGTH_SHORT).show();
        }
        else if (name.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
        }
        else if(cvv.length()!=3){
            Toast.makeText(this, "cvv length is incorrect", Toast.LENGTH_SHORT).show();
        }
        else if(cardType=="unknown"){
            Toast.makeText(this, "didnt select card type", Toast.LENGTH_SHORT).show();
        }
        else if(expiry.length()!=5){
            Toast.makeText(this, "expiry date is incorrect", Toast.LENGTH_SHORT).show();
        }
        else {
            CheckBox saveInfo = findViewById(R.id.saveInfo);
            String filename = "output1.txt";
            String data = name + ", "+cardType + ", " + cardNumber + ", " + cvv + ", " + expiry + "," + billingAddress + ", /n";
            FileOutputStream outputStream;
            if(saveInfo.isChecked()){
                Context context;
                    try {
                        FileOutputStream fileout=openFileOutput("output1.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                        outputWriter.write(data);
                        outputWriter.close();
//                        Toast.makeText(this, "yay", Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e) {
                        Log.e("Exception", "File write failed: " + e.toString());
                    }
            }
            Intent intent1 = new Intent(this, PaymentReceipt.class);
            Bundle bundle1 = new Bundle();
            bundle1.putDouble("totalAmount", totalAmount);
            intent1.putExtras(bundle1);
            startActivity(intent1);
        }
    }
}
