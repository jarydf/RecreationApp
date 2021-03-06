package ca.ubco.cosc341.projectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PaymentReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

        Intent intentget = getIntent();
        Bundle bundle = intentget.getExtras();
        final Double totalAmount = bundle.getDouble("totalAmount");
        TextView total=findViewById(R.id.total);
        total.setText("$"+totalAmount.toString());
        Button home = findViewById(R.id.homePageButton);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Button calendar = findViewById(R.id.calendarButton);
        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
