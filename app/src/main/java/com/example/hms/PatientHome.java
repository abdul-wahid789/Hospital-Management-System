package com.example.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientHome extends AppCompatActivity {
    TextView usernameField;
    EditText passwordField,fullNameField,emailField,ageField;
    RadioGroup genderGroup;
    RadioButton maleRadio,femaleRadio;
    FirebaseDatabase database;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        usernameField=findViewById(R.id.textViewPatientHomeID);
        passwordField=findViewById(R.id.pHomepasswordID);
        fullNameField=findViewById(R.id.pHomefullNameID);
        emailField=findViewById(R.id.pHomeemailID);
        genderGroup=findViewById(R.id.pHomegenderGroupID);
        maleRadio=findViewById(R.id.pHomemaleRaidoID);
        femaleRadio=findViewById(R.id.pHomefemaleRaidoID);
        ageField=findViewById(R.id.pHomeageID);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Patient");

        username= getIntent().getStringExtra("Current Patient");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(username)){

                    Patient user = dataSnapshot.child(username).getValue(Patient.class);
                    usernameField.setText(user.getUsername());
                    passwordField.setText(user.getPassword());
                    fullNameField.setText(user.getFullName());
                    emailField.setText(user.getEmail());
                    ageField.setText(user.getAge());
                    String gender=user.getGender();
                    if(gender.equals("Male")){
                        maleRadio.setChecked(true);
                    }
                    else{
                        femaleRadio.setChecked(true);
                    }

                }
                else{
                    usernameField.setError("User not found");

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



    }

    public void updateBtn(View view) {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String fullName = fullNameField.getText().toString().trim();
        String email= emailField.getText().toString().trim();
        String ageStr= ageField.getText().toString().trim();
        String gender= null;
        if(genderGroup.getCheckedRadioButtonId()==R.id.pHomemaleRaidoID){
            gender="Male";
        }
        else if(genderGroup.getCheckedRadioButtonId()==R.id.pHomefemaleRaidoID){
            gender="Female";
        }


        if(username.isEmpty()||password.isEmpty()||fullName.isEmpty()||email.isEmpty()||gender==null||ageStr.isEmpty()){
            Toast.makeText(this, "Data field empty", Toast.LENGTH_SHORT).show();
            Log.d("TAG", username+password+fullName+email+ageStr+gender);
        }
        else{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Patient");
            Patient patientObj=new Patient(username,password,fullName,email,ageStr,gender);
            myRef.child(username).setValue(patientObj);
            Intent patientHome=new Intent(this,PatientHome.class);
            patientHome.putExtra("Current Patient",username);
            finish();
            startActivity(patientHome);

        }
    }

    public void logoutBtn(View view) {
        Intent lgOut=new Intent(this,MainActivity.class);
        finish();
        startActivity(lgOut);
    }
}