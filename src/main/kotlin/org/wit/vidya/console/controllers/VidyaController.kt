package org.wit.vidya.console.controllers

import mu.KotlinLogging

import org.wit.vidya.console.models.VidyaMemStore
import org.wit.vidya.console.models.VidyaModel
import org.wit.vidya.console.views.VidyaView

val vidyaView = VidyaView()
val games = VidyaMemStore()

val logger = KotlinLogging.logger {}

class VidyaController {



    init {
        logger.info { "Launching Vidya Console App" }
        println("Vidya Kotlin App Version 1.0")
    }

    fun menu() :Int { return vidyaView.menu() }

    fun add(){
        var aVidya = VidyaModel()

        if (vidyaView.addVidyaData(aVidya))
            games.create(aVidya)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        vidyaView.viewWishlist(games)
    }

    fun update() {

        vidyaView.viewWishlist(games)
        var searchId = vidyaView.getId()
        val aVidya = search(searchId)

        if(aVidya != null) {
            if(vidyaView.updateVidyaData(aVidya)) {
                games.update(aVidya)
                vidyaView.showGame(aVidya)
                logger.info("Game Updated : [ $aVidya ]")
            }
            else
                logger.info("Game Not Updated")
        }
        else
            println("Game Not Updated...")
    }
}

fun search() {
    val aVidya = search(vidyaView.getId())!!
    vidyaView.showGame(aVidya)
}

fun search(id: Long) : VidyaModel? {
    var foundPlacemark = games.findOne(id)
    return foundPlacemark
}