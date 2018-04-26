package com.example.bicher.ds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TrainingActivity extends BaseActivity {

//    private RecyclerView recyclerView;
//    private CustomAdapter adapter;
//    private SharedPreferences mySharedPreferences;


//    private String [] imageUrl = {"https://www.kinonews.ru/insimgs/2017/newsimg/newsimg73351.jpg",
//    "https://www.kinonews.ru/insimgs/newsimg/newsimg16681.jpg",
//    "https://sportarius.ru/wp-content/uploads/2017/03/mike-o-hearn-buy-at-american-supps.jpg",
//    "https://i.ytimg.com/vi/QX2v3cn25WU/hqdefault.jpg",
//    "http://www.buildbody.org.ua/wp-content/uploads/2015/06/ct-fletcher.jpg",
//    "http://4rama.com/attachments/bjrdzybdnue-jpg.2171/",
//    "http://www.peoples.ru/sport/bodybilding/sergio_oliva/oliva_478.jpg",
//    "https://i.ytimg.com/vi/44cqnifQ76g/hqdefault.jpg",
//    "https://fightnews.info/img/pages/26643_resize(602-602-4).jpg"};

    private List<TrainingProgram> listForCash = new ArrayList<>();

//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference myRef = database.getReference();
//    private List<DataSnapshot> fireBaseListMain = new ArrayList<>();
//    private List<String> nameList = new ArrayList<>();
//    private List<String> imageUrlList = new ArrayList<>();
//    private List<String> youtubeUriList = new ArrayList<>();
//    private List<List<String>> exerciseList = new ArrayList<>();
//    private Gson gson = new Gson();


    Type listTypeOfTrainingProgram = new TypeToken<List<TrainingProgram>>(){}.getType();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //adapter = new CustomAdapter();
        recyclerView = findViewById(R.id.rvTraining);
        //mySharedPreferences = getSharedPreferences("com.example.bicher.ds", Context.MODE_PRIVATE);
        String sharedListForCahs = mySharedPreferences.getString("LIST_FOR_CASH", null);
        //**GenericTypeIndicator<List<DataSnapshot>> gSnapshot = new GenericTypeIndicator<List<DataSnapshot>>() {};

        if (sharedListForCahs != null) {
            listForCash = gson.fromJson(sharedListForCahs, listTypeOfTrainingProgram);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setTrainingProgramList(listForCash);

        fireBaseListener();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //Toast.makeText(TrainingActivity.this, listForNewData.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrainingActivity.this, TrainingInfoActivity.class);
                intent.putExtra("TR_PROGRAM", listForCash.get(position));
                startActivity(intent);
            }
        });
    }

    private void fireBaseListener(){
        myRef.orderByValue();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listForCash.clear();
                fireBaseListMain.clear();
                mySharedPreferences.edit().clear();
                nameList.clear();
                imageUrlList.clear();
                youtubeUriList.clear();
                exerciseList.clear();

                for (DataSnapshot dataSnapshots: dataSnapshot.child("Training").getChildren()) {
                    fireBaseListMain.add(dataSnapshots);
                }
                setOfTranningProgramsFields();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.wtf("FireBaseData", "Failed to read value.", error.toException());
            }
        });
    }

    private void setOfTranningProgramsFields(){
        GenericTypeIndicator<List<String>> g = new GenericTypeIndicator<List<String>>() {};
        for (int i = 0; i < fireBaseListMain.size(); i++) {
            //Log.wtf("FIREBASE TRAINING ACTIVITY LIST", fireBaseListMain.get(i).getValue().toString());
            nameList.add(fireBaseListMain.get(i).child("name").getValue(String.class));
            imageUrlList.add(fireBaseListMain.get(i).child("imageUrl").getValue(String.class));
            youtubeUriList.add(fireBaseListMain.get(i).child("youtubeUri").getValue(String.class));
            exerciseList.add(fireBaseListMain.get(i).child("exercise").getValue(g));
        }

        for (int i = 0; i < fireBaseListMain.size(); i++) {
            listForCash.add(new TrainingProgram(
                nameList.get(i),
                3,
                imageUrlList.get(i),
                youtubeUriList.get(i),
                exerciseList.get(i)));
        }

        String jsonListForCash = gson.toJson(listForCash);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //editor.clear();
        editor
            .putString("LIST_FOR_CASH", jsonListForCash)
            .apply();

        adapter.setTrainingProgramList(listForCash);
    }

}
