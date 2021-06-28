package fr.eni.androkado.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import org.parceler.Parcels;

import fr.eni.androkado.activity.InfoUrlActivity;
import fr.eni.androkado.R;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class DetailArticleFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {


        return inflater.inflate(R.layout.fragment_detail_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton imageButton = view.findViewById(R.id.IB_url);

        Bundle bundle = getArguments();
        if(bundle != null){
            ArticleDTO article = Parcels.unwrap(bundle.getParcelable(InfoUrlActivity.EXTRA_OBJECT));
            TextView textViewArticleName =  view.findViewById(R.id.tv_articleName);
            TextView textViewArticlePrice = view.findViewById(R.id.tv_articlePrice);
            TextView textViewArticleDescription = view.findViewById(R.id.tv_articleDescription);
            RatingBar ratingBarreArticleRating = view.findViewById(R.id.rb_articleNotation);

            textViewArticleName.setText(article.name);
            textViewArticlePrice.setText(""+article.price);
            textViewArticleDescription.setText(article.description);
            ratingBarreArticleRating.setRating((float) article.rating);

            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(view.getContext(), InfoUrlActivity.class);
                intent.putExtra(InfoUrlActivity.EXTRA_OBJECT, Parcels.wrap(article));
                startActivity(intent);
            });
        }




    }
}