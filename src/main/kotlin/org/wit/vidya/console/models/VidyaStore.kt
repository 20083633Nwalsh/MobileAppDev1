package org.wit.vidya.console.models

interface VidyaStore {
    fun findAll(): List<VidyaModel>
    fun findOne(id: Long): VidyaModel?
    fun create(vidya: VidyaModel)
    fun update(vidya: VidyaModel)
}