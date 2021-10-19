package org.wit.vidya.console.controllers

import com.andreapivetta.kolor.lightMagenta
import mu.KotlinLogging
import org.wit.vidya.console.models.VidyaJSONStore
import org.wit.vidya.console.models.VidyaModel
import org.wit.vidya.console.views.VidyaView

// val games = VidyaMemStore()

var games = VidyaJSONStore()
val vidyaView = VidyaView()
val logger = KotlinLogging.logger {}

class VidyaController {

    init {
        logger.info { "Launching Vidya Console App".lightMagenta() }
        println("Vidya Kotlin App Version 1.0".lightMagenta())
    }

 fun start() {
     var input: Int

     do {
         input = vidyaView.menu()
         when(input) {
             1 -> list()
             2 -> add()
             3 -> edit()
             4 -> delete()
             -1 -> println("Exiting App".lightMagenta())
             else -> println("Invalid Option".lightMagenta())
         }
         println()
     } while (input != -1)
     logger.info { "Shutting Down Vidya Console App".lightMagenta() }

 }

    fun menu() :Int { return vidyaView.menu() }

    fun add(){
        var aVidya = VidyaModel()

        if (vidyaView.addVidyaData(aVidya))
            games.create(aVidya)
        else
            logger.info("Game Not Added".lightMagenta())
    }

    fun list() {
        vidyaView.viewWishlist(games)
    }

    fun edit() {

        vidyaView.viewWishlist(games)
        var searchId = vidyaView.getId()
        val aVidya = search(searchId)

        if(aVidya != null) {
            if(vidyaView.updateVidyaData(aVidya)) {
                games.edit(aVidya)
                vidyaView.showGame(aVidya)
                logger.info("Game Updated : [ $aVidya ]".lightMagenta())
            }
            else
                logger.info("Game Not Updated".lightMagenta())
        }
        else
            println("Game Not Updated...".lightMagenta())
    }
}

fun delete() {
    vidyaView.viewWishlist(games)
    var searchId = vidyaView.getId()
    val aVidya = search(searchId)

    if(aVidya != null) {
        games.delete(aVidya)
        println("Game Deleted...".lightMagenta())
        vidyaView.viewWishlist(games)
    }
    else
        println("Game Not Deleted...".lightMagenta())
}

fun search() {
    val aVidya = search(vidyaView.getId())!!
    vidyaView.showGame(aVidya)
}

fun search(id: Long) : VidyaModel? {
    var foundGame = games.findOne(id)
    return foundGame
}