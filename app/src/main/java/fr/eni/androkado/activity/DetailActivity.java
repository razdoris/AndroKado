package fr.eni.androkado.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.parceler.Parcels;

import fr.eni.androkado.R;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.fragment.DetailArticleFragment;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private ArticleDTO article;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = Parcels.unwrap(getIntent().getParcelableExtra(InfoUrlActivity.EXTRA_OBJECT));
        setContentView(R.layout.activity_detail);
        DetailArticleFragment fragment = new DetailArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(InfoUrlActivity.EXTRA_OBJECT, Parcels.wrap(article));
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fragment,fragment,"tagdetailArticle");
        transaction.commit();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_detail_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionModify:
                Toast.makeText(this, "modifY", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.actionReturn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}