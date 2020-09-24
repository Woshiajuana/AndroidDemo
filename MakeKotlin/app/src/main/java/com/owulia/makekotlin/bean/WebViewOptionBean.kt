package com.owulia.makekotlin.bean

import android.os.Parcel
import android.os.Parcelable

data class WebViewOptionBean(
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

    companion object CREATOR : Parcelable.Creator<WebViewOptionBean> {
        override fun createFromParcel(parcel: Parcel): WebViewOptionBean {
            return WebViewOptionBean(parcel)
        }

        override fun newArray(size: Int): Array<WebViewOptionBean?> {
            return arrayOfNulls(size)
        }
    }
}