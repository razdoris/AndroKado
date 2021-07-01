package fr.eni.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import fr.eni.androkado.R;
import fr.eni.androkado.adapter.ContactAdapter;
import fr.eni.androkado.bo.Contact;
import fr.eni.androkado.metier.dao.ContactDAO;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArticleDTO article = Parcels.unwrap(getIntent().getParcelableExtra(InfoUrlActivity.EXTRA_OBJECT));
        setContentView(R.layout.activity_contact);

        RecyclerView recyclerView = findViewById(R.id.RV_contact);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Contact> listContact = ContactDAO.getListContact(this);

        ContactAdapter contactAdapter = new ContactAdapter(listContact, article, this);
        recyclerView.setAdapter(contactAdapter);

    }
}