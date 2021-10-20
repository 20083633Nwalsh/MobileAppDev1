package org.wit.vidya.console.models

data class VidyaModel(      var id: Int = 0,    // Would like to have used short
                            var name: String = "",
                            var dev: String = "",
                            var year: Int = 0,
                           /* var genre: String "",
                           Would have liked to have a "select a genre" and have used numbers for the selection but was not sure how to go about this
                            */
                            var note: String = "")