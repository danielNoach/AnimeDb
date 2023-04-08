package com.example.animedb.data.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animedb.Animeitem

@Database(entities = arrayOf(Animeitem::class), version = 1, exportSchema = false)
abstract class AnimeItemDataBase: RoomDatabase() {

    abstract fun animeItemsDao() : AnimeItemsDao

    companion object {

        @Volatile
        private var instance: AnimeItemDataBase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, AnimeItemDataBase::class.java, "animeItems_db").allowMainThreadQueries().build()
        }
    }
}