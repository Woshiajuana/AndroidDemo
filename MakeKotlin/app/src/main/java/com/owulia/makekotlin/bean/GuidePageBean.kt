package com.owulia.makekotlin.bean

import android.os.Parcel
import android.os.Parcelable

data class GuidePageBean(
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

    companion object CREATOR : Parcelable.Creator<GuidePageBean> {
        override fun createFromParcel(parcel: Parcel): GuidePageBean {
            return GuidePageBean(parcel)
        }

        override fun newArray(size: Int): Array<GuidePageBean?> {
            return arrayOfNulls(size)
        }
    }
}