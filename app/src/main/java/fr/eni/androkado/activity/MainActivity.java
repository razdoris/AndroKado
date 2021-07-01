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