package com.example.marvelcharacterlist.core.retrofit

import com.example.marvelcharacterlist.model.CharacterDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MarvelService {

    @GET("characters")
    fun getCharacters(@Query("name") name: String?,
                      @Query("nameStartsWith") nameStartsWith: String?,
                      @Query("modifiedSince") modifiedSince: Date?,
                      @Query("comics") comics: String?,
                      @Query("events") events: String?,
                      @Query("series") series: String?,
                      @Query("stories") stories: String?,
                      @Query("orderBy") orderBy: String?,
                      @Query("limit") limit: Int?,
                      @Query("offset") offset: Int?): Single<CharacterDataWrapper>

}