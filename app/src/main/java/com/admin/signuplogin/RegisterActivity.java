package com.admin.signuplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button mBntReg;
    private TextView mTextLogin;
    private EditText mEtEmail, mEtPass;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DbHelper(this);
        initView();
    }

    private void initView() {
        mBntReg = (Button)findViewById(R.id.btnReg);
        mTextLogin = (TextView)findViewById(R.id.tvLogin);
        mEtEmail = (EditText)findViewById(R.id.etEmail);
        mEtPass = (EditText)findViewById(R.id.edPass);

        mBntReg.setOnClickListener(this);
        mTextLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            default:
        }
    }

    private void register(){
        String email = mEtEmail.getText().toString();
        String password = mEtPass.getText().toString();
        if (email.isEmpty() && password.isEmpty()){
            displayToast("Username/password field empty");
        }else {
            db.addUser(email,password);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
