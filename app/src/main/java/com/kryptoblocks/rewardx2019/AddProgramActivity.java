package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddProgramActivity extends AppCompatActivity {


    Button save_add_program;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);

        save_add_program = findViewById(R.id.save_add_program_button);

        save_add_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),ProfileActivity.class));
            }
        });
    }
}
