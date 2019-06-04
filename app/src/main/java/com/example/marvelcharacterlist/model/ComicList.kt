package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class ComicList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ComicSummary>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.createTypedArrayList(ComicSummary)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(available)
        parcel.writeValue(returned)
        parcel.writeString(collectionURI)
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicList> {
        override fun createFromParcel(parcel: Parcel): ComicList {
            return ComicList(parcel)
        }

        override fun newArray(size: Int): Array<ComicList?> {
            return arrayOfNulls(size)
        }
    }
}