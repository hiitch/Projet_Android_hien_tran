package org.esia.hien_tran.pokepokedex.pokeapi;

import org.esia.hien_tran.pokepokedex.models.ResponsePokemon;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alexhien on 22/01/2018.
 */

public interface PokeapiService {

    @GET("pokemon")
    Call<ResponsePokemon> getListPokemon();
}
