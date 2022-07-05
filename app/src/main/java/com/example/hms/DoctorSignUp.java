package com.example.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorSignUp extends AppCompatActivity {
    EditText usernameField,passwordField,fullNameField,emailField,ageField;
    RadioGroup genderGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        usernameField=findViewById(R.id.usernameID);
        passwordField=findViewById(R.id.passwordID);
        fullNameField=findViewById(R.id.fullNameID);
        emailField=findViewById(R.id.emailID);
        genderGroup=findViewById(R.id.genderGroupID);
        ageField=findViewById(R.id.ageID);
    }
    String gender= null;

    public void signUp(View view) {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String fullName = fullNameField.getText().toString().trim();
        String email= emailField.getText().toString().trim();
        String ageStr= ageField.getText().toString().trim();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Doctor");
        if(genderGroup.getCheckedRadioButtonId()==R.id.maleRaidoID){
            gender="Male";
        }
        else if(genderGroup.getCheckedRadioButtonId()==R.id.femaleRaidoID){
            gender="Female";
        }
        if(username.isEmpty()||password.isEmpty()||fullName.isEmpty()||email.isEmpty()||gender==null||ageStr.isEmpty()){
            Toast.makeText(this, "Data field empty", Toast.LENGTH_SHORT).show();
        }
        else{
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.hasChild(username)){

                        usernameField.setError("Use Another username");

                    }
                    else{

                        Patient patientObj=new Patient(username,password,fullName,email,ageStr,gender);
                        myRef.child(username).setValue(patientObj);
                        Intent logIn=new Intent(DoctorSignUp.this,MainActivity.class);
                        logIn.putExtra("User Patient",username);
                        startActivity(logIn);
                    }


                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }
    }
    public void BackBtn(View view) {
    }
}