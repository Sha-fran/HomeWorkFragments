package com.example.homeworkfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworkfragments.databinding.ItemBinding

class SuperheroRecyclerViewAdapter (
    var items:MutableList<Superheroes> = mutableListOf(),
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SuperheroRecyclerViewAdapter.SuperheroRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return SuperheroRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SuperheroRecyclerViewHolder, position: Int) {
        holder.superheroName.text = String.format("Name: ${items[position].name}")
        holder.superheroSlug.text = String.format("Slug: ${items[position].slug}")
        holder.superheroGender.text = String.format("Gender: ${items[position].appearance?.gender}")
        holder.superheroRace.text = String.format("Race: ${items[position].appearance?.race}")
        Glide.with(holder.superheroImage)
            .load(items[position].images?.lg)
            .into(holder.superheroImage)

        holder.bind(items[position], onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(item:Superheroes)
    }

    class SuperheroRecyclerViewHolder(item:View):RecyclerView.ViewHolder(item) {
        val binding = ItemBinding.bind(item)
        val superheroImage = binding.superheroImage
        val superheroName = binding.superheroName
        val superheroSlug = binding.superheroSlug
        val superheroGender = binding.superheroGender
        val superheroRace = binding.superheroRace

        fun bind(item: Superheroes, onItemClickListener: OnItemClickListener) = with(binding) {
            onItemClickListener.onItemClick(item)
        }

    }
}


