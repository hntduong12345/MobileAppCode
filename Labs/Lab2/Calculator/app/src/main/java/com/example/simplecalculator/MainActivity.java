package com.example.simplecalculator;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText txt_FirstNumber, txt_SecondNumber;
    Button btn_Plus, btn_Minus, btn_Multiply, btn_Divide;
    TextView txt_Result, txt_Error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_Error = (TextView) findViewById((R.id.textViewError));
        txt_Result = (TextView) findViewById(R.id.textViewResult);
        txt_FirstNumber = (EditText) findViewById(R.id.editTextNumber1);
        txt_SecondNumber = (EditText) findViewById(R.id.editTextNumber2);

        btn_Plus = (Button) findViewById(R.id.buttonPlus);
        btn_Minus = (Button) findViewById(R.id.buttonMinus);
        btn_Multiply = (Button) findViewById(R.id.buttonMultiply);
        btn_Divide = (Button) findViewById(R.id.buttonDivide);


        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt_Result.setText(String.format("%s", Calculation('+')));
                    txt_Error.setText("");
                } catch (Exception e) {
                    txt_Error.setText("Please enter 2 number values!");
                }
            }
        });

        btn_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt_Result.setText(String.format("%s", Calculation('-')));
                    txt_Error.setText("");
                } catch (Exception e) {
                    txt_Error.setText("Please enter 2 number values!");
                }
            }
        });

        btn_Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt_Result.setText(String.format("%s", Calculation('*')));
                    txt_Error.setText("");
                } catch (Exception e) {
                    txt_Error.setText("Please enter 2 number values!");
                }
            }
        });

        btn_Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double res = Calculation('/');
                    if (res < 0) {
                        txt_Error.setText("Cannot divided by 0");
                    } else {
                        txt_Result.setText(String.format("%s", Calculation('/')));
                        txt_Error.setText("");
                    }
                } catch (Exception e) {
                    txt_Error.setText("Please enter 2 number values!");
                }
            }
        });

    }

    private double Calculation(char sign) throws Exception {
        double number1 = 0, number2 = 1;
        //Check input validation
        if (txt_FirstNumber.length() > 0 && txt_SecondNumber.length() > 0) {
            number1 = Double.parseDouble(txt_FirstNumber.getText().toString());
            number2 = Double.parseDouble(txt_SecondNumber.getText().toString());
        } else {
            throw new Exception();
        }

        if(number2 == 0) return -1;

        double result = 0;
        switch (sign) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number1 / number2;
                break;
        }

        return result;
    }
}