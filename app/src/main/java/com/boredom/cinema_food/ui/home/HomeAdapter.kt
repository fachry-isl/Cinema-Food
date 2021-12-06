package com.boredom.cinema_food.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.MovieEntity
import com.boredom.cinema_food.databinding.ItemMovieNowPlayingBinding
import com.boredom.cinema_food.ui.home.order.OrderActivity
import com.boredom.cinema_food.ui.home.order.OrderActivity.Companion.EXTRA_MOVIE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HomeAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<HomeAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()


    fun setMovies(list: List<MovieEntity>) {
        listMovies.clear()
        listMovies.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemMovieNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val items = listMovies[position]

        with(holder) {
            binding.tvMovieTitle.text = items.title
            Glide.with(itemView.context)
                .load(items.image)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.ivMovie)
            val remainedText = "${items.ticketRemaining} Remaining"
            binding.tvMovieRemaining.text = remainedText

            itemView.setOnClickListener {
                listener.onItemClick(items)
                val p0 = Intent(itemView.context, OrderActivity::class.java)
                p0.putExtra(EXTRA_MOVIE, items)
                itemView.context.startActivity(p0)
            }
        }
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(val binding: ItemMovieNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(items: MovieEntity)
    }
}