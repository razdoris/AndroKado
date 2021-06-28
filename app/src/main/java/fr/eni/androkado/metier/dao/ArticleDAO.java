package fr.eni.androkado.metier.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.preference.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import fr.eni.androkado.metier.bdd.BaseContrat;
import fr.eni.androkado.metier.bdd.DatabaseHelper;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class ArticleDAO {

    public static List<ArticleDTO> getListArticles(Context context)
    {

        // récupération de la base de données :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean triPrix = preferences.getBoolean("triPrix", false);

        // requète :
        String[] projection = {
                BaseContrat.ArticleContrat._ID,
                BaseContrat.ArticleContrat.COLONNE_NAME,
                BaseContrat.ArticleContrat.COLONNE_PRICE,
                BaseContrat.ArticleContrat.COLONNE_DESCRIPTION,
                BaseContrat.ArticleContrat.COLONNE_RATING,
                BaseContrat.ArticleContrat.COLONNE_URL
        };

        String tri;
        if(triPrix == true){
            tri = BaseContrat.ArticleContrat.COLONNE_PRICE + " ASC";
        }else {
            tri = null;
        }

        Cursor cursor = db.query(
                false,
                BaseContrat.ArticleContrat.TABLE_ARTICLE,
                projection,
                null,
                null,
                null,
                null,
                tri,
                null);


        List<ArticleDTO> listDesArticles = new ArrayList<>();
        if(cursor != null){
            try {
                while (cursor.moveToNext()){
                    listDesArticles.add(new ArticleDTO(
                            cursor.getLong(cursor.getColumnIndex(BaseContrat.ArticleContrat._ID)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_NAME)),
                            cursor.getDouble(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_PRICE)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_DESCRIPTION)),
                            cursor.getDouble(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_RATING)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_RATING))
                    ));
                }

            }catch (Exception e){
                e.printStackTrace();

            }finally {
                cursor.close();
            }
        }

        return listDesArticles;
    }

    public static ArticleDTO getArticle(Context context, int id)
    {

        // récupération de la base de données :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // requète :
        String[] projection = {
                BaseContrat.ArticleContrat._ID,
                BaseContrat.ArticleContrat.COLONNE_NAME,
                BaseContrat.ArticleContrat.COLONNE_PRICE,
                BaseContrat.ArticleContrat.COLONNE_DESCRIPTION,
                BaseContrat.ArticleContrat.COLONNE_RATING,
                BaseContrat.ArticleContrat.COLONNE_URL
        };

        String selection = BaseContrat.ArticleContrat._ID + " = ? ";
        String[] selectionArg = {String.valueOf(id)};

        Cursor cursor = db.query(
                false,
                BaseContrat.ArticleContrat.TABLE_ARTICLE,
                projection,
                selection,
                selectionArg,
                null,
                null,
                null,
                null);

        ArticleDTO articleDTO = new ArticleDTO();
        if(cursor != null){
            try {

                articleDTO = new ArticleDTO(
                            cursor.getLong(cursor.getColumnIndex(BaseContrat.ArticleContrat._ID)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_NAME)),
                            cursor.getDouble(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_PRICE)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_DESCRIPTION)),
                            cursor.getDouble(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_RATING)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticleContrat.COLONNE_RATING))
                    );


            }catch (Exception e){
                e.printStackTrace();

            }finally {
                cursor.close();
            }
        }

        return articleDTO;
    }


    public static long addArticle(ArticleDTO articleDTO, Context context){

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = getContentValues(articleDTO);


        return db.insert(BaseContrat.ArticleContrat.TABLE_ARTICLE, null, values);

    }


    public static int updateArticle(ArticleDTO articleDTO, Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = getContentValues(articleDTO);

        String selection = BaseContrat.ArticleContrat._ID + " = ? ";
        String[] selectionArg = {String.valueOf(articleDTO.id)};

        int count = db.update(
                BaseContrat.ArticleContrat.TABLE_ARTICLE,
                values,
                selection,
                selectionArg);

        return count;

    }

    public static void deleteArticle(ArticleDTO articleDTO, Context context){

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String selection = BaseContrat.ArticleContrat._ID + " = ?";
        String[] selectionArgs = { String.valueOf(articleDTO.id)};

        db.delete(BaseContrat.ArticleContrat.TABLE_ARTICLE, selection, selectionArgs);
    }


    @NotNull
    private static ContentValues getContentValues(ArticleDTO articleDTO) {
        ContentValues values = new ContentValues();
        values.put(BaseContrat.ArticleContrat.COLONNE_NAME, articleDTO.name);
        values.put(BaseContrat.ArticleContrat.COLONNE_PRICE, articleDTO.price);
        values.put(BaseContrat.ArticleContrat.COLONNE_DESCRIPTION, articleDTO.description);
        values.put(BaseContrat.ArticleContrat.COLONNE_RATING, articleDTO.rating);
        values.put(BaseContrat.ArticleContrat.COLONNE_URL, articleDTO.url);
        return values;
    }
}
