package com.example.bicher.ds;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public CustomAdapter adapter;
    public SharedPreferences mySharedPreferences;

    public List<TrainingProgram> listForCash = new ArrayList<>();

    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference();
    public List<DataSnapshot> fireBaseListMain = new ArrayList<>();
    public List<String> nameList = new ArrayList<>();
    public List<String> imageUrlList = new ArrayList<>();
    public List<String> youtubeUriList = new ArrayList<>();
    public List<List<String>> exerciseList = new ArrayList<>();
    public List<List<String>> rationList = new ArrayList<>();
    public Gson gson = new Gson();


    Type listTypeOfTrainingProgram = new TypeToken<List<TrainingProgram>>(){}.getType();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CustomAdapter();
        //recyclerView = findViewById(R.id.rvTraining);
        mySharedPreferences = getSharedPreferences("com.example.bicher.ds", Context.MODE_PRIVATE);

    }
}
