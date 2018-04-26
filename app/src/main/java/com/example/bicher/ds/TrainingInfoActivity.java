package com.example.bicher.ds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrainingInfoActivity extends AppCompatActivity {

    private TextView tvTrainingName;
    private ImageView imYoutube;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private List<String> exerciseList;
    private ListView lv;

    private RecyclerView recyclerView;
    CustomRVInfoAdapter customRVInfoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_info);

        tvTrainingName = findViewById(R.id.tvTrainingName);
        imYoutube = findViewById(R.id.imYoutube);
        //lv = findViewById(R.id.lv);
        recyclerView = findViewById(R.id.rvInfo);
        customRVInfoAdapter = new CustomRVInfoAdapter();





        final TrainingProgram program = getIntent().getParcelableExtra("TR_PROGRAM");
        tvTrainingName.setText(program.getName());
        exerciseList = program.getProgramList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(customRVInfoAdapter);
        customRVInfoAdapter.setStringList(exerciseList);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, exerciseList);
        //lv.setAdapter(arrayAdapter);

        String s =  "http://img.youtube.com/vi/";
        String youtubeVideoUri = program.getVideoUri();
        Log.wtf("TrainingInfoActivity", youtubeVideoUri);
        String s1 = youtubeVideoUri.substring(youtubeVideoUri.length() - 11);
        Log.wtf("TrainingInfoActivity", s1);


        Picasso.with(this)
            .load(s + s1 +"/mqdefault.jpg")
            .placeholder(R.drawable.ic_launcher_background)
            .into(imYoutube);

        imYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(program.getVideoUri());
                intent.setData(uri);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }
}
