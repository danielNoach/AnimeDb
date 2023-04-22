package com.example.animedb.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.animedb.Animeitem

@Dao
interface AnimeItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnime(anime: Animeitem)

    @Delete
    suspend fun deleteAnime(vararg animeItems: Animeitem)

    @Update
    suspend fun updateAnime(anime: Animeitem)

    @Query("SELECT * from AnimeItems ORDER BY title ASC")
    fun getAnimeItems(): LiveData<List<Animeitem>>

    @Query("DELETE from AnimeItems")
    suspend fun deleteAll()
}