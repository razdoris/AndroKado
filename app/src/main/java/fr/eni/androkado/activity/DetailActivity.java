package fr.eni.androkado.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import org.parceler.Parcels;

import java.security.Permission;
import java.util.List;

import fr.eni.androkado.R;
import fr.eni.androkado.bo.Article;
import fr.eni.androkado.bo.Contact;
import fr.eni.androkado.fragment.DetailArticleFragment;
import fr.eni.androkado.metier.dao.ContactDAO;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private static final int REQUEST_CODE_AUTORISATION = 123;
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
                Intent intentModify = new Intent(this, ModifyArticleActivity.class);
                intentModify.putExtra(InfoUrlActivity.EXTRA_OBJECT,Parcels.wrap(article));
                startActivity(intentModify);
                return true;
            case R.id.actionReturn:
                Intent intentReturn = new Intent(this, MainActivity.class);
                startActivity(intentReturn);
                return true;
            case R.id.actionSend:
                //Toast.makeText(this, "click sur envoyer", Toast.LENGTH_LONG).show();
                int permissionContact = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
                int permissionSms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
                if (permissionContact == PackageManager.PERMISSION_GRANTED && permissionSms == PackageManager.PERMISSION_GRANTED){
                    viewContact();
                }else{
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS}, REQUEST_CODE_AUTORISATION);
                    
                }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_AUTORISATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                viewContact();
            }else {
                Toast.makeText(this, "autorisation refusé !!!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void viewContact() {
        List<Contact> listContacts = ContactDAO.getListContact(this);
        //Toast.makeText(this, "coucou", Toast.LENGTH_SHORT).show();
        Intent intentContact = new Intent(this, ContactActivity.class);
        intentContact.putExtra(InfoUrlActivity.EXTRA_OBJECT, Parcels.wrap(article));
        startActivity(intentContact);
    }


}