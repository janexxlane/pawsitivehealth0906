package com.example.pawsitivehealth3005;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsitivehealth3005.Database.ArztpraxisDAO;
import com.example.pawsitivehealth3005.Database.ArztpraxisDatabase;
import com.example.pawsitivehealth3005.Database.ArztpraxisEntity;
import com.example.pawsitivehealth3005.Database.MedikamenteDAO;
import com.example.pawsitivehealth3005.Database.MedikamenteDatabase;
import com.example.pawsitivehealth3005.Database.MedikamenteEntity;
import com.example.pawsitivehealth3005.Database.SpaziergängeDAO;
import com.example.pawsitivehealth3005.Database.SpaziergängeDatabase;
import com.example.pawsitivehealth3005.Database.SpaziergängeEntity;

import java.util.List;

public class Log extends AppCompatActivity {
    private static final String TAG = "LogArztpraxis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_log_arzttermin);

        TextView logTextView = findViewById(R.id.logAnzeige);

        //////Button der LOG von Arztterminen aufruft
        Button btnArzt = findViewById(R.id.button_Arzt);
        btnArzt.setOnClickListener(view -> {

            printDatabaseContentsArzt(this, logTextView);
        });

        //////Button der LOG von Medikamenten aufruft
        Button btnMedi = findViewById(R.id.button_Medi);
        btnMedi.setOnClickListener(view -> {

           printDatabaseContentsMedis(this, logTextView);
        });

        //////Button der LOG von Spaziergänge aufruft
        Button btnSpaz = findViewById(R.id.button_spaz);
        btnSpaz.setOnClickListener(view -> {

             printDatabaseContentsSpaziergang(this, logTextView);
        });

        //////Button der LOG von Ernährung aufruft
        Button btnErnäh = findViewById(R.id.button_ernährung);
        btnErnäh.setOnClickListener(view -> {

            //  printDatabaseContentsDiet(this, logTextView);
        });

    }
    /////////// Methode zum Abrufen und Anzeigen der ARZTTERMINE Inhalte
    public void printDatabaseContentsArzt(Context context, TextView textView) {
        ArztpraxisDatabase database = ArztpraxisDatabase.getInstance(context);
        ArztpraxisDAO dao = database.arztpraxisDAO();

        // Datenbankzugriff in einem Hintergrund-Thread durchführen
        new AsyncTask<Void, Void, List<ArztpraxisEntity>>() {
            @Override
            protected List<ArztpraxisEntity> doInBackground(Void... voids) {
                return dao.getArztpraxis();
            }

            @Override
            protected void onPostExecute(List<ArztpraxisEntity> entities) {
                if (entities != null && !entities.isEmpty()) {
                    StringBuilder logData = new StringBuilder();


                    for (ArztpraxisEntity entity : entities) {
                        logData.append("ID: ").append(entity.getId()).append("\n")
                                .append("Diagnose: ").append(entity.getDiagnoseString()).append("\n")
                                .append("Arztname: ").append(entity.getArztNameString()).append("\n")
                                .append("Arzt Telefonnnummer: ").append(entity.getArztNummerString()).append("\n\n");
                    }

                    // Log-Daten in TextView setzen
                    textView.setText(logData.toString());
                }
            }
        }.execute();
    }




    ///////Aufruf von Log MEDIKAMENT

    public void printDatabaseContentsMedis(Context context, TextView textView2) {
        MedikamenteDatabase database = MedikamenteDatabase.getInstance(context);
        MedikamenteDAO dao = database.medikamenteDAO();

        // Datenbankzugriff in einem Hintergrund-Thread durchführen
        new AsyncTask<Void, Void, List<MedikamenteEntity>>() {
            @Override
            protected List<MedikamenteEntity> doInBackground(Void... voids) {
                return dao.getMedikamente();
            }

            @Override
            protected void onPostExecute(List<MedikamenteEntity> entities) {
                if (entities != null && !entities.isEmpty()) {
                    StringBuilder logData = new StringBuilder();


                    for (MedikamenteEntity entity : entities) {
                        logData.append("ID: ").append(entity.getId()).append("\n")
                                .append("Medikamentenname: ").append(entity.getMediNameString()).append("\n")
                                .append("Mg Angabe: ").append(entity.getMgAngabe()).append("\n")
                                .append("morgens: ").append(entity.getIsChecked()).append("\n")
                                .append("mittags: ").append(entity.getIsChecked2()).append("\n")
                                .append("abends: ").append(entity.getIsChecked3()).append("\n\n");
                    }

                    // Log-Daten in TextView setzen
                    textView2.setText(logData.toString());
                }
            }
        }.execute();
    }


    ///////Aufruf von Log SPAZIERGANG


    public void printDatabaseContentsSpaziergang(Context context, TextView textView2) {
        SpaziergängeDatabase database = SpaziergängeDatabase.getInstance(context);
        SpaziergängeDAO dao = database.spaziergängeDAO();

        // Datenbankzugriff in einem Hintergrund-Thread durchführen
        new AsyncTask<Void, Void, List<SpaziergängeEntity>>() {
            @Override
            protected List<SpaziergängeEntity> doInBackground(Void... voids) {
                return dao.getSpaziergänge();
            }

            @Override
            protected void onPostExecute(List<SpaziergängeEntity> entities) {
                if (entities != null && !entities.isEmpty()) {
                    StringBuilder logData = new StringBuilder();


                    for (SpaziergängeEntity entity : entities) {
                        logData.append("ID: ").append(entity.getId()).append("\n")
                                .append("Routennamen: ").append(entity.getSpaziergangName()).append("\n")
                                .append("Schritte: ").append(entity.getStepCount()).append("\n")
                                .append("Mood happy: ").append(entity.isHappy()).append("\n")
                                .append("Mood neutral: ").append(entity.isMedium()).append("\n")
                                .append("Mood sad: ").append(entity.isSad()).append("\n\n");
                    }

                    // Log-Daten in TextView setzen
                    textView2.setText(logData.toString());
                }
            }
        }.execute();
    }
}


