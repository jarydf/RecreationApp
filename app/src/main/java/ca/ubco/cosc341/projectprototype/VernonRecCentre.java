package ca.ubco.cosc341.projectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VernonRecCentre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vernon_rec_centre);
        Intent incoming = getIntent();
        String recvalue = incoming.getStringExtra("reccentre");
        Button register = findViewById(R.id.button);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterEvent.class);
                startActivity(intent);
            }
        });
    }
}
