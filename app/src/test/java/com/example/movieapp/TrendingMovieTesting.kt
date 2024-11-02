package com.example.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapp.GetUpComing.Model.Trending.Movie
import com.example.movieapp.GetUpComing.Model.Trending.TrendingResponse
import com.example.movieapp.GetUpComing.viewmodel.MoviesViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TrendingMovieTesting {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    private lateinit var trendingMoviesLiveData: MutableLiveData<TrendingResponse>
    private var closeable: AutoCloseable? = null

    @Before
    fun setUp() {
        // Use openMocks to initialize mocks
        closeable = MockitoAnnotations.openMocks(this)
        trendingMoviesLiveData = MutableLiveData()

        // Mocking LiveData from ViewModel
        `when`(viewModel.trendingMovieLiveData).thenReturn(trendingMoviesLiveData)

        trendingMoviesLiveData.observeForever({ observer.onChanged(it.movies) })
    }

    @After
    fun tearDown() {
        // Close mocks if they were initialized
        closeable?.close()
    }

    @Test
    fun `when trending movies data is available, update adapter with movies`() {
        val trendingMovies = listOf(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BYzliNzRjNDMtYTFmOS00NDQxLWJlOWMtZTViNjcyMzc0NzQwXkEyXkFqcGc@.300_.jpg",
                "6.6 (26K)",
                "https://www.imdb.com/title/tt7737800/?ref_=chtmvm_i_1",
                "1h 35m",
                "Woman of the Hour",
                "2023"
            )
        )

        val trendingResponse = TrendingResponse(trendingMovies)

        // Act: Set data and trigger the observer
        trendingMoviesLiveData.value = trendingResponse


        // Assert: Verify observer was triggered with correct data
        verify(observer).onChanged(trendingMovies)
    }
}
