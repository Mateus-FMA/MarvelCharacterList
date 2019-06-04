package com.example.marvelcharacterlist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcharacterlist.R
import com.example.marvelcharacterlist.databinding.ActivityCharacterDetailBinding
import com.example.marvelcharacterlist.model.Character
import com.example.marvelcharacterlist.ui.viewmodel.adapter.SummaryAdapter

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<Character>(CHARACTER)
        val path = "${character?.thumbnail?.path}.${character?.thumbnail?.extension}"

        Glide.with(this)
            .load(path)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivCharThumbnail)

        binding.charName = character?.name
        binding.charDescription = if (character?.description?.isNotBlank() == true)
            character.description
        else
            getString(R.string.no_description_available)

        // Setup navigation bar back arrow.
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }


        // Setup empty list signs visibilities.
        binding.tvNoComics.visibility = if (character.comics?.items?.isNotEmpty() == true) View.GONE else View.VISIBLE
        binding.tvNoEvents.visibility = if (character.events?.items?.isNotEmpty() == true) View.GONE else View.VISIBLE
        binding.tvNoSeries.visibility = if (character.series?.items?.isNotEmpty() == true) View.GONE else View.VISIBLE
        binding.tvNoStories.visibility = if (character.stories?.items?.isNotEmpty() == true) View.GONE else View.VISIBLE

        // Setup recycler views.
        binding.rvComics.layoutManager = LinearLayoutManager(this)
        binding.rvComics.adapter = SummaryAdapter(character.comics?.items?.map { it.name.orEmpty() } ?: emptyList())
        binding.rvComics.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.rvEvents.layoutManager = LinearLayoutManager(this)
        binding.rvEvents.adapter = SummaryAdapter(character.events?.items?.map { it.name.orEmpty() } ?: emptyList())
        binding.rvEvents.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.rvSeries.layoutManager = LinearLayoutManager(this)
        binding.rvSeries.adapter = SummaryAdapter(character.series?.items?.map { it.name.orEmpty() } ?: emptyList())
        binding.rvSeries.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.rvStories.layoutManager = LinearLayoutManager(this)
        binding.rvStories.adapter = SummaryAdapter(character.stories?.items?.map { it.name.orEmpty() } ?: emptyList())
        binding.rvStories.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    companion object {

        private const val CHARACTER = "character"

        fun launch(context: Context, character: Character) {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(CHARACTER, character)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            context.startActivity(intent)
        }
    }
}