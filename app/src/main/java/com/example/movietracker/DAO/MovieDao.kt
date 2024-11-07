package com.example.movietracker.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movietracker.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie): Long

    // Atualizar informações de um filme favorito
    @Update
    suspend fun updateMovie(movie: Movie)

    // Deletar um filme dos favoritos
    @Delete
    suspend fun deleteMovie(movie: Movie)

    // Listar todos os filmes favoritos
    @Query("SELECT * FROM movies ORDER BY title ASC")
    suspend fun getAllMovies(): List<Movie>

    // Buscar um filme favorito por ID
    @Query("SELECT * FROM movies WHERE tmdbId = :tmdbId LIMIT 1")
    suspend fun getMovieById(tmdbId: Int): Movie?

    // Verificar se um filme está nos favoritos
    @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE tmdbId = :tmdbId)")
    suspend fun isFavorite(tmdbId: Int): Boolean
}