package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.spotify.databinding.FragmentLlistaCansonsBinding;
import com.example.spotify.databinding.FragmentMyMusicBinding;

import java.util.ArrayList;
import java.util.List;

import Adapters.Song_Adapter;
import model.Album;
import model.Song;


public class llista_cansons extends Fragment {

    FragmentLlistaCansonsBinding b;


    Song_Adapter adapter;

     public static Album entrada;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentLlistaCansonsBinding.inflate(getLayoutInflater());
        View v = b.getRoot();

        setUpAlbumInfo();

        setUpAnimations();

        setUpRecycleViewCansons();

        setUpFloatingButton();



        return v;
    }

    private void setUpFloatingButton() {

        b.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song_Creation_CustomDialog customDialog = new Song_Creation_CustomDialog();
                customDialog.show(getFragmentManager(), "CustomDialogFragment");
            }
        });

    }

    private void setUpAnimations() {

        Animation slideUp = AnimationUtils.loadAnimation(this.getContext(), R.anim.floating_button_anim);
        b.fab.startAnimation(slideUp);



    }

    private void setUpAlbumInfo() {
        b.txvNameArtist.setText(entrada.getAuthor().toString());

        b.txvAlbumName.setText(entrada.getName().toString());

        b.imgAlbum.setImageBitmap(entrada.getBitmap());


    }

    private void setUpRecycleViewCansons() {


        //Prov album amb canço
        List<Song> prova = new ArrayList<>();
        prova.add(new Song(0,true,"Hello","4:55"));
        prova.add(new Song(1,true,"Si no Estás","3:04"));
        entrada.setConsons_Album(prova);


        if(entrada.getConsons_Album()!=null){
            b.cardAvis.setVisibility(View.GONE);
            b.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,false));
            adapter=new Song_Adapter(entrada.getConsons_Album(),this.getContext());
            b.recyclerView.setAdapter(adapter);



        }else{
            b.cardAvis.setVisibility(View.VISIBLE);
        }




    }
}