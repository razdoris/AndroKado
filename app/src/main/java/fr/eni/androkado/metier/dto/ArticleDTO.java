package fr.eni.androkado.metier.dto;

import org.parceler.Parcel;

@Parcel
public class ArticleDTO {

    public long id;
    public String name;
    public double price;
    public String description;
    public double rating;
    public String url;

    public ArticleDTO() {
    }

    public ArticleDTO(long id, String name, double price, String description, double rating, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.url = url;
    }
}
