package com.example.marvelcharacterlist.ui.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcharacterlist.R
import com.example.marvelcharacterlist.model.Character
import com.example.marvelcharacterlist.databinding.LayoutCharacterItemBinding
import com.example.marvelcharacterlist.ui.CharacterDetailActivity

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    var items = mutableListOf<Character?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            VIEW_ITEM -> {
                val binding = DataBindingUtil.inflate<LayoutCharacterItemBinding>(
                    inflater,
                    R.layout.layout_character_item,
                    parent,
                    false
                )

                return CharacterViewHolder(binding)
            }

            else -> {  // VIEW_PROGRESS_BAR
                return ViewHolder(inflater.inflate(R.layout.layout_progress_bar, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position]?.let { (holder as? CharacterViewHolder)?.bind(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.lastIndex) VIEW_PROGRESS_BAR else VIEW_ITEM
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class CharacterViewHolder(private val binding: LayoutCharacterItemBinding) : ViewHolder(binding.root) {

        fun bind(char: Character) {
            binding.charName = char.name
            val path = "${char.thumbnail?.path}.${char.thumbnail?.extension}"
            Glide.with(binding.root.context)
                .load(path)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivCharThumbnail)

            binding.root.setOnClickListener { CharacterDetailActivity.launch(binding.root.context, char) }
        }

    }


    companion object {

        private const val VIEW_ITEM = 0
        private const val VIEW_PROGRESS_BAR = 1

    }
}