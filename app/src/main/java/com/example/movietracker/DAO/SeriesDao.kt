package com.example.movietracker.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movietracker.model.Series

@Dao
interface SeriesDao {

    // Inserir uma nova série como favorita
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: Series): Long

    // Atualizar informações de uma série favorita
    @Update
    suspend fun updateSeries(series: Series)

    // Deletar uma série dos favoritos
    @Delete
    suspend fun deleteSeries(series: Series)

    // Listar todas as séries favoritas
    @Query("SELECT * FROM series ORDER BY title ASC")
    suspend fun getAllSeries(): List<Series>

    // Buscar uma série favorita por ID
    @Query("SELECT * FROM series WHERE tmdbId = :tmdbId LIMIT 1")
    suspend fun getSeriesById(tmdbId: Int): Series?

    // Verificar se uma série está nos favoritos
    @Query("SELECT EXISTS(SELECT 1 FROM series WHERE tmdbId = :tmdbId)")
    suspend fun isFavorite(tmdbId: Int): Boolean

}