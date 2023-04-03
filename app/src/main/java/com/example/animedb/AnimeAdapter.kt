package com.example.animedb

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animedb.databinding.AnimeCardLayoutBinding


class AnimeAdapter (val Animeitems:List<Animeitem>) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(){

    class AnimeViewHolder(private val binding: AnimeCardLayoutBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(Animeitem : Animeitem) {
                binding.animecardTitle.text = Animeitem.title
                binding.animecardDesc.text = Animeitem.description
                Glide.with(binding.root).load(Animeitem.photo).circleCrop().into(binding.animecardImage)
                //TODO:Load the imag,ep, rel
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnimeViewHolder(AnimeCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) =
        holder.bind(Animeitems[position])

    override fun getItemCount() =
        Animeitems.size

}