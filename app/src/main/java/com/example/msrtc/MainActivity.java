package com.example.msrtc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText tx1,tx2,tx3;
    TextView tv1,tv2,tv3,tv4,tv5;
    Button b1;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        tx1 = findViewById(R.id.pid);
        tx2 = findViewById(R.id.email);
        tx3 = findViewById(R.id.password);
        b1 = findViewById(R.id.btnLogin);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(!tx1.getText().toString().isEmpty() && !tx2.getText().toString().isEmpty() && !tx3.getText().toString().isEmpty())
                    {
                        boolean value = databaseHelper.insertUser(tx2.getText().toString() , tx3.getText().toString());
                        if(value == true)
                        {
                            Intent intent = new Intent(MainActivity.this , Dashboard.class);
                            intent.putExtra("email" , tx2.getText().toString());
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error Registering! Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }
}