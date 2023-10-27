package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotify.databinding.FragmentCustomDialogImagePickerBinding;
import com.example.spotify.databinding.FragmentDeleteAlbumCustomDialogBinding;

import model.Album;


public class delete_album_custom_dialog extends DialogFragment {
    
    
    FragmentDeleteAlbumCustomDialogBinding b;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentDeleteAlbumCustomDialogBinding.inflate(getLayoutInflater());
        View v = b.getRoot();

        
        setUpButtons();
        

        return v;
        
    }

    private void setUpButtons() {

        b.btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMusic.deleteSelectedItems();

                delete_album_custom_dialog.this.dismiss();
            }
        });


        b.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_album_custom_dialog.this.dismiss();
            }
        });

    }
}