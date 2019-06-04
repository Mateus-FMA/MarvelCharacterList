package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class EventSummary(
    val resourceURI: String?,
    val name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(resourceURI)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventSummary> {
        override fun createFromParcel(parcel: Parcel): EventSummary {
            return EventSummary(parcel)
        }

        override fun newArray(size: Int): Array<EventSummary?> {
            return arrayOfNulls(size)
        }
    }
}