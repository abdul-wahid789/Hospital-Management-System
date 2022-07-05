package com.example.hms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;

    ArrayList<Patient> list;

    public Adapter(Context context, ArrayList<Patient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Patient aPatient = list.get(position);
        holder.username.setText(aPatient.getUsername());
        holder.password.setText(aPatient.getPassword());
        holder.fullname.setText(aPatient.getFullName());
        holder.email.setText(aPatient.getEmail());
        holder.age.setText(aPatient.getAge());
        holder.gender.setText(aPatient.getGender());


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username, password, fullname,email,age,gender;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.TextViewUserNameID);
            password = itemView.findViewById(R.id.TextViewPasswordID);
            fullname = itemView.findViewById(R.id.TextViewFullNameID);
            email = itemView.findViewById(R.id.TextViewEmailID);
            age = itemView.findViewById(R.id.TextViewAgeID);
            gender = itemView.findViewById(R.id.TextViewGenderID);

        }
    }
}
