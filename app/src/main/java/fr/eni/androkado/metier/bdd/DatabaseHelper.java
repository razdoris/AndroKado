package fr.eni.androkado.metier.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
