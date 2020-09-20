package com.owulia.makekotlin.model

import android.os.Parcel
import android.os.Parcelable

data class WebViewOptionModel(
    val link: String,
    val title: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(link)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WebViewOptionModel> {
        override fun createFromParcel(parcel: Parcel): WebViewOptionModel {
            return WebViewOptionModel(parcel)
        }

        override fun newArray(size: Int): Array<WebViewOptionModel?> {
            return arrayOfNulls(size)
        }
    }
}