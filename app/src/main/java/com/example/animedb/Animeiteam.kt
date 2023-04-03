package com.example.animedb

data class Animeitem (val title:String, val description:String, val episodesnum:String?,
                       val yearrel:String?, val photo:String?)

object ItemManager {

    val Animes : MutableList<Animeitem> = mutableListOf()

    fun add(Animeitem : Animeitem) {
        Animes.add(Animeitem)
    }

    fun remove(index:Int) {
        Animes.removeAt(index)
    }
}