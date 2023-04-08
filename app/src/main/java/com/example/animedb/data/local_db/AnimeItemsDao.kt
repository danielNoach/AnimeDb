package com.example.animedb.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.animedb.Animeitem

@Dao
interface AnimeItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAnime(anime: Animeitem)

    @Delete
    fun deleteAnime(vararg animeItems: Animeitem)

    @Update
    fun updateAnime(anime: Animeitem)

    @Query("SELECT * from AnimeItems ORDER BY title ASC")
    fun getAnimeItems(): LiveData<List<Animeitem>>

    @Query("SELECT * from AnimeItems WHERE id = :id")
    fun getItem(id:Int) : Animeitem
}