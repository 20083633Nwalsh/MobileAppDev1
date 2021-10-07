package org.wit.vidya.console.main

import mu.KotlinLogging
import org.wit.vidya.console.models.VidyaModel

private val logger = KotlinLogging.logger {}

var vidya = VidyaModel()

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
}

fun addGame() {

    println("Add Game")
    println()
    print("Enter Game Name : ")
    vidya.name = readLine()!!
    print("Enter Developer of Game : ")
    vidya.dev = readLine()!!
    println("You entered [ " + vidya.name + " ] for name, [ " + vidya.dev + " ] for developer")
}

fun editGame() {
    println("Edit Game")
    println()
    print("Enter a new name for [" + vidya.name + " ] : ")
    vidya.name = readLine()!!
    print("Enter a new developer for [ " + vidya.dev + " ] : ")
    vidya.dev = readLine()!!
    println("You updated [ " + vidya.name + " ] for name and [ " + vidya.dev + " ] for developer")
}


fun deleteGame() {

}