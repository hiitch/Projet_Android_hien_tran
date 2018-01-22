package org.esia.hien_tran.pokepokedex.pokeapi;

import org.esia.hien_tran.pokepokedex.models.ResponsePokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexhien on 22/01/2018.
 */

public interface PokeapiService {

    @GET("pokemon")
    Call<ResponsePokemon> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
