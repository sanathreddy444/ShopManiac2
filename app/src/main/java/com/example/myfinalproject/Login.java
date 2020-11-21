package com.example.myfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends Activity {
    Boolean isEmailValid = true;
    Boolean isPasswordValid = true;
    Boolean isUserValid = true;
    DatabaseReference databaseReference;
    EditText email;
    EditText password;
    Button signuppagebutton,loginbutton;

    public void setValidation(){
        if (isEmailValid && isPasswordValid) {
            final String emailEntered = email.getText().toString().trim();
            final String passwordEntered = password.getText().toString().trim();
            databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(!email.getText().toString().trim().isEmpty()){
                        if(!password.getText().toString().trim().isEmpty()){
                            if (dataSnapshot.hasChildren()) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    UserClass credentials = child.getValue(UserClass.class);
                                    System.out.println(" *******credentials*******" + credentials.getEmail());
                                    String demail = credentials.getEmail();
                                    String dpassword = credentials.getPassword();
                                    if (demail.equals(emailEntered)) {
                                        System.out.println("email matched");
                                        if (dpassword.equals(passwordEntered)) {
                                            System.out.println("password matched");
                                            isUserValid = true;
                                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                            startActivity(intent);
                                        }else{
                                            password.setError("Invalid Password");
                                        }
                                    }
                                }
                            }
                        }else {
                            password.setError("Password should not be empty");
                        }
                    }else {
                        email.setError("Email should not be empty");
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
        if(!isUserValid)
        {
            Toast.makeText(this, "Username or password invalid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.txt_login_email);
        password = findViewById(R.id.txt_login_password);
        signuppagebutton = findViewById(R.id.btn_signup_page);
        loginbutton = findViewById(R.id.btn_login);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                setValidation();
            }
        });

        signuppagebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }

}
