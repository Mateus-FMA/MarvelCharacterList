package com.example.marvelcharacterlist.core.app

import android.app.Application
import com.example.marvelcharacterlist.core.module.marvelModule
import org.koin.android.ext.android.startKoin

class MarvelCharacterListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(marvelModule))
    }

}