package org.wit.vidya.console.views

import mu.KotlinLogging

import org.wit.vidya.console.main.vidyaView
import org.wit.vidya.console.main.games

import org.wit.vidya.console.models.VidyaMemStore
import org.wit.vidya.console.models.VidyaModel

private val logger = KotlinLogging.logger {}

class VidyaView {
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

    fun viewWishlist(games : VidyaMemStore) {
        println("View Wishlist")
        println()
        games.logAll()
        println()
    }

    fun showGame(vidya : VidyaModel) {
        if(vidya != null)
            println("Game Details [ $vidya ]")
        else
            println("Game Not Found...")
    }

    fun addVidyaData(vidya : VidyaModel) : Boolean {
        println()
        print("Enter a Name : ")
        vidya.name = readLine()!!
        print("Enter a Developer : ")
        vidya.dev = readLine()!!

        return vidya.name.isNotEmpty() && vidya.dev.isNotEmpty()
    }

    fun updateVidyaData(vidya : VidyaModel) : Boolean {

        var tempName: String?
        var tempDev: String?

        // GENRE AND NOTES

        if (vidya != null) {
            print("Enter a new name for [" + vidya.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new developer for [ " + vidya.dev + " ] : ")
            tempDev = readLine()!!

            if (!tempName.isNullOrEmpty() && !tempDev.isNullOrEmpty()) {
                vidya.name = tempName
                vidya.dev = tempDev
                return true
                // ADD GENRE AND NOTES HERE
            }
        }
        return false
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

}