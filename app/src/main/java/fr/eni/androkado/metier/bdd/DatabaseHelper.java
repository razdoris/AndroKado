package fr.eni.androkado.metier.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fr.eni.androkado.metier.dao.ArticleDAO;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(@Nullable Context context ) {
        super(context, "article.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BaseContrat.ArticleContrat.TABLE_ARTICLE + "("
                + BaseContrat.ArticleContrat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BaseContrat.ArticleContrat.COLONNE_NAME + " TEXT NOT NULL, "
                + BaseContrat.ArticleContrat.COLONNE_PRICE + " DOUBLE NOT NULL, "
                + BaseContrat.ArticleContrat.COLONNE_DESCRIPTION + " TEXT NOT NULL, "
                + BaseContrat.ArticleContrat.COLONNE_RATING + " DOUBLE NOT NULL, "
                + BaseContrat.ArticleContrat.COLONNE_URL + " TEXT "
                +")");


        List<ArticleDTO> listArticle =new ArrayList<>();
        listArticle.add(new ArticleDTO(0,"pain au chocolats", 1.2, "une viennoiserie au beurre et au chocolat", 2, "leTempleDuCroissant.com"));
        listArticle.add(new ArticleDTO(0,"croissant", 1.0, "une viennoiserie au beurre", 1, "leTempleDuCroissant.com"));
        listArticle.add(new ArticleDTO(0,"voiture téléguidé", 85, "une voiture téléguidé", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Harry Potter", 25, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Harry Potter la maison d'hagrid", 25, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Harry Potter le chat de rusard", 35, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Harry Potter la chambre et le basilic", 15, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars l'étoil de la mort", 1000, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars le x wing", 75, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars le faucon millenium", 99, "un set de jeux lego", 3, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars tie-fighter", 72, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars baby yoda", 16, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars tie-fighters de dark vador", 18, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Star wars destroyer imperial", 26, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Marvel spiderman", 52, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Marvel ant-man", 6, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Marvel les gardien de la galaxie", 15, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Lego Marvel iron man", 25, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new ArticleDTO(0,"Kawasaki Z750", 25000, "une moto", 4, "motorep.com"));

        for(ArticleDTO article:listArticle){
            ContentValues values = new ContentValues();
            values.put(BaseContrat.ArticleContrat.COLONNE_NAME, article.name);
            values.put(BaseContrat.ArticleContrat.COLONNE_PRICE, article.price);
            values.put(BaseContrat.ArticleContrat.COLONNE_DESCRIPTION, article.description);
            values.put(BaseContrat.ArticleContrat.COLONNE_RATING, article.rating);
            values.put(BaseContrat.ArticleContrat.COLONNE_URL, article.url);

            db.insert(BaseContrat.ArticleContrat.TABLE_ARTICLE, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
