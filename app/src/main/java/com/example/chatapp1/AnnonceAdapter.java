package com.example.chatapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chatapp1.databinding.ActivityMainBinding;
import com.example.chatapp1.ui.home.HomeFragment;
import com.example.chatapp1.utilities.Constants;

import java.util.ArrayList;

public class AnnonceAdapter extends ArrayAdapter<Annonce> {

    public AnnonceAdapter(Context context, ArrayList<Annonce> annonceArrayList) {
        super(context, R.layout.item_annonce,annonceArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Annonce annonce = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_annonce,parent,false);
        }

        ImageView imageannonce = convertView.findViewById(R.id.imageAnnonceur);
        TextView nomannonce = convertView.findViewById(R.id.nomAnnonce);
        TextView prixannonce = convertView.findViewById(R.id.prixAnnonce);
        TextView dateannonce = convertView.findViewById(R.id.dateAnnonce);
        TextView datedebut = convertView.findViewById(R.id.dateDebut);




        imageannonce.setImageResource(annonce.imageId);
        nomannonce.setText(annonce.nomAnnonce);
        prixannonce.setText(annonce.prixAnnonce);
        dateannonce.setText(annonce.dateAnnonce);
        datedebut.setText(annonce.dateDebut);

        return convertView;
    }
}



