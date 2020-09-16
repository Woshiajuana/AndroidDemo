package com.owulia.makekotlin.model

import android.os.Parcel
import android.os.Parcelable

data class GuidePageModel(
    val isLast: Boolean = false,
    val index: Int,
    val bgColor: Int,
    val bannerIcon: Int,
    val textIcon: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isLast) 1 else 0)
        parcel.writeInt(index)
        parcel.writeInt(bgColor)
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