package fr.eni.androkado.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.List;

import fr.eni.androkado.activity.DetailActivity;
import fr.eni.androkado.activity.InfoUrlActivity;
import fr.eni.androkado.activity.MainActivity;
import fr.eni.androkado.R;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.fragment.DetailArticleFragment;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class KadoAdapter extends RecyclerView.Adapter<KadoAdapter.KadoViewHolder>
{

    private static final String TAG = "KadoAdapter";
    private List<ArticleDTO> listArticles;
    private MainActivity mainActivity;

    public KadoAdapter(List<ArticleDTO> listArticles, MainActivity mainActivity) {
        this.listArticles = listArticles;
        this.mainActivity = mainActivity;
        Log.i(TAG,"ça passe ici");
    }

    @NonNull
    @Override
    public KadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View viewArticle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kado, parent, false);

        Log.i(TAG,"ça passe ici aussi");
        return new KadoViewHolder(viewArticle);

    }

    @Override
    public void onBindViewHolder(@NonNull KadoViewHolder holder, int position)
    {
        holder.textViewArticleName.setText(listArticles.get(position).name);
        holder.ratingBarArticle.setRating((float) listArticles.get(position).rating);
        Log.i(TAG,"ça passe ici aussi je pense");


    }

    @Override
    public int getItemCount() {
        return listArticles.size();
    }

    class KadoViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewArticleName;
        public RatingBar ratingBarArticle;



        public KadoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textViewArticleName = itemView.findViewById(R.id.tv_articleName);
            ratingBarArticle = itemView.findViewById(R.id.rb_articleNotation);

            //ajout d'un listener
            itemView.setOnClickListener(v -> {
                ArticleDTO article = listArticles.get(getAdapterPosition());

                if(mainActivity.findViewById(R.id.FL_container_detail) != null){
                    DetailArticleFragment fragment = new DetailArticleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(InfoUrlActivity.EXTRA_OBJECT, Parcels.wrap(article));
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction =  mainActivity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.FL_container_detail,fragment,"tagdetailArticle");
                    transaction.commit();

                }else{
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra(InfoUrlActivity.EXTRA_OBJECT, Parcels.wrap(article));
                    itemView.getContext().startActivity(intent);
                }



            });

        }
    }

}
