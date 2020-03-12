package com.alithya.talentsboard.model;


import java.time.LocalDate;

public class FelicitationResponse {

    private String UID;
    private PersonEntity fromPerson;
    private PersonEntity toPerson;
    private String message;
    private String date;
    private int numberOfLikes;
    private boolean isFelicitationLikedByYou;


    public String getUID() {
        return UID;
    }

    public PersonEntity getFromPerson() {
        return fromPerson;
    }

    public PersonEntity getToPerson() {
        return toPerson;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public boolean isFelicitationLikedByYou() {
        return isFelicitationLikedByYou;
    }
}
