package org.wit.vidya.console.main

import mu.KotlinLogging
import org.wit.vidya.console.models.VidyaModel

private val logger = KotlinLogging.logger {}


val games = ArrayList<VidyaModel>()

fun main(args: Array<String>){
    logger.info { "Launching Placemark Console App" }
    println("Vidya Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> viewWishlist()
            2 -> addGame()
            3 -> editGame()
            4 -> deleteGame()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. View Wishlist")
    println(" 2. Add Game")
    println(" 3. Edit Game")
    println(" 4. Delete Game from Wishlist")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun viewWishlist() {
    println("View Wishlist")
    println()
    games.forEach { logger.info("${it}") }
}

fun addGame() {
    var aVidya = VidyaModel()

    println("Add Game")
    println()
    print("Enter Game Name : ")
    aVidya.name = readLine()!!
    print("Enter Developer of Game : ")
    aVidya.dev = readLine()!!

    if (aVidya.name.isNotEmpty() && aVidya.dev.isNotEmpty()) {
        aVidya.id = games.size.toLong()
        games.add(aVidya.copy())
        logger.info("Game Added : [ $aVidya ]")
    }
    else
        logger.info("Game Not Added")

}

fun editGame() {
    println("Edit Game")
    println()
    viewWishlist()
    var searchId = getId()
    val aVidya = search(searchId)
    var tempName: String?
    var tempDev: String?

    if (aVidya != null) {
        print("Enter a new name for [" + aVidya.name + " ] : ")
        tempName = readLine()!!
        print("Enter a new developer for [ " + aVidya.dev + " ] : ")
        tempDev = readLine()!!
        println("You updated [ " + aVidya.name + " ] for name and [ " + aVidya.dev + " ] for developer")


        if (!tempName.isNullOrEmpty() && !tempDev.isNullOrEmpty()) {
            aVidya.name = tempName
            aVidya.dev = tempDev
            println(
                "You updated [ " + aVidya.name + " ] for name " +
                        "and [ " + aVidya.dev + " ] for developer"
            )
            logger.info("Game Updated : [ $aVidya ]")
        } else
            println("Game not updated")
    }
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

fun search(id: Long) : VidyaModel? {
    var foundVidya: VidyaModel? = games.find { p -> p.id == id }
    return foundVidya
}