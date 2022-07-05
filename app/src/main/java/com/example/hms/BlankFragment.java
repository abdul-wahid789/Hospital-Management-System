package com.example.hms;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BlankFragment extends Fragment {
    View parent;
    Activity reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        reference=getActivity();
        parent= inflater.inflate(R.layout.fragment_blank, container, false);
        return parent;
    }
}