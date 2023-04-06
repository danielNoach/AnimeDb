package com.example.animedb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animedb.Animeitem
import com.example.animedb.data.repository.AnimeItemRepository

class AnimeItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo= AnimeItemRepository(application)

    val animeItems : LiveData<List<Animeitem>>? = repo.getAnimeItems()

    private val _chosenAnimeItem = MutableLiveData<Animeitem>()
    val chosenAnimeItem: LiveData<Animeitem> get() = _chosenAnimeItem
    fun setAnimeItem(anime: Animeitem){
        _chosenAnimeItem.value = anime
    }

    fun addAnimeItem(anime: Animeitem){
        repo.addAnimeItem(anime)
    }

    fun deleteAnimeItem(anime: Animeitem){
        repo.deleteAnimeItem(anime)
    }

}