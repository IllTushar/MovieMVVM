package com.example.movieapp.ui.Movies.upcomings.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.example.movieapp.R
import com.example.movieapp.ui.Movies.upcomings.Model.UpComingMoviesObject
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MovieAdapter(
    private val context: Context,
    private val data: MutableList<UpComingMoviesObject>,
) :
    RecyclerView.Adapter<MovieAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movies: CircleImageView = itemView.findViewById(R.id.upcomingMovies)
        val title = itemView.findViewById<TextView>(R.id.movieTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.up_coming_movies, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        Picasso.get().load(data[position].imageUrl).placeholder(R.drawable.movies_icon)
            .into(holder.movies)
        val StringTitle = data[position].title
        val parts = StringTitle.split("(")
        holder.title.text = parts[0].trim()

    }
}