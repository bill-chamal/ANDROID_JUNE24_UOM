package com.example.uom_june24;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner sp;
    StudentList stdL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = findViewById(R.id.namesList);

        stdL = new StudentList(getAssets());

        ArrayList<String> names = stdL.getListNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

    }

    public void onClickButton(View view){
        String selectedName = sp.getSelectedItem().toString();
        String av = stdL.findAverage(selectedName);
        // Show toast Average grade
        if (av.equals("0.0"))
            Toast.makeText(getApplicationContext(), "He didn't pass any lesson (AV=0) ", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Average grade : " +  av, Toast.LENGTH_LONG).show();
    }
}