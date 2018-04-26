package com.example.bicher.ds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NutritionActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        recyclerView = findViewById(R.id.rvNutrition);
        String sharedListForCahs = mySharedPreferences.getString("LIST_FOR_CASH_NUTRITION", null);
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
                Intent intent = new Intent(NutritionActivity.this, TrainingInfoActivity.class);
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
                rationList.clear();

                for (DataSnapshot dataSnapshots: dataSnapshot.child("Nutrition").getChildren()) {
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
            rationList.add(fireBaseListMain.get(i).child("ration").getValue(g));
        }

        for (int i = 0; i < fireBaseListMain.size(); i++) {
            listForCash.add(new TrainingProgram(
                nameList.get(i),
                0,
                imageUrlList.get(i),
                youtubeUriList.get(i),
                rationList.get(i)));
        }

        String jsonListForCash = gson.toJson(listForCash);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //editor.clear();
        editor
            .putString("LIST_FOR_CASH_NUTRITION", jsonListForCash)
            .apply();

        adapter.setTrainingProgramList(listForCash);
    }
}
