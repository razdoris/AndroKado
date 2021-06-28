package fr.eni.androkado.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import fr.eni.androkado.R;
import fr.eni.androkado.adapter.KadoAdapter;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.metier.bdd.DatabaseHelper;
import fr.eni.androkado.metier.dao.ArticleDAO;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.RV_kado);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        List<ArticleDTO> listArticle = ArticleDAO.getListArticles(this);

                /*new ArrayList<>();
        listArticle.add(new Article("croissant", 1.0, "une viennoiserie au beurre", 1, "leTempleDuCroissant.com"));
        listArticle.add(new Article("pain au chocolats", 1.2, "une viennoiserie au beurre et au chocolat", 2, "leTempleDuCroissant.com"));
        listArticle.add(new Article("voiture téléguidé", 85, "une voiture téléguidé", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Harry Potter", 25, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Harry Potter la maison d'hagrid", 25, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Harry Potter le chat de rusard", 35, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Harry Potter la chambre et le basilic", 15, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars l'étoil de la mort", 1000, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars le x wing", 75, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars le faucon millenium", 99, "un set de jeux lego", 3, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars tie-fighter", 72, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars baby yoda", 16, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars tie-fighters de dark vador", 18, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Star wars destroyer imperial", 26, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Marvel spiderman", 52, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Marvel ant-man", 6, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Marvel les gardien de la galaxie", 15, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Lego Marvel iron man", 25, "un set de jeux lego", 4, "maxingJouet.com"));
        listArticle.add(new Article("Kawasaki Z750", 25000, "une moto", 4, "motorep.com"));*/

        KadoAdapter kadoAdapter = new KadoAdapter(listArticle, this);
        recyclerView.setAdapter(kadoAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_list_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionAdd:
                Intent intentNew = new Intent(this, NewArticleActivity.class);
                startActivity(intentNew);
                return true;
            case R.id.actionConfiguration:
                Intent intentConfiguration = new Intent(this, ConfigurationActivity.class);
                startActivity(intentConfiguration);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}