package org.wit.vidya.console.models

data class VidyaModel(      var id: Long = 0,
                            var name: String = "",
                            var dev: String = "",
                            var genre: String = "", //enumerated, pick a genre
                            var note: String = "")