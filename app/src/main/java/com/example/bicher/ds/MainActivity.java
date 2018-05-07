package com.example.bicher.ds;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    /*private FirebaseAuth mAuth;
    private EditText etEmail;
    private EditText etPassword;
    private Button bAuthorization;
    private Button bRegistration;
    private Button bSignOut;*/

    //private boolean start = false;
    //private MyAsyncTask asyncTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new MyAsyncTask().execute();

        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), ChoiseActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(1000);
//                Intent intent = new Intent(getApplicationContext(), ChoiseActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        }).start();


        //Intent intent = new Intent(getApplicationContext(), ChoiseActivity.class);
        //if (start) {
            //SystemClock.sleep(2000);
            //startActivity(intent);
        //}
        //Переход на окно выбора

        ////////////////////

       /* etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bAuthorization = findViewById(R.id.bAuthorization);
        bRegistration = findViewById(R.id.bRegistration);
        bSignOut = findViewById(R.id.bSignOut);

        mAuth = FirebaseAuth.getInstance();

        bAuthorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signning(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        bRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        bSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                //FirebaseUser user = mAuth.getCurrentUser();
                //Log.wtf("Sign Out", user.getDisplayName() + "");
                //Log.wtf("Sign Out", user.getEmail() + "");
            }
        });
    }

    public void signning(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "registration:success", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "registration:failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
    }

    }

