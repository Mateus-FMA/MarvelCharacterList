package com.example.marvelcharacterlist.core

enum class CharactersOrderByType {
    MODIFIED,
    MODIFIED_DESC,
    NAME,
    NAME_DESC;

    companion object {
        private val stringMap = mapOf(
            Pair(MODIFIED, "modified"),
            Pair(MODIFIED_DESC, "-modified"),
            Pair(NAME, "name"),
            Pair(NAME_DESC, "-name")
        )

        fun toQueryParam(type: CharactersOrderByType?): String? {
            return type?.let { stringMap.getValue(it) }
        }
    }
}