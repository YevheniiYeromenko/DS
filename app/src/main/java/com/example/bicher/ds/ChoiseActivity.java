package com.example.bicher.ds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiseActivity extends AppCompatActivity {

    private Button bTraining;
    private Button bNutrition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);
        bTraining = findViewById(R.id.bTraining);
        bNutrition = findViewById(R.id.bNutrition);

        bTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiseActivity.this, TrainingActivity.class);
                startActivity(intent);
            }
        });

        bTraining.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.youtube.com/channel/UCnEEkiWWRG5mfgEN3UE7Rjg");
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return false;
            }
        });

        bNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiseActivity.this, NutritionActivity.class);
                startActivity(intent);
            }
        });

        bNutrition.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.youtube.com/channel/UCvPFKUF4h6NdZqfisK4wD3Q");
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return false;
            }
        });

    }
}
