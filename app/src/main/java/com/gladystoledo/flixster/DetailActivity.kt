package com.gladystoledo.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView

private const val YOUTUBE_API = "AIzaSyApIEulhuxkC2_GMxl97vgxJZnnaHj7kW8"
private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var rbVoteAvg: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        tvTitle = findViewById(R.id.tvTitle)
        tvOverview = findViewById(R.id.tvOverview)
        rbVoteAvg = findViewById(R.id.rbVoteAvg)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie
        Log.i(TAG,"Movie is $movie")
        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        rbVoteAvg.rating = movie.voteAvg.toFloat()
    }
}