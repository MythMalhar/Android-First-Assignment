package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
        EditText name_edittext,email_edittext,password_edittext;
        Button singup_btn,login_btn;
        DbHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        email_edittext=findViewById(R.id.editText_email);
        name_edittext=findViewById(R.id.editText_name);
        password_edittext=findViewById(R.id.editText_password);
        singup_btn= findViewById(R.id.button_signup);
        login_btn=findViewById(R.id.button_gotologin);
        DB=new DbHelper(this);

        singup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edittext.getText().toString();
                String name = name_edittext.getText().toString();
                String password = password_edittext.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                    Toast.makeText(SignupActivity.this, "Please Fill All the Fields!!!", Toast.LENGTH_SHORT).show();
                else{
                    boolean checkemail = DB.checkemail(email);
                    if(checkemail)
                        Toast.makeText(SignupActivity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                    else{
                        boolean insertdata = DB.insertdata(name,password,email);
                        if(insertdata) {
                            Toast.makeText(SignupActivity.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                            Intent loginactivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(loginactivity);
                        }
                        else
                            Toast.makeText(SignupActivity.this, "Registeration Failed!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginactivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginactivity);
            }
        });
    }
}
