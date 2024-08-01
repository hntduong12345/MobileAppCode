package com.example.basicauthorizesystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_Username, edt_Password;
    private TextView tv_NoAccYet;
    private Button btn_SignIn;

    private final String REQUIRE = "Required";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edt_Username = (EditText) findViewById(R.id.editTextTextUsername);
        edt_Password = (EditText) findViewById(R.id.editTextTextPass);
        tv_NoAccYet = (TextView) findViewById(R.id.textViewCreateAcc);
        btn_SignIn = (Button) findViewById(R.id.buttonSignIn);

        tv_NoAccYet.setOnClickListener(this);
        btn_SignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignIn:
                signIn();
                break;
            case R.id.textViewCreateAcc:
                signUpForm();
                break;
        }
    }

    private boolean checkInput() {
        if(TextUtils.isEmpty(edt_Username.getText().toString())){
            edt_Username.setError(REQUIRE);
            return false;
        }

        if(TextUtils.isEmpty(edt_Password.getText().toString())){
            edt_Password.setError(REQUIRE);
            return false;
        }

        return true;
    }

    private void signIn(){
        if(!checkInput()){
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpForm(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
