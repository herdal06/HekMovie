package com.herdal.moviehouse.ui.movie_details.view_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.herdal.moviehouse.ui.movie_details.movie_credits.MovieCreditsFragment
import com.herdal.moviehouse.ui.movie_details.recommended_movies.RecommendedMoviesFragment
import com.herdal.moviehouse.ui.movie_details.reviews.MovieReviewsFragment
import com.herdal.moviehouse.ui.movie_details.similar_movies.SimilarMoviesFragment

class MovieDetailsSlidePagerAdapter(
    fa: FragmentActivity,
    private val movieId: Int
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return MovieReviewsFragment(movieId = movieId)
            }
            1 -> {
                return SimilarMoviesFragment(movieId = movieId)
            }
            2 -> {
                return RecommendedMoviesFragment(movieId = movieId)
            }
            3 -> {
                return MovieCreditsFragment(movieId = movieId)
            }
        }
        return MovieReviewsFragment(movieId = movieId)
    }
}