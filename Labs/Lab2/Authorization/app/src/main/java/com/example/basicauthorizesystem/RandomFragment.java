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

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView txt_RdResult;
    EditText txt_MinVal;
    EditText txt_MaxVal;
    Button btn_RdGenerate;

    int min = 0, max = 0;

    public RandomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RandomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RandomFragment newInstance(String param1, String param2) {
        RandomFragment fragment = new RandomFragment();
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
        return inflater.inflate(R.layout.fragment_random, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_RdResult = (TextView) view.findViewById(R.id.textViewRandomNum);
        txt_MinVal = (EditText) view.findViewById(R.id.editTextNumberMin);
        txt_MaxVal = (EditText) view.findViewById(R.id.editTextNumberMax);

        btn_RdGenerate = (Button) view.findViewById(R.id.button2);
    }

    @Override
    public void onStart() {
        super.onStart();
        btn_RdGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check input validation
                if (txt_MinVal.length() > 0 && txt_MaxVal.length() > 0) {
                    min = Integer.parseInt(txt_MinVal.getText().toString());
                    max = Integer.parseInt(txt_MaxVal.getText().toString());
                }

                if (min > max) {
//                    max = min +1;
                    min += max;
                    max = min - max;
                    min -= max;
                    txt_MaxVal.setText(String.format("%d", max));
                    txt_MinVal.setText(String.format("%d", min));
                }

                //Generate random number
                Random random = new Random();
                int rdNumber = random.nextInt((max - min) + 1) + min;

                txt_RdResult.setText(String.format("%d", rdNumber));
            }
        });
    }
}