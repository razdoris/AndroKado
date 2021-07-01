package fr.eni.androkado.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

import fr.eni.androkado.R;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        EditText editTextDefaultPrice = findViewById(R.id.IT_defaultPrice);
        Switch switchTri = findViewById(R.id.SwitchPrice);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean triPrix = preferences.getBoolean("triPrix", false);
        int defaultPrice = preferences.getInt("prix", 10);

        editTextDefaultPrice.setText(String.valueOf(defaultPrice));
        switchTri.setChecked(triPrix);

        SharedPreferences.Editor editor = preferences.edit();



        switchTri.setOnClickListener(v -> {
            editor.putBoolean("triPrix",switchTri.isChecked());
            editor.apply();
        });


        /* non remplacer par lambda pour avoir la syntaxe*/
        editTextDefaultPrice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(ConfigurationActivity.this, editTextDefaultPrice.getText(), Toast.LENGTH_SHORT).show();
                int prix = Integer.parseInt(editTextDefaultPrice.getText().toString());
                editor.putInt("prix",prix);
                editor.apply();

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_retour_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.actionReturn){
            Intent intentNew = new Intent(this, MainActivity.class);
            startActivity(intentNew);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}