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
    VidyaController().start()
}

