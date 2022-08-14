package com.amaromerovic.countryflaggame.data;

public class Country {
    private final int picture;
    private final int name;

    public Country(int picture, int name) {
        this.picture = picture;
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public int getName() {
        return name;
    }
}
