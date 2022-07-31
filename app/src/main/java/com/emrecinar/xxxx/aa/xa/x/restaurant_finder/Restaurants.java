package com.emrecinar.xxxx.aa.xa.x.restaurant_finder;

import java.util.ArrayList;

public class Restaurants {
    private ArrayList<String> menuList;
    private String place;
    private double rating;
    private String favStatus;

    public Restaurants(ArrayList<String> menuList, String place, double rating, String favStatus) {
        this.menuList = menuList;
        this.place = place;
        this.rating = rating;
        this.favStatus = favStatus;
    }

    public Restaurants() {
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public ArrayList<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<String> menuList) {
        this.menuList = menuList;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
