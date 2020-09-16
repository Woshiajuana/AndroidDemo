package com.owulia.makekotlin.model

import android.os.Parcel
import android.os.Parcelable

data class GuidePageModel(
    val bannerIcon: Int,
    val textIcon: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bannerIcon)
        parcel.writeInt(textIcon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GuidePageModel> {
        override fun createFromParcel(parcel: Parcel): GuidePageModel {
            return GuidePageModel(parcel)
        }

        override fun newArray(size: Int): Array<GuidePageModel?> {
            return arrayOfNulls(size)
        }
    }
}