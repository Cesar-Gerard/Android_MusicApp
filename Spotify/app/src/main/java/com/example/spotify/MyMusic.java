package com.example.spotify;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.spotify.databinding.FragmentMyMusicBinding;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import Adapters.Album_Adapter;
import model.Album;


public class MyMusic extends Fragment {


   FragmentMyMusicBinding b;

   public static Album_Adapter adapter;

    public ActionMode.Callback actionModeCallback;

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

        setUpActionBar();

        setUpFloatingButton();




        return v;
    }

    public void setUpActionBar() {

        actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.contextual_action_bar_menu_album, menu); // Define el menú contextual
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {

                    confirmarEliminació();
                    mode.finish(); // Finaliza el Action Mode
                    return true;
                }else if(item.getItemId()==R.id.action_edit){

                    for (Album a : Album.list_albums) {
                        if (a.isSelected()) {
                            Album_Creation.entrada= a;
                        }
                    }

                    mode.finish();

                    Navigation.findNavController(MyMusic.this.getView()).navigate(R.id.action_global_album_Creation);


                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

                adapter.notifyDataSetChanged();
            }
        };


    }









    public static void deleteSelectedItems() {
        List<Album> selectedItems = new ArrayList<>();

        // Itera sobre musicList para identificar elementos seleccionados
        for (Album item : Album.list_albums) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }

        // Elimina los elementos seleccionados de musicList
        Album.list_albums.removeAll(selectedItems);

        // Notifica al adaptador del cambio
        adapter.notifyDataSetChanged();
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


        adapter= new Album_Adapter(Album.list_albums,this);
        b.rcyAlbums.setAdapter(adapter);


    }


    private void confirmarEliminació() {
        delete_album_custom_dialog customDialog = new delete_album_custom_dialog();

        customDialog.show(getFragmentManager(), "CustomDialogFragment");
    }
}
