package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button login_btn,register_btn;
    EditText email,password;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.button_Login);
        register_btn= findViewById(R.id.button_registerfirst);
        email = findViewById(R.id.email_editText);
        password =findViewById(R.id.editText_password);
        DB=new DbHelper(this);

//        Intent home = (Intent) findViewById()
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "Please fill all the fields!!", Toast.LENGTH_SHORT).show();
                else{
                    boolean checkemail = DB.checkemail(mail);
                    boolean checkemailpassword = DB.checkemailpassword(mail,pass);
                    if(!checkemail)
                        Toast.makeText(MainActivity.this, "You are not registered, please register first!", Toast.LENGTH_SHORT).show();
                    else if (checkemailpassword) {
                        Intent home= new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);
                    }
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register= new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(register);
            }
        });
    }
}