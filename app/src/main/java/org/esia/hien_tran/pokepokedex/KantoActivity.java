package org.esia.hien_tran.pokepokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView recyclerView;
    private ListPokemonAdapter listPokemonAdapter;

    private boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanto);

        recyclerView =  (RecyclerView) findViewById(R.id.recyclerView);
        listPokemonAdapter = new ListPokemonAdapter(this);
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        //On crée un layout de 3 colonnes
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        //Lorsqu'on scroll l'écran
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleCount = layoutManager.findFirstVisibleItemPosition();

                    if(bool){
                        if((visibleItemCount + pastVisibleCount) >= totalItemCount){
                            Log.i(TAG,  "FIN de la liste");

                            bool = false;
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bool = true;

        getData();
    }

    //On récupère ici la liste des pokémons
    private void getData(){
        PokeapiService service = retrofit.create(PokeapiService.class);

        Call<ResponsePokemon> pokemonResponseCall=  service.getListPokemon(151);

        pokemonResponseCall.enqueue(new Callback<ResponsePokemon>() {
            @Override
            public void onResponse(Call<ResponsePokemon> call, Response<ResponsePokemon> response) {
                bool = true;
                if(response.isSuccessful()){
                    ResponsePokemon responsePokemon = response.body();
                    ArrayList<Pokemon> listPokemon = responsePokemon.getResults();

                    listPokemonAdapter.addListPokemon(listPokemon);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponsePokemon> call, Throwable t) {
                bool = true;
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
