package com.android.example.tennisscore.models;

import java.util.Calendar;

public class Player {

    private String firstName;
    private String lastName;
    private String country;
    private int birthYear;
    private int avatarId;

    public Player(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player(String firstName, String lastName, String country, int birthYear, int avatarId) {

        this(firstName, lastName);
        this.country = country;
        this.birthYear = birthYear;
        this.avatarId = avatarId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - this.birthYear;
    }

    public int getAvatarId() {
        return avatarId;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
