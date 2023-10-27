package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotify.databinding.FragmentDeleteAlbumCustomDialogBinding;
import com.example.spotify.databinding.FragmentSongCreationCustomDialogBinding;


public class Song_Creation_CustomDialog extends DialogFragment {


    FragmentSongCreationCustomDialogBinding b;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        b = FragmentSongCreationCustomDialogBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        

        setUpNumberPicker();

        return v;

    }

    private void setUpNumberPicker() {

        b.numberPickerMinutos.setMaxValue(59);
        b.numberPickerMinutos.setMinValue(0);
        b.numberPickerMinutos.setValue(0);

        b.numberPickerSegundos.setMaxValue(59);
        b.numberPickerSegundos.setMinValue(0);
        b.numberPickerSegundos.setValue(0);


    }
}