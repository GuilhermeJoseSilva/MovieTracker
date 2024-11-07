package com.example.movietracker.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movietracker.API.TmdbApi
import com.example.movietracker.DAO.MovieDao
import com.example.movietracker.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao, private val tmdbApi: TmdbApi) {
    suspend fun getPopularMovies(apiKey: String, page: Int): List<Movie> {
        // Tentamos buscar os filmes da API
        val response = tmdbApi.getPopularMovies(apiKey, page).execute()

        return if (response.isSuccessful && response.body() != null) {
            val movieResponse = response.body()!!
            // Salva os filmes no banco local (Room) se a requisição for bem-sucedida
            saveMovies(movieResponse.results)
            movieResponse.results
        } else {
            // Se falhar, tenta pegar os filmes do banco local
            getMoviesFromDatabase()
        }
    }

    // Função para pegar os filmes do banco local
    suspend fun getMoviesFromDatabase(): List<Movie> {
        return movieDao.getAllMovies()
    }

    // Função para salvar filmes no banco local
    private suspend fun saveMovies(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }


}