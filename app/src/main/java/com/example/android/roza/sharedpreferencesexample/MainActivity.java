package com.example.android.roza.sharedpreferencesexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // La chaîne récupérée est mise dans une view pour affichage.
        editText = findViewById(R.id.editText1);
        loadProperty("user","anonymous");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadProperty(String property, String defaut){
        // Récupération du fichier de préférences par défaut.
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        // Récupération de la chaîne associée à la clé nommée "user".
        // Si la clé n'existe pas, la chaîne "anonymous" est retournée.
        String user = settings.getString(property, defaut);
        editText.setText(user);
    }

    private void saveProperty(String property, String value){
        // Récupération du fichier de préférences par défaut.
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        // Récupération de l'Editor pour modifier les entrées.
        SharedPreferences.Editor editor = settings.edit();
        // La chaîne est associée à la clé "user".
        editor.putString(property, value);
        // On committe les modifications.
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onStop()
    {
    super.onStop();
    saveProperty("user",( editText.getText() ).toString() );
    }


    /** Ecrire le texte vers le fichier */
    public void writeBtn(View v) {
        saveProperty("user",( editText.getText() ).toString() );
    }

    /** Effacer l'EditText */
    public void clearBtn(View v) {
        editText.setText("");
    }

    /** Lire le texte à partir des SharedPreferences */
    public void readBtn(View v) {
        loadProperty("user", "anonymous");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
