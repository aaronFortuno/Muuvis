package aaronfortuno.ioc.muuvis.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val genre: String,
    val duration: Int,
    var year: Int,
    val description: String,
    val rating: Double,
    val isWatched: Boolean,
    val imageUrl: String
)