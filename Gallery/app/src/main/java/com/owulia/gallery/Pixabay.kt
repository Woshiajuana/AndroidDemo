package com.owulia.gallery

data class Pixabay (
    val totalHits: Int,
    val hits:Array<PhotoItem>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (!hits.contentEquals(other.hits)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + hits.contentHashCode()
        return result
    }
}

data class PhotoItem (
    val webformatURL:String,
    val id:Int,
    val largeImageURL:String
)