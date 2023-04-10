package com.example.animedb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Parcelize
@Entity(tableName = "AnimeItems")
data class Animeitem (
    @ColumnInfo(name="title")
    val title:String,
    @ColumnInfo(name="description")
    val description:String,
    @ColumnInfo(name="number_of_episodes")
    val number_of_episodes:String?,
    @ColumnInfo(name="release_year")
    val release_year:String?,
    @ColumnInfo(name="photo")
    val photo:String?
    ) : Parcelable {
    @PrimaryKey(autoGenerate=true)
    var id: Int = 0
    }

