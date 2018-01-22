package org.esia.hien_tran.pokepokedex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.esia.hien_tran.pokepokedex.models.Pokemon;

import java.util.ArrayList;

/**
 * Created by alexhien on 22/01/2018.
 */

public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    public ArrayList<Pokemon> dataset;

    public ListPokemonAdapter() {
        dataset = new ArrayList<>();
    }

    @Override
    public ListPokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListPokemonAdapter.ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nameTextView.setText(p.getName());

        Glide.with
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addListPokemon(ArrayList<Pokemon> listPokemon) {
         dataset.addAll(listPokemon);
         notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView photoImageView;
        private TextView nameTextView;

        public ViewHolder(View itemView){
            super(itemView);

            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
