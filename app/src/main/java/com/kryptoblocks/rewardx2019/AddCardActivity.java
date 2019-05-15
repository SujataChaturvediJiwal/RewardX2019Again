package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner cardMonth_spinner;
    String[] cardMonthNumbers={"1","2","3","4","5","6","7","8","9","10","11","12"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        cardMonth_spinner = findViewById(R.id.cardSpinner_month);

        cardMonth_spinner.setOnItemSelectedListener(this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter monthArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cardMonthNumbers);
        monthArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        cardMonth_spinner.setAdapter(monthArrayAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

