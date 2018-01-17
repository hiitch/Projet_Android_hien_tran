package org.esia.hien_tran.pokepokedex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by alexhien on 15/01/2018.
 */

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //Permet d'afficher les items de barre de menu (en haut)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Permet de définir l'action des boutons de l'action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_information:
                boiteDeDialogue(findViewById(R.id.menu_information));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Fonction qui se lance lorsqu'on appuie sur le bouton Kanto
    public void goToKanto(View v){
        Toast.makeText(getApplicationContext(),getString(R.string.fr_kanto),Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, KantoActivity.class);
        startActivity(i);
    }

    //Fonction qui se lance lorsqu'on appuie sur le bouton Johto
    public void goToJohto(View v){
       /* Toast.makeText(getApplicationContext(),getString(R.string.fr_johto),Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, JohtoActivity.class);
        startActivity(i);*/

    }

    //Affiche une boîte de dialogue
    public void boiteDeDialogue(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.fr_titre_boite_dialogue)
                .setMessage(R.string.fr_message_boite_dialogue)
                .setNeutralButton(R.string.fr_retour_boite_dialogue, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
