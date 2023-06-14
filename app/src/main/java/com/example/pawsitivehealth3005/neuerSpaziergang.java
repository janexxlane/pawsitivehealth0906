package com.example.pawsitivehealth3005;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class neuerSpaziergang extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private int stepCount = 0;
    private TextView stepCountTextView;
    private boolean isCounterSensorPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuer_spaziergang);


        Button start = findViewById(R.id.trackerButton);
        start.setOnClickListener(view -> {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            stepCountTextView = findViewById(R.id.trackerAnzeige);

            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            if (sensorManager != null) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
                    stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    isCounterSensorPresent = true;
                    sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
                    System.out.println("Counting...");
                } else {
                    stepCountTextView.setText("Kein Schrittzähler gefunden");
                    isCounterSensorPresent = false;
                }
            } else {
                Toast.makeText(getApplicationContext(), "SensorManager nicht verfügbar", Toast.LENGTH_SHORT).show();
                isCounterSensorPresent = false;
            }
        });

        Button end = findViewById(R.id.endTrack);
        end.setOnClickListener(view -> {
            stepCount = 0;
            stepCountTextView.setText(String.valueOf(stepCount));
        });

        Button zurückButton = findViewById(R.id.backButton);
        zurückButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(neuerSpaziergang.this, Datum_heute.class);
                startActivity(myintent);
            }
        });

        Button speichern=findViewById(R.id.saveButton);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textViewName=findViewById(R.id.nameSpaz);
                String nameString = textViewName.getText().toString();
                CheckBox happyBoolean=findViewById(R.id.happy);
                boolean isCheckedhappy = happyBoolean.isChecked();
                CheckBox mediumBoolean=findViewById(R.id.medium);
                boolean isCheckedmedium = mediumBoolean.isChecked();
                CheckBox sadBoolean=findViewById(R.id.sad);
                boolean isCheckedsad = sadBoolean.isChecked();

                Intent myintent = new Intent(neuerSpaziergang.this, Datum_heute.class);
                myintent.putExtra("spaziergangName", nameString);
                myintent.putExtra("stepCount", stepCount);
                myintent.putExtra("happy",isCheckedhappy);
                myintent.putExtra("medium",isCheckedmedium);
                myintent.putExtra("sad",isCheckedsad);
                startActivity(myintent);
            }});


    }



    @Override
    protected void onResume() {
        super.onResume();
        if (isCounterSensorPresent && sensorManager != null && stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isCounterSensorPresent && sensorManager != null && stepCounterSensor != null) {
            sensorManager.unregisterListener(this, stepCounterSensor);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == stepCounterSensor) {
            stepCount = (int)sensorEvent.values[0];
            stepCountTextView.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Not used in this implementation
    }
}
