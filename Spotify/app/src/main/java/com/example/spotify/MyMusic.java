package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.spotify.databinding.FragmentMyMusicBinding;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import Adapters.Album_Adapter;
import model.Album;


public class MyMusic extends Fragment {


   FragmentMyMusicBinding b;

   Album_Adapter adapter;

    public static long imagenumber=0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentMyMusicBinding.inflate(getLayoutInflater());
        View v = b.getRoot();


        setupUniversalImageLoader();

        setUpRecycleViewAlbums();

        setUpAnimations();

        setUpFloatingButton();


        return v;
    }

    private void setUpFloatingButton() {

        b.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_album_Creation);
            }
        });

    }

    private void setUpAnimations() {
        Animation slideUp = AnimationUtils.loadAnimation(this.getContext(), R.anim.floating_button_anim);
        b.fab.startAnimation(slideUp);

    }


    private void setupUniversalImageLoader() {
        DisplayImageOptions dop = new DisplayImageOptions.Builder().
                showImageOnLoading(R.drawable.not_found)
                .build();
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(this.getContext())
                        //.threadPoolSize(10)
                        //.diskCacheSize(1000)
                        .defaultDisplayImageOptions(dop)
                        .build();

        ImageLoader.getInstance().init(config);
    }

    private void setUpRecycleViewAlbums() {

        int numberOfColumns = 2;


        b.rcyAlbums.setLayoutManager(new GridLayoutManager(
                this.getContext(),numberOfColumns,
                LinearLayoutManager.VERTICAL,
                false));


        adapter= new Album_Adapter(Album.list_albums,this.getContext());
        b.rcyAlbums.setAdapter(adapter);


    }





}