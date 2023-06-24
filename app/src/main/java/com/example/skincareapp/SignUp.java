package com.example.skincareapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class SignUp extends AppCompatActivity {
        Button btn_signup, btn_login, btn_noAcc;
        EditText user_name, pass_word;
        FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sign_up);

            user_name = findViewById(R.id.username);
            pass_word = findViewById(R.id.password);
            btn_signup = findViewById(R.id.signUp_btn);
            btn_login = findViewById(R.id.login_btn);
            btn_noAcc = findViewById(R.id.noAcc_btn);

            mAuth = FirebaseAuth.getInstance();

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            });

            btn_noAcc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            btn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = user_name.getText().toString().trim();
                    String password= pass_word.getText().toString().trim();
                    if(email.isEmpty())
                    {
                        user_name.setError("Email is empty");
                        user_name.requestFocus();
                        return;
                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    {
                        user_name.setError("Enter the valid email address");
                        user_name.requestFocus();
                        return;
                    }
                    if(password.isEmpty())
                    {
                        pass_word.setError("Enter the password");
                        pass_word.requestFocus();
                        return;
                    }
                    if(password.length()<6)
                    {
                        pass_word.setError("Length of the password should be more than 6");
                        pass_word.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(SignUp.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }
