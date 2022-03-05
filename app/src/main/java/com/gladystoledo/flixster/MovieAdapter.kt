package com.gladystoledo.flixster

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "MovieAdapter"
class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position: $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            val imageURL: String
            imageURL = if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                movie.backdropImageURL
            }else{
                movie.posterImageURL
            }
            val radius = 30 // corner radius, higher value = more rounded
            val margin = 2 // crop margin, set to 0 for corners with no crop
            Glide.with(context)
                .load(imageURL)
                .placeholder(R.drawable.ic_nocover)
                .centerCrop() // scale image to fill the entire ImageView
                .transform(RoundedCornersTransformation(radius, margin))
                .into(ivPoster)
        }

        override fun onClick(v: View?) {
            //1. Get Notified of hte particular movie which was clicked
            val movie = movies[adapterPosition]

            //2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

}
