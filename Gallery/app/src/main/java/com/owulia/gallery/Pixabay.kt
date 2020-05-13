package com.owulia.gallery

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Pixabay (
    val totalHits: Int,
    val hits: Array<PhotoItem>,
    val total: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (!hits.contentEquals(other.hits)) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + hits.contentHashCode()
        result = 31 * result + total
        return result
    }
}

@Parcelize data class PhotoItem (
    @SerializedName("id") val photoId: Int,
    @SerializedName("webformatURL") val previewUrl: String,
    @SerializedName("largeImageURL") val fullUrl: String,
    @SerializedName("likes") val photoLikes: Int,
    @SerializedName("favorites") val photoFavorites: Int,
    @SerializedName("user") val photoUser: String,
    @SerializedName("webformatHeight") val photoHeight: Int
): Parcelable