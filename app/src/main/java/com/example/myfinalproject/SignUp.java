package com.example.myfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Activity {

    EditText name,email,phoneNumber,password,confirmpassword;


    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name = (EditText) findViewById(R.id.txt_name);
        email = (EditText) findViewById(R.id.txt_login_email);
        phoneNumber = (EditText) findViewById(R.id.txt_phone);
        password = (EditText) findViewById(R.id.txt_login_password);
        confirmpassword = (EditText)findViewById(R.id.txt_confirm_password);
        database = FirebaseDatabase.getInstance().getReference("Users");

        Button signupbutton = findViewById(R.id.btn_signup);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SetValidation1();
            }
        });

        Button login_page = findViewById(R.id.btn_loginpage);

        login_page.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent tologinpage = new Intent(getApplicationContext(), Login.class);
                startActivity(tologinpage);
            }
        });
    }

    public void SetValidation1() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            name.setError("Name should not be empty");
            isNameValid = false;
        } else  {
            isNameValid = true;

        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            email.setError("Email should not be empty");
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Invalid Email Id");
            isEmailValid = false;
        } else  {
            isEmailValid = true;

        }

        // Check for a valid phone number.
        if (phoneNumber.getText().toString().isEmpty()) {
            phoneNumber.setError("Phone Number should not be empty");
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
        }


        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            password.setError("Password should not be empty");
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError("Password is invalid");
            isPasswordValid = false;
        }
        else if(confirmpassword.getText().toString().isEmpty()) {
            confirmpassword.setError("Password should not be empty");
        }
        else if(!confirmpassword.getText().toString().equals(password.getText().toString())){
            confirmpassword.setError("Password and Confirm Password should be same");
        }
        else {
            isPasswordValid = true;
        }


        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            //Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            Intent activity = new Intent(SignUp.this, Login.class);
            startActivity(activity);
            AddtoDatabase();
        }

    }


    public void AddtoDatabase(){
        String dname = name.getText().toString().trim();
        String demail = email.getText().toString().trim();
        String dphone = phoneNumber.getText().toString().trim();
        String dpassword = password.getText().toString().trim();

        String id = database.push().getKey();
        UserClass user = new UserClass(id,dname,demail,dphone,dpassword);
        database.child(dname).setValue(user);
        Toast.makeText(this,"User added",Toast.LENGTH_SHORT).show();

    }
}
