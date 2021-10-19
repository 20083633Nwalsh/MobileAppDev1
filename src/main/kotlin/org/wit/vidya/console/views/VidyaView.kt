package org.wit.vidya.console.views


import com.andreapivetta.kolor.lightBlue
import com.andreapivetta.kolor.lightWhite
import mu.KotlinLogging
import org.wit.vidya.console.models.VidyaJSONStore
import org.wit.vidya.console.models.VidyaMemStore // not used. JSON used instead
import org.wit.vidya.console.models.VidyaModel

private val logger = KotlinLogging.logger {}

class VidyaView {
    fun menu() : Int {

        var option : Int
        var input: String? = null


        println("Main Menu".lightWhite())
        println(" 1. View Wishlist".lightWhite())
        println(" 2. Add Game".lightWhite())
        println(" 3. Edit Game".lightWhite())
        println(" 4. Delete Game from Wishlist".lightWhite())
        println("-1. Exit".lightWhite())
        println()
        print("Select a menu number : ".lightBlue())
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun viewWishlist(games: VidyaJSONStore) {
        println("View Wishlist".lightWhite())
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
        print("Enter a Name : ".lightBlue())
        vidya.name = readLine()!!
        print("Enter a Developer : ".lightBlue())
        vidya.dev = readLine()!!
        print("Enter release Year : ".lightBlue())
        vidya.year = readLine()!!.toInt()
        print("Add a note (optional) : ".lightBlue())
        vidya.note = readLine()!!

        return vidya.name.isNotEmpty() && vidya.dev.isNotEmpty()  /* && vidya.year.isNotEmpty() */
    }

    fun updateVidyaData(vidya : VidyaModel) : Boolean {

        var tempName: String?
        var tempDev: String?
        var tempYear: Int?
        var tempNote: String?

        if (vidya != null) {
            print("Enter a new name for [".lightBlue() + vidya.name.lightBlue() + " ] : ".lightBlue())
            tempName = readLine()!!
            print("Enter a new developer for [ ".lightBlue() + vidya.dev.lightBlue() + " ] : ".lightBlue())
            tempDev = readLine()!!
            print("Enter a new year instead of [ ".lightBlue() + vidya.year + " ] : ".lightBlue())
            tempYear = readLine()!!.toInt()
            print("Enter a new note (optional) : ".lightBlue())
            tempNote = readLine()!!
            vidya.note = tempNote
            if (!tempName.isNullOrEmpty() && !tempDev.isNullOrEmpty()  /* && !tempYear.isNullOrEmpty() */ ) {
                vidya.name = tempName
                vidya.dev = tempDev
                vidya.year = tempYear
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id of game you wish to edit or delete : ".lightBlue())
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
    // Would have liked to have implemented a get a game by name function
/*
    fun getName() : String {
        var GetName : String? // String to hold user input
        print("Enter name of game to update : ")
        GetName = readLine()!!
        if (!GetName.isEmpty())
            GetName.toString()
        else
            -9
        return GetName
    }


 */

}