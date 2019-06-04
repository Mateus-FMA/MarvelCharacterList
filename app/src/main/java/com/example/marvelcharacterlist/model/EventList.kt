package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class EventList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<EventSummary>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.createTypedArrayList(EventSummary)
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

    companion object CREATOR : Parcelable.Creator<EventList> {
        override fun createFromParcel(parcel: Parcel): EventList {
            return EventList(parcel)
        }

        override fun newArray(size: Int): Array<EventList?> {
            return arrayOfNulls(size)
        }
    }
}