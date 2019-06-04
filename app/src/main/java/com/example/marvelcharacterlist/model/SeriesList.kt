package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class SeriesList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<SeriesSummary>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.createTypedArrayList(SeriesSummary)
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

    companion object CREATOR : Parcelable.Creator<SeriesList> {
        override fun createFromParcel(parcel: Parcel): SeriesList {
            return SeriesList(parcel)
        }

        override fun newArray(size: Int): Array<SeriesList?> {
            return arrayOfNulls(size)
        }
    }
}