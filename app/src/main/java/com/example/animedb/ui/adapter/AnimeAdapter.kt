package com.example.animedb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animedb.Animeitem
import com.example.animedb.databinding.AnimeCardLayoutBinding


class AnimeAdapter (val Animeitems:List<Animeitem>, val callBack: AnimeListener) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(){
    interface AnimeListener{
        fun onAnimeItemClick(index: Int)
    }

    inner class AnimeViewHolder(private val binding: AnimeCardLayoutBinding)
        :RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {
            binding.root.setOnClickListener(this)
        }

            fun bind(Animeitem : Animeitem)
            {
                binding.animecardTitle.text = Animeitem.title
                binding.animecardDesc.text = Animeitem.description
                Glide.with(binding.root).load(Animeitem.photo).circleCrop().into(binding.animecardImage)
                //TODO:Load the imag,ep, rel
            }

        override fun onClick(v: View?) {
            callBack.onAnimeItemClick(adapterPosition)
        }
    }

    fun animeItemAt(position: Int) = Animeitems[position]


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnimeViewHolder(AnimeCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int)=
        holder.bind(Animeitems[position])


    override fun getItemCount() =
        Animeitems.size

}