package com.example.basicauthorizesystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View calculatorView;
    EditText txt_FirstNumber, txt_SecondNumber;
    Button btn_Plus, btn_Minus, btn_Multiply, btn_Divide;
    TextView txt_Result, txt_Error;
    boolean isDividedByZero;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        calculatorView = inflater.inflate(R.layout.fragment_calculator, container, false);
        return calculatorView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_Error = (TextView) view.findViewById((R.id.textViewError));
        txt_Result = (TextView) view.findViewById(R.id.textViewResult);
        txt_FirstNumber = (EditText) view.findViewById(R.id.editTextNumber1);
        txt_SecondNumber = (EditText) view.findViewById(R.id.editTextNumber2);

        btn_Plus = (Button) view.findViewById(R.id.buttonPlus);
        btn_Minus = (Button) view.findViewById(R.id.buttonMinus);
        btn_Multiply = (Button) view.findViewById(R.id.buttonMultiply);
        btn_Divide = (Button) view.findViewById(R.id.buttonDivide);

        isDividedByZero = false;
    }

    @Override
    public void onStart() {
        super.onStart();
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
                    if (isDividedByZero) {
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
                if (number2 == 0) {
                    isDividedByZero = true;
                    return 0;
                } else {
                    isDividedByZero = false;
                    result = number1 / number2;
                }
                break;
        }

        return result;
    }
}