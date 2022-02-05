package com.example.sSqlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btnRegister ;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnRegister = (Button) findViewById(R.id.registerbtn);
        DB = new DatabaseHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals(" ")||pass.equals(" "))
                    Toast.makeText(LoginActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    int checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass > 0) {

                        Toast.makeText(LoginActivity.this,"Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("usrId", String.valueOf(checkuserpass));
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btnRegister .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });

    }
}