package org.wit.vidya.console.main

import mu.KotlinLogging
import org.wit.vidya.console.controllers.VidyaController
import org.wit.vidya.console.models.VidyaMemStore
import org.wit.vidya.console.models.VidyaModel
import org.wit.vidya.console.views.VidyaView


private val logger = KotlinLogging.logger {}


val games = VidyaMemStore()
val vidyaView = VidyaView()

fun main(args: Array<String>){
    logger.info { "Launching Vidya Console App" }
    println("Vidya Kotlin App Version 1.0")

    val controller = VidyaController()

    var input: Int

    do {
        input = vidyaView.menu()
        when(input) {
            1 -> vidyaView.viewWishlist(games)
            2 -> addGame()
            3 -> editGame()
            4 -> deleteGame()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Vidya Console App" }
}

/*
fun viewWishlist() {
    println("View Wishlist")
    println()
    games.forEach { logger.info("${it}") }
} */

fun addGame() {
    var aVidya = VidyaModel()

    if (vidyaView.addVidyaData(aVidya))
        games.create(aVidya)
    else
        logger.info("Game Not Added")

}

fun editGame() {

    vidyaView.viewWishlist(games)

    var searchId = vidyaView(games)
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


fun deleteGame() {

}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun searchVidya() {
    val aVidya = search(vidyaView.getId())!!
    vidyaView.showGame(aVidya)
}

fun search(id: Long) : VidyaModel? {
    var foundVidya: VidyaModel? = games.find { p -> p.id == id }
    return foundVidya
}