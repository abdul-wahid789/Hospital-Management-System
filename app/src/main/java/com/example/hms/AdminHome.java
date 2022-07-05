package com.example.hms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {
    DatabaseReference database;
    ArrayList<Patient> patientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void patientData(View view) {
        Intent patient=new Intent(this,PatientData.class);
        startActivity(patient);

    }

    public void receptionistData(View view) {
        Intent patient=new Intent(this,ReceptionistData.class);
        startActivity(patient);
    }

    public void doctorData(View view) {
        Intent patient=new Intent(this,DoctorData.class);
        startActivity(patient);

    }
}