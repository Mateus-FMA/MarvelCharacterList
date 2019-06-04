package com.example.marvelcharacterlist.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val thumbnail: Image?,
    val comics: ComicList?,
    val events: EventList?,
    val series: SeriesList?,
    val stories: StoryList?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readSerializable() as? Date,
        parcel.readString(),
        parcel.createTypedArrayList(Url),
        parcel.readParcelable(Image::class.java.classLoader),
        parcel.readParcelable(ComicList::class.java.classLoader),
        parcel.readParcelable(EventList::class.java.classLoader),
        parcel.readParcelable(SeriesList::class.java.classLoader),
        parcel.readParcelable(StoryList::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeSerializable(modified)
        parcel.writeString(resourceURI)
        parcel.writeTypedList(urls)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeParcelable(comics, flags)
        parcel.writeParcelable(events, flags)
        parcel.writeParcelable(series, flags)
        parcel.writeParcelable(stories, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}