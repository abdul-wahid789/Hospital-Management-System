package com.example.hms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] loginType={"Patient","Doctor","Receptionist","Admin"};
    Spinner loginSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter loginTypeAdapter=new ArrayAdapter(this,R.layout.spinner_adapter,loginType);
        loginTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        loginSpinner=findViewById(R.id.loginSpinnerID);
        loginSpinner.setAdapter(loginTypeAdapter);
        loginSpinner.setOnItemSelectedListener(this);

    }
    Fragment fragment;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String loginType=parent.getItemAtPosition(position).toString();

       if(loginType.equals("Patient")){
           fragment=new PatientFragment();
       }
       else if(loginType.equals("Doctor")){
           fragment=new DoctorFragment();
       }
       else if(loginType.equals("Admin")){
           fragment=new AdminFragment();
       }
       else{
           fragment=new ReceptionistFragment();
       }
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.blankFragmentID,fragment);
        ft.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}