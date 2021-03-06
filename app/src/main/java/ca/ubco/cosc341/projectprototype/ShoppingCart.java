package ca.ubco.cosc341.projectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileOutputStream;

public class ShoppingCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        Intent intentget = getIntent();
        Bundle bundle = intentget.getExtras();
        String program = bundle.getString("program");
        final Double price = bundle.getDouble("price");

        TextView amount= findViewById(R.id.amount);
        amount.setText("$"+price.toString());
        final TextView programName = findViewById(R.id.programName);
        programName.setText(program);
        TextView programPrice=findViewById(R.id.price);

        TextView totalAmount=findViewById(R.id.totalAmount);
        totalAmount.setText(amount.getText());
        totalAmount.setText(amount.getText());

        final Button checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {nextPage(view,price); }
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterEvent.class);
                startActivity(intent);
            }
        });
        final Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ViewGroup layout = (ViewGroup) removeButton.getParent();
                    layout.removeView(removeButton);
                    TextView amount= findViewById(R.id.amount);
                    amount.setText("");
                    TextView programName = findViewById(R.id.programName);
                    programName.setText("");
                    TextView totalAmount=findViewById(R.id.totalAmount);
                    totalAmount.setText(amount.getText());
                    ViewGroup layout1 = (ViewGroup) checkout.getParent();
                    layout1.removeView(checkout);
            }
        });
    }
    public void nextPage(View view,Double total){
        TextView totalAmount=findViewById(R.id.totalAmount);
        Intent intent = new Intent(this, PaymentInformation.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("totalAmount", total);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
