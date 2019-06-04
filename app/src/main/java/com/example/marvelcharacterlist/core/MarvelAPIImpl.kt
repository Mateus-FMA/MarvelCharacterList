package com.example.marvelcharacterlist.core

import com.example.marvelcharacterlist.core.retrofit.MarvelService
import com.example.marvelcharacterlist.model.CharacterDataWrapper
import io.reactivex.Single
import java.util.*

class MarvelAPIImpl(val service: MarvelService) :
    MarvelAPI {

    private fun stringifyIntegerList(list: List<Int>?): String? {
        return list?.let { it.fold("", { acc, n -> "$acc,$n" }) }
    }

    override fun getCharacters(name: String?,
                               nameStartsWith: String?,
                               modifiedSince: Date?,
                               comics: List<Int>?,
                               events: List<Int>?,
                               series: List<Int>?,
                               stories: List<Int>?,
                               orderBy: CharactersOrderByType?,
                               limit: Int?,
                               offset: Int?): Single<CharacterDataWrapper> {

        return service.getCharacters(
            name,
            nameStartsWith,
            modifiedSince,
            stringifyIntegerList(comics),
            stringifyIntegerList(events),
            stringifyIntegerList(series),
            stringifyIntegerList(stories),
            CharactersOrderByType.toQueryParam(orderBy),
            limit,
            offset
        )
    }
}