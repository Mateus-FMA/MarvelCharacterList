package com.example.marvelcharacterlist.core

import com.example.marvelcharacterlist.model.CharacterDataWrapper
import io.reactivex.Single
import java.util.*

interface MarvelAPI {

    fun getCharacters(name: String? = null,
                      nameStartsWith: String? = null,
                      modifiedSince: Date? = null,
                      comics: List<Int>? = null,
                      events: List<Int>? = null,
                      series: List<Int>? = null,
                      stories: List<Int>? = null,
                      orderBy: CharactersOrderByType? = null,
                      limit: Int? = null,
                      offset: Int? = null): Single<CharacterDataWrapper>

}