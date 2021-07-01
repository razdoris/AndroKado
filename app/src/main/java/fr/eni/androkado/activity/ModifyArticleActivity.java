package fr.eni.androkado.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.parceler.Parcels;

import fr.eni.androkado.R;
import fr.eni.androkado.metier.dao.ArticleDAO;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class ModifyArticleActivity extends AppCompatActivity {
    ArticleDTO article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_article);
        article = Parcels.unwrap(getIntent().getParcelableExtra(InfoUrlActivity.EXTRA_OBJECT));

        TextView textViewModifyArticleName = findViewById(R.id.ET_modifyArticleName);
        TextView textViewModifyArticleDesc = findViewById(R.id.ET_modifyArticleDesc);
        TextView textViewModifyArticleUrl = findViewById(R.id.ET_modifyArticleUrl);
        TextView textViewModifyArticlePrice = findViewById(R.id.ET_modifyArticlePrice);
        RatingBar ratingBarModifyArticleRating = findViewById(R.id.RB_modifyArticleNotation);
        Button buttonModifyArticleSave = findViewById(R.id.BT_modifyArticleSave);
        Button buttonModifyArticleDelete = findViewById(R.id.BT_modifyArticleDelete);

        textViewModifyArticleName.setText(article.name);
        textViewModifyArticleDesc.setText(article.description);
        textViewModifyArticleUrl.setText(article.url);
        textViewModifyArticlePrice.setText(""+article.price);
        ratingBarModifyArticleRating.setRating((float)article.rating);

        buttonModifyArticleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article.name = textViewModifyArticleName.getText().toString();
                article.description = textViewModifyArticleDesc.getText().toString();
                article.price = Double.parseDouble(textViewModifyArticlePrice.getText().toString());
                article.rating = ratingBarModifyArticleRating.getRating();
                article.url =textViewModifyArticleUrl.getText().toString();

                ArticleDAO.updateArticle(article,v.getContext());
                returnToArticle(v.getContext());
            }
        });

        buttonModifyArticleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleDAO.deleteArticle(article, v.getContext());
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_retour_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionReturn:
                returnToArticle(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnToArticle(Context modifyArticleActivity) {
        Intent intentReturnDetail = new Intent(modifyArticleActivity, DetailActivity.class);
        intentReturnDetail.putExtra(InfoUrlActivity.EXTRA_OBJECT, Parcels.wrap(article));
        startActivity(intentReturnDetail);
    }
}