package com.example.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapp.GetUpComing.Model.Movies.Details
import com.example.movieapp.GetUpComing.Model.Movies.Movies
import com.example.movieapp.GetUpComing.Model.Movies.UpComingMoviesResponse
import com.example.movieapp.GetUpComing.viewmodel.MoviesViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify


class UpComingsTesting {
    @get:Rule
    val InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var observer: Observer<UpComingMoviesResponse>

    @Mock
    private lateinit var upComingMovies: MutableLiveData<UpComingMoviesResponse>

    private var closeable: AutoCloseable? = null

    @Before
    fun setUp() {
        //Close
        closeable = MockitoAnnotations.openMocks(this)

        upComingMovies = MutableLiveData()

        //Mockito LiveData
        `when`(viewModel.upComingMovieLiveData).thenReturn(upComingMovies)

        // Observe with a mock observer
        upComingMovies.observeForever(observer)
    }

    @After
    fun close() {
        closeable?.close()
    }

    @Test
    fun testUpComingApi() {
        val category = listOf("Documentary", "Biography", "Crime")
        val staring = listOf("Nick Bailey", "Jo Anne Byrne", "Zhuang Cai", "Rachel Canton")
        val details = listOf(
            Details(
                category,
                "https://m.media-amazon.com/images/M/MV5BNmY3YzRiZDAtNmFkNS00ZjMzLThkOWEtMGQxZTMzOTdiZjQ2XkEyXkFqcGc@.300_.jpg",
                "https://www.imdb.com/title/tt23329452/?ref_=rlm",
                staring,
                "Starring Jerry as Himself (2023)"
            )
        )
        val movieList = listOf(Movies("Nov 06, 2024", details))
        val upComingMovie = UpComingMoviesResponse(movieList)
        upComingMovies.value = upComingMovie


        verify(observer).onChanged(upComingMovie)

    }

}