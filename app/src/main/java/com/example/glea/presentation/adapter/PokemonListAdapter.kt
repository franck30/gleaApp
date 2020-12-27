package com.example.glea.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.glea.R
import com.example.glea.domain.models.Pokemon


class PokemonListAdapter :
    PagingDataAdapter<Pokemon, RecyclerView.ViewHolder>(pokemonDiffCallback) {

    var pokemonSelectedListener: OnPokemonSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PokemonViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonNameTextView : AppCompatTextView = itemView.findViewById(R.id.pokemon_name)
        fun bind(pokemon: Pokemon?) {
            pokemonNameTextView.text = pokemon?.name
            itemView.setOnClickListener {
                pokemonSelectedListener?.onPokemonSelected(pokemon?.name)
            }
        }
    }

    interface OnPokemonSelectedListener {
        fun onPokemonSelected(name: String?)
    }

    companion object {
        private val pokemonDiffCallback = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}