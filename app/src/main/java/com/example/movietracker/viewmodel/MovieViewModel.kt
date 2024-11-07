package com.example.movietracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.model.Movie
import com.example.movietracker.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel( private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    // Função para carregar filmes da API ou do banco de dados
    fun loadPopularMovies(apiKey: String, page: Int) {
        viewModelScope.launch {
            // Chama a função da Repository para pegar os filmes
            val movieList = movieRepository.getPopularMovies(apiKey, page)
            _movies.value = movieList // Atualiza a LiveData com os filmes
        }
    }

}