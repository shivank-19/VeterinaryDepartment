package com.e.veterinarydepartment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout username , password;
    private Button loginbutton;
    private FirebaseAuth mAuth;
    private String s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();

        username= findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.buttonlogin);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = username.getEditText().getText().toString();
                s2 = password.getEditText().getText().toString();

                if (s1.equals("") || s2.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();

                } else {
                    mAuth.signInWithEmailAndPassword(s1, s2)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        // Log.d(TAG, "signInWithEmail:success");
                                        // FirebaseUser user = mAuth.getCurrentUser();
                                        //   updateUI(user);

                                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                        startActivity(intent);
                                        finish();
                                      //  Toast.makeText(MainActivity.this, "Authentication",
                                      //          Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //   updateUI(null);
                                    }
                                    // ...
                                }
                            });
                }
            }
        });
    }
}
