package fr.eni.androkado.metier.bdd;

import android.provider.BaseColumns;

public class BaseContrat {

    private BaseContrat() {
    }

    public static class ArticleContrat implements BaseColumns
    {
        public static final String TABLE_ARTICLE = "articles";
        public static final String COLONNE_NAME = "name";
        public static final String COLONNE_PRICE = "price";
        public static final String COLONNE_DESCRIPTION = "description";
        public static final String COLONNE_RATING = "rating";
        public static final String COLONNE_URL = "url";
    }


}
