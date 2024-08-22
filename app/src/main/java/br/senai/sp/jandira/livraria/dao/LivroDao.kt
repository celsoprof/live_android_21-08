package br.senai.sp.jandira.livraria.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.senai.sp.jandira.livraria.model.Livro

@Dao
interface LivroDao {

  @Insert
  fun salvar(livro: Livro): Long

  @Query("SELECT * FROM tbl_livro ORDER BY titulo ASC")
  fun listarLivros(): List<Livro>

  @Update
  fun atualizar(livro: Livro): Int

}