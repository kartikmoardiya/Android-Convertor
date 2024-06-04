package com.example.converter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> scaleList = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ActivityMainBinding binding;
    String first = "", second = "";
    float answer = 1.5F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        scaleList.add("Select Here");
        scaleList.add("Inch");
        scaleList.add("Centimeter");
        scaleList.add("Feet");
        scaleList.add("Meter");
        scaleList.add("Millimeter");

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, scaleList);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, scaleList);

        binding.spinnerOne.setAdapter(adapter1);
        binding.spinnerTwo.setAdapter(adapter2);

        binding.spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                first = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                second = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        scaleList.add("Inch");
//        scaleList.add("Centimeter");
//        scaleList.add("Feet");
//        scaleList.add("Meter");
//        scaleList.add("Millimeter");
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((first.equalsIgnoreCase("Select Here") && second.equalsIgnoreCase("Select Here")) || (first != null && second != null)) {
                    if(binding.edt.getText().toString()!=null) {
                        if (first.equalsIgnoreCase("Inch")) {
                            answer = (float) (Float.parseFloat(binding.edt.getText().toString()) * 0.0254);
                        } else if (first.equalsIgnoreCase("Centimeter")) {
                            answer = (float) (Float.parseFloat(binding.edt.getText().toString()) * 0.01);
                        } else if (first.equalsIgnoreCase("Feet")) {
                            answer = (float) (Float.parseFloat(binding.edt.getText().toString()) * 0.3048);
                        } else if (first.equalsIgnoreCase("Meter")) {
                            answer = (float) (Float.parseFloat(binding.edt.getText().toString()) * 1);
                        } else if (first.equalsIgnoreCase("Millimeter")) {
                            answer = (float) (Float.parseFloat(binding.edt.getText().toString()) * 0.001);
                        }
                        if (second.equalsIgnoreCase("Inch")) {
                            answer *= 39.37007874;
                        } else if (second.equalsIgnoreCase("Centimeter")) {
                            answer *= 100;
                        } else if (second.equalsIgnoreCase("Feet")) {
                            answer *= 3.2808399;
                        } else if (second.equalsIgnoreCase("Meter")) {
                            answer *= 1;
                        } else if (second.equalsIgnoreCase("Millimeter")) {
                            answer *= 1000;
                        }

                        binding.txt.setText("" + answer);
                    }else{
                        Toast.makeText(MainActivity.this, "Ala Bhala Mana Value Nakhta Tu Bhuli Gyo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}