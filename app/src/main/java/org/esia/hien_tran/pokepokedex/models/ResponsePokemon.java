package org.esia.hien_tran.pokepokedex.models;

import java.util.ArrayList;

/**
 * Created by alexhien on 22/01/2018.
 */

public class ResponsePokemon {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
