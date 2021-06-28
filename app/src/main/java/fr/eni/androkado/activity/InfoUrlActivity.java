package fr.eni.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

import fr.eni.androkado.R;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class InfoUrlActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT = "article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArticleDTO article = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_OBJECT));
        setContentView(R.layout.activity_info_url);
        TextView textViewArticleUrl = findViewById(R.id.TV_articleUrl);
        textViewArticleUrl.setText(article.url);


    }
}