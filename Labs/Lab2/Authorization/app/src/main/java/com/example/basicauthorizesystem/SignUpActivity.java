package com.example.basicauthorizesystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_Username, edt_Password, edt_ConfirmPass;
    private TextView tv_AlreadyAcc;
    private Button btn_SignUp;

    private final String REQUIRE = "Required";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_Username = (EditText) findViewById(R.id.editTextTextUsername);
        edt_Password = (EditText) findViewById(R.id.editTextTextPass);
        edt_ConfirmPass = (EditText) findViewById(R.id.editTextTextConfirmPass);
        tv_AlreadyAcc = (TextView) findViewById(R.id.textViewSignIn);
        btn_SignUp = (Button) findViewById(R.id.buttonSignUp);

        tv_AlreadyAcc.setOnClickListener(this);
        btn_SignUp.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(edt_Username.getText().toString())) {
            edt_Username.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(edt_Password.getText().toString())) {
            edt_Password.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(edt_ConfirmPass.getText().toString())) {
            edt_ConfirmPass.setError(REQUIRE);
            return false;
        }

        if (!TextUtils.equals(edt_Password.getText().toString(),
                edt_ConfirmPass.getText().toString())) {
            Toast.makeText(this, "Password is not matched", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void signUp() {
        if (!checkInput()) return;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signInForm() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                signUp();
                break;
            case R.id.textViewSignIn:
                signInForm();
                break;
        }
    }
}
