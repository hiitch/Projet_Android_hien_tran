package org.esia.hien_tran.pokepokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.esia.hien_tran.pokepokedex.models.Pokemon;
import org.esia.hien_tran.pokepokedex.models.ResponsePokemon;
import org.esia.hien_tran.pokepokedex.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KantoActivity extends AppCompatActivity {

    private static final String  TAG = "POKEDEX";

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanto);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getData();
    }

    private void getData(){
        PokeapiService service = retrofit.create(PokeapiService.class);

        Call<ResponsePokemon> pokemonResponseCall=  service.getListPokemon();

        pokemonResponseCall.enqueue(new Callback<ResponsePokemon>() {
            @Override
            public void onResponse(Call<ResponsePokemon> call, Response<ResponsePokemon> response) {
                if(response.isSuccessful()){
                    ResponsePokemon responsePokemon = response.body();
                    ArrayList<Pokemon> listPokemon = responsePokemon.getResults();

                    for(int i=0; i<listPokemon.size(); i++){
                        Pokemon p = listPokemon.get(i);
                        Log.i(TAG, "Pokemon: " + p.getName());
                    }

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponsePokemon> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
