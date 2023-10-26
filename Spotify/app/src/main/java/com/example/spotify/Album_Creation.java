package com.example.spotify;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.spotify.databinding.FragmentAlbumCreationBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import model.Album;
import model.DateUtils;

public class Album_Creation extends Fragment implements Custom_Dialog_Image_Picker.OnImageSelectedListener {

    FragmentAlbumCreationBinding b;
    DatePickerDialog picker;


    String pathImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentAlbumCreationBinding.inflate(getLayoutInflater());
        View v = b.getRoot();

        setUpDialogDatePicker();
        setUpImagePicker();
        setUpTextController();

        setUpCreationButton();




        return v;

    }



    private void setUpCreationButton() {

        b.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Album nou = new Album(Album.getNewId(),b.edtAlbumTitle.getText().toString(), pathImage, b.edtAuthorName.getText().toString(), DateUtils.parseDayMonthYear(b.editText.getText().toString()));

                Album.list_albums.add(nou);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Album enregistrat amb exit")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                b.edtAlbumTitle.setText("");
                                b.edtAuthorName.setText("");
                                b.editText.setText("");
                                b.imgPickerReal.setImageResource(R.drawable.music_creation);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });

    }


    private void setUpTextController() {

        b.edtAlbumTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Album.Album_Realesed(b.edtAlbumTitle.getText().toString())){
                    verificarYActualizarBoton();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Nom ja registrat")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    b.edtAlbumTitle.setText("");
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        });


        b.edtAuthorName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verificarYActualizarBoton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        b.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verificarYActualizarBoton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setUpImagePicker() {
        b.imgPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Custom_Dialog_Image_Picker customDialog = new Custom_Dialog_Image_Picker();

                customDialog.show(getFragmentManager(), "CustomDialogFragment");


                customDialog.setOnImageSelectedListener(new Custom_Dialog_Image_Picker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri selectedImageUri) {
                        // Actualiza el ImageButton con la imagen seleccionada

                        File folder = getContext().getFilesDir();
                        File arxiu = new File(folder, "nom"+MyMusic.imagenumber+".png");
                        Bitmap mImageBitmap = null;
                        MyMusic.imagenumber++;

                        try {
                            InputStream inputStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                            mImageBitmap = BitmapFactory.decodeStream(inputStream);
                            FileOutputStream outputStream = new FileOutputStream(arxiu);
                            mImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                            outputStream.close();

                            pathImage = arxiu.getAbsolutePath();


                            Bitmap bitmap = BitmapFactory.decodeFile(pathImage);

                            b.imgPickerReal.setImageBitmap(bitmap);


                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });





            }
        });
    }

    private void setUpDialogDatePicker() {

        b.btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        R.style.DatePickerSpinner,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                b.editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }


    @Override
    public void onImageSelected(Uri selectedImageUri) {
        b.imgPicker.setImageURI(selectedImageUri);
    }


    private void verificarYActualizarBoton() {


        String texto1 = b.edtAuthorName.getText().toString();
        String texto2 = b.edtAlbumTitle.getText().toString();
        String texto3 = b.editText.getText().toString();

        boolean habilitarBoton = !texto1.isEmpty() && !texto2.isEmpty() && !texto3.isEmpty();
        b.fab.setEnabled(habilitarBoton);
    }

}