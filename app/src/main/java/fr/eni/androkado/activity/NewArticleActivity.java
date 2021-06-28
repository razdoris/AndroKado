package fr.eni.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import fr.eni.androkado.R;
import fr.eni.androkado.metier.dao.ArticleDAO;
import fr.eni.androkado.metier.dto.ArticleDTO;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class NewArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int defaultPrice = preferences.getInt("prix", 10);

        TextView textViewNewArticleName = findViewById(R.id.ET_newArticleName);
        TextView textViewNewArticleDesc = findViewById(R.id.ET_newArticleDesc);
        TextView textViewNewArticleUrl = findViewById(R.id.ET_newArticleUrl);
        TextView textViewNewArticlePrice = findViewById(R.id.ET_newArticlePrice);
        RatingBar ratingBarNewArticleRating = findViewById(R.id.RB_newArticleNotation);
        Button buttonAddNewArticle = findViewById(R.id.BT_addArticle);
        Button buttonAbordNewArticle = findViewById(R.id.BT_abord);

        textViewNewArticlePrice.setText(String.valueOf(defaultPrice));

        buttonAbordNewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain(v);
            }
        });

        buttonAddNewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String articleName = textViewNewArticleName.getText().toString();
                double articlePrice = Double.parseDouble(textViewNewArticlePrice.getText().toString());
                String articleDescription = textViewNewArticleDesc.getText().toString();
                double articleRating = ratingBarNewArticleRating.getRating();
                String articleUrl = textViewNewArticleUrl.getText().toString();


                ArticleDTO articleDTO = new ArticleDTO(0, articleName, articlePrice,articleDescription,articleRating,articleUrl);
                ArticleDAO.addArticle(articleDTO,v.getContext());


                returnToMain(v);
            }
        });

    }

    private final void returnToMain(View v) {
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);

    }
}