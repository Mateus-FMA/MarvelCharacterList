package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class CharacterDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<Character>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createTypedArrayList(Character)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(offset)
        parcel.writeValue(limit)
        parcel.writeValue(total)
        parcel.writeValue(count)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterDataContainer> {
        override fun createFromParcel(parcel: Parcel): CharacterDataContainer {
            return CharacterDataContainer(parcel)
        }

        override fun newArray(size: Int): Array<CharacterDataContainer?> {
            return arrayOfNulls(size)
        }
    }
}