package com.example.marvelcharacterlist.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcharacterlist.core.CharactersOrderByType
import com.example.marvelcharacterlist.core.MarvelAPI
import com.example.marvelcharacterlist.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicBoolean

class MainViewModel(
    private val context: Context,
    private val marvelAPI: MarvelAPI
) : ViewModel(), Observable {

    private var offset = 0

    private val registry = PropertyChangeRegistry()
    private val disposables = CompositeDisposable()

    val charactersLiveData = MutableLiveData<MutableList<Character?>>()
    val loading = AtomicBoolean(false)

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    fun load(type: CharactersOrderByType = CharactersOrderByType.NAME, onRefresh: () -> Unit = {}) {
        disposables.add(
            marvelAPI.getCharacters(orderBy = type, offset = offset)
                .doOnSubscribe { loading.set(true) }
                .doFinally { loading.set(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val updatedItems = charactersLiveData.value?.take(offset)?.toMutableList() ?: mutableListOf()
                    updatedItems += it.data?.results?.toMutableList() ?: mutableListOf(null)
                    offset += it.data?.count ?: 0

                    charactersLiveData.value = updatedItems
                    onRefresh()
                }) {
                    Toast.makeText(context, "Could not load data (${it.message}).", Toast.LENGTH_SHORT).show()
                    onRefresh()
                }
        )
    }

    fun disposeAll() {
        disposables.clear()
    }

    fun reset() {
        offset = 0
    }

}