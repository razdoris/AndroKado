package fr.eni.androkado.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.parceler.Parcels;

import fr.eni.androkado.R;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class InfoUrlActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT = "article";

    ArticleDTO article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_OBJECT));
        setContentView(R.layout.activity_info_url);
        TextView textViewArticleUrl = findViewById(R.id.TV_articleUrl);
        textViewArticleUrl.setText(article.url);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_retour_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionReturn){
                Intent intentNew = new Intent(this, DetailActivity.class);
                intentNew.putExtra(EXTRA_OBJECT, Parcels.wrap(article));
                startActivity(intentNew);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}