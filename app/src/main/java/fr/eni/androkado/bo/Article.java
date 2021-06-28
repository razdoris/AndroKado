package fr.eni.androkado.bo;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Article{

    public String name;
    public double price;
    public String description;
    public double rating;
    public String url;

    public Article() {
    }

    public Article(String name, double price, String description, double rating, String url) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.url = url;
    }


}
