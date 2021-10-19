package org.wit.vidya.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}


internal fun getId(): Long {
    return lastId++
}

class VidyaMemStore : VidyaStore {

    val games = ArrayList<VidyaModel>()

    override fun findAll(): List<VidyaModel> {
        return games
    }

    override fun findOne(id: Long) : VidyaModel? {
        var foundVidya: VidyaModel? = games.find { p -> p.id == id }
        return foundVidya
    }

    override fun create(Vidya: VidyaModel) {
        Vidya.id = getId()
        games.add(Vidya)
        logAll()
    }

    override fun edit(Vidya: VidyaModel) {
        var foundVidya = findOne(Vidya.id!!)
        if (foundVidya != null) {
            foundVidya.name = Vidya.name
            foundVidya.dev = Vidya.dev
        }
    }

    override fun delete(vidya: VidyaModel) {
        games.remove(vidya)
    }

    internal fun logAll() {
        games.forEach { logger.info("${it}") }
    }
}