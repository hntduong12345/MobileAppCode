package com.example.random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txt_RdResult;
    EditText txt_MinVal;
    EditText txt_MaxVal;
    Button btn_RdGenerate;

    int min = 0, max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_RdResult = (TextView) findViewById(R.id.textViewRandomNum);
        txt_MinVal = (EditText) findViewById(R.id.editTextNumberMin);
        txt_MaxVal = (EditText) findViewById(R.id.editTextNumberMax);

        btn_RdGenerate = (Button) findViewById(R.id.button2);

        btn_RdGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check input validation
                if (txt_MinVal.length() > 0 && txt_MaxVal.length() > 0) {
                    min = Integer.parseInt(txt_MinVal.getText().toString());
                    max = Integer.parseInt(txt_MaxVal.getText().toString());
                }

                if(min > max)
                {
                    max = min +1;
                    txt_MaxVal.setText(String.format("%d", max));
                }

                //Generate random number
                Random random = new Random();
                int rdNumber = random.nextInt((max - min) + 1) + min;

                txt_RdResult.setText(String.format("%d", rdNumber));
            }
        });
    }
}