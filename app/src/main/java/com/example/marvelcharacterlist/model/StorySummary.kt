package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class StorySummary(
    val resourceURI: String?,
    val name: String?,
    val type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(resourceURI)
        parcel.writeString(name)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StorySummary> {
        override fun createFromParcel(parcel: Parcel): StorySummary {
            return StorySummary(parcel)
        }

        override fun newArray(size: Int): Array<StorySummary?> {
            return arrayOfNulls(size)
        }
    }
}