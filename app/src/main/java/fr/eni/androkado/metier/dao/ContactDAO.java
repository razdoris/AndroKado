package fr.eni.androkado.metier.dao;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import fr.eni.androkado.bo.Contact;
import fr.eni.androkado.metier.bdd.BaseContrat;

public class ContactDAO {

    public static List<Contact>  getListContact(Context context){

        //String tri = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        String tri = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE + " ASC";

        //String selection = ContactsContract.CommonDataKinds.Phone. + " = ? ";
       // String[] selectionArg = {String.valueOf(id)};

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                tri
        );


        List<Contact> listContact = new ArrayList<>();

        if(cursor != null){
            try {
                while(cursor.moveToNext()){
                    listContact.add(new Contact(
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    ));
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                cursor.close();
            }
        }


        return listContact;
    }

}
