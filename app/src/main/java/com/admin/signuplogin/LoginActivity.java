package com.admin.signuplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button mBtnLogin, mBtnRegister;
    private EditText mEditEmail, mEditPassWord;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DbHelper(this);
        session = new Session(this);
        initView();

        //如果已經登入，就直接登入頁面
        if (session.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void initView() {
        mBtnLogin = (Button)findViewById(R.id.btnLogin);
        mBtnRegister = (Button)findViewById(R.id.btnReg);
        mEditEmail = (EditText)findViewById(R.id.editEmail);
        mEditPassWord = (EditText)findViewById(R.id.editPassword);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            default:
        }
    }

    private void login(){
        String email = mEditEmail.getText().toString();
        String password = mEditPassWord.getText().toString();

        if (db.getUser(email, password)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Wrong email/password",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
