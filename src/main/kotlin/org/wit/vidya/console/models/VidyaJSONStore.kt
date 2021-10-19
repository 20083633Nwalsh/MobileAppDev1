package org.wit.vidya.console.models

import com.andreapivetta.kolor.lightMagenta
import com.andreapivetta.kolor.lightYellow
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.vidya.console.helpers.exists


import org.wit.vidya.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "games.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<VidyaModel>>() {}.type

var lastId = 0L

fun generateId(): Long {
    return lastId++
   /* return Random().nextLong() */
}

class VidyaJSONStore : VidyaStore {

    var games = mutableListOf<VidyaModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<VidyaModel> {
        return games
    }

    override fun findOne(id: Long) : VidyaModel? {
        var foundVidya: VidyaModel? = games.find { p -> p.id == id }
        return foundVidya
    }

    override fun create(Vidya: VidyaModel) {
        Vidya.id = generateId()
        games.add(Vidya)
        logAll()
        serialize() //updates JSON
    }

    override fun edit(Vidya: VidyaModel) {
        var foundVidya = findOne(Vidya.id!!)
        if (foundVidya != null) {
            foundVidya.name = Vidya.name
            foundVidya.dev = Vidya.dev
        }
        serialize()
    }

    override fun delete (vidya: VidyaModel) {
        games.remove(vidya)
        serialize()
    }

    internal fun logAll() {
        games.forEach { logger.info("${it}".lightMagenta()) }
    }

    private fun serialize() {               //necessary to update JSON file
        val jsonString = gsonBuilder.toJson(games, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        games = Gson().fromJson(jsonString, listType)
    }
}