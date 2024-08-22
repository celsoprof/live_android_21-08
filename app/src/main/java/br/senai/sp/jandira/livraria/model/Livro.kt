package br.senai.sp.jandira.livraria.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_livro")
data class Livro(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val titulo: String = "",
  val autor: String = "",
  @ColumnInfo(name = "data_publicacao") val dataPublicacao: String = "",
  val valor: Double = 0.0,
  val usado: Boolean = false
)
