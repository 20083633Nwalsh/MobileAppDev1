package org.wit.vidya.console.models

interface VidyaStore {
    fun findAll(): List<VidyaModel>
    fun findOne(id: Int): VidyaModel?
    fun create(vidya: VidyaModel)
    fun edit(vidya: VidyaModel)
    fun delete(vidya: VidyaModel)
}