package com.example.hms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance(String param1, String param2) {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Activity resource;
    View parent;
    Button login, register;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText usernameField,passwordFiled;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        resource = getActivity();
        parent = inflater.inflate(R.layout.fragment_doctor, container, false);
        login = parent.findViewById(R.id.patientLoginBtnID);
        register = parent.findViewById(R.id.patientRegisterBtnID);
        usernameField=parent.findViewById(R.id.patientLoginUsernameID);
        passwordFiled=parent.findViewById(R.id.patientLoginPasswordID);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Doctor");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=usernameField.getText().toString().trim();
                String password=passwordFiled.getText().toString().trim();

                if(username.isEmpty()){
                    usernameField.setError("Insert username");

                }
                else if(password.isEmpty()){
                    passwordFiled.setError("Insert Password");
                }
                else{
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if(dataSnapshot.hasChild(username)){

                                String passFromFirebase = dataSnapshot.child(username).child("password").getValue(String.class);

                                if(passFromFirebase.equals(password)){
                                    Intent patientHome=new Intent(resource,DoctorHome.class);
                                    patientHome.putExtra("Current Doctor",username);
                                    startActivity(patientHome);


                                }
                                else{
                                    passwordFiled.setError("Password and username didn't match");
                                    passwordFiled.requestFocus();
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

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientRegis=new Intent(resource,DoctorSignUp.class);
                startActivity(patientRegis);

            }
        });

        return parent;
    }

}