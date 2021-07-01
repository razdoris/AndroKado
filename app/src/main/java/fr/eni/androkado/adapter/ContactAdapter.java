package fr.eni.androkado.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.List;

import fr.eni.androkado.R;
import fr.eni.androkado.activity.ContactActivity;
import fr.eni.androkado.activity.DetailActivity;
import fr.eni.androkado.activity.InfoUrlActivity;
import fr.eni.androkado.activity.MainActivity;
import fr.eni.androkado.bo.Contact;
import fr.eni.androkado.fragment.DetailArticleFragment;
import fr.eni.androkado.metier.dto.ArticleDTO;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {



    private static final String TAG = "ContactAdapter";
    private List<Contact> listContact;
    private ContactActivity contactActivity;
    private ArticleDTO article;

    public ContactAdapter(List<Contact> listContact, ArticleDTO article, ContactActivity contactActivity) {
        this.listContact = listContact;
        this.contactActivity = contactActivity;
        this.article = article;

    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View viewContact = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        return new ContactAdapter.ContactViewHolder(viewContact);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position)
    {
        holder.textViewContactName.setText(listContact.get(position).nom);
        holder.textViewContactNumber.setText(listContact.get(position).numero);

    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewContactName;
        public TextView textViewContactNumber;

        public ContactViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textViewContactName = itemView.findViewById(R.id.tv_contactName);
            textViewContactNumber = itemView.findViewById(R.id.tv_contactNumber);

            itemView.setOnClickListener(v -> {
                Contact contact = listContact.get(getAdapterPosition());

                StringBuffer sms = new StringBuffer("coucou " + contact.nom);
                sms.append("\r\n");
                sms.append("cette article pourrais m'interesser ");
                sms.append("\r\n");
                sms.append(article.name).append(": ");
                sms.append("\r\n");
                sms.append(article.url);


                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(contact.numero,null, sms.toString(),null,null);
                Toast.makeText(v.getContext(), "message envoyé a " + contact.nom + " : " + contact.numero, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                itemView.getContext().startActivity(intent);
            });

        }
    }
}
