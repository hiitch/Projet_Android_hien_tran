package org.esia.hien_tran.pokepokedex;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by alexhien on 15/01/2018.
 */

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

   /*public class PokemonUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG", getIntent().getAction());
            JSONArray arrayPokemon = getPokemonFromFile();
        }
    }

    public JSONArray getPokemonFromFile(){
        try{
           InputStream is = new FileInputStream(getCacheDir() + "/" + "pokemon.json");

        }
    }*/

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
            case R.id.menu_mail:
                envoyerMail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Fonction qui se lance lorsqu'on appuie sur le bouton Kanto
    public void goToKanto(View v){
        Toast.makeText(getApplicationContext(),getString(R.string.kanto),Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, KantoActivity.class);
        startActivity(i);
    }

    //Fonction qui se lance lorsqu'on appuie sur le bouton Johto
    public void goToJohto(View v){
        Toast.makeText(getApplicationContext(),getString(R.string.johto),Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, JohtoActivity.class);
        startActivity(i);
    }

    //Affiche une boîte de dialogue
    public void boiteDeDialogue(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titre_boite_dialogue)
                .setMessage(R.string.message_boite_dialogue)
                .setNeutralButton(R.string.retour_boite_dialogue, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    //Nous envoyer un feedback :D
    public void envoyerMail(){
        Toast.makeText(getApplicationContext(),"ENVOYERMAIL()",Toast.LENGTH_LONG).show();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //setType prend le type mime du mail
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.sujet_mail));
        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.contenu_mail));
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        startActivity(emailIntent);
    }

    //Notifcation
    public void notifer(View v){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.pokedex)
                    .setContentTitle(getString(R.string.titre_notification))
                    .setContentText(getString(R.string.message_notification));

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }
}
