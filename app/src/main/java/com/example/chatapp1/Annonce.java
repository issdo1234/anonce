package com.example.chatapp1;

public class Annonce {
    String  nomAnnonce, prixAnnonce, dateAnnonce, dateDebut ;
    int imageId;

    public Annonce(String nomAnnonce, String prixAnnonce, String dateAnnonce, String dateDebut, int imageId) {
        this.nomAnnonce = nomAnnonce;
        this.prixAnnonce = prixAnnonce;
        this.dateAnnonce = dateAnnonce;
        this.dateDebut = dateDebut;
        this.imageId = imageId;
    }
}