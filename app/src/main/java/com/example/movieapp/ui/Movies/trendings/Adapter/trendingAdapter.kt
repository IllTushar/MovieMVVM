package com.example.movieapp.ui.Movies.trendings.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.Movies.trendings.Model.trendingModel
import com.squareup.picasso.Picasso

class trendingAdapter(
    private val context: Context,
    private val dataList: MutableList<trendingModel>,
) :
    RecyclerView.Adapter<trendingAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.trendingMoviesImage)
        val title: TextView = itemView.findViewById(R.id.trendingMovieTitle)
        val rating: TextView = itemView.findViewById(R.id.trendingMovieIMDBRating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.trending_movies, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        Picasso.get().load(dataList[position].imageURL).placeholder(R.drawable.movies_icon)
            .resize(300, 300) // Example size, adjust as needed
            .centerCrop().into(holder.image)
        holder.title.text = dataList[position].title
        holder.rating.text = "IMDB Rating: ${dataList[position].imdbRating}"
    }
}