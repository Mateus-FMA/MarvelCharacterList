package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable

data class CharacterDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: CharacterDataContainer?,
    val etag: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(CharacterDataContainer::class.java.classLoader),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(code)
        parcel.writeString(status)
        parcel.writeString(copyright)
        parcel.writeString(attributionText)
        parcel.writeString(attributionHTML)
        parcel.writeParcelable(data, flags)
        parcel.writeString(etag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterDataWrapper> {
        override fun createFromParcel(parcel: Parcel): CharacterDataWrapper {
            return CharacterDataWrapper(parcel)
        }

        override fun newArray(size: Int): Array<CharacterDataWrapper?> {
            return arrayOfNulls(size)
        }
    }
}