package com.example.animedb.data.repository

import android.app.Application
import com.example.animedb.Animeitem
import com.example.animedb.data.local_db.AnimeItemDataBase
import com.example.animedb.data.local_db.AnimeItemsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeItemRepository(application: Application) {

    private var animeItemDao: AnimeItemsDao?

    init {
        val db = AnimeItemDataBase.getDatabase(application.applicationContext)
        animeItemDao = db?.animeItemsDao()
    }

    fun getAnimeItems() = animeItemDao?.getAnimeItems()

    suspend fun addAnimeItem(anime: Animeitem) {
        animeItemDao?.addAnime(anime)
    }

    suspend fun deleteAnimeItem(anime: Animeitem) {
        animeItemDao?.deleteAnime(anime)
    }

    suspend fun updateAnimeItem(anime: Animeitem) {
        animeItemDao?.updateAnime(anime)
    }

    suspend fun deleteAll() {
        animeItemDao?.deleteAll()
    }


}