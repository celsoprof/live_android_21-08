package br.senai.sp.jandira.livraria.repositorio

import android.content.Context
import br.senai.sp.jandira.livraria.dao.LivrariaDataBase
import br.senai.sp.jandira.livraria.model.Livro

class LivroRepositorio(context: Context) {

  private val db = LivrariaDataBase.getBancoDados(context).livroDao()

  fun salvar(livro: Livro): Long {
    return db.salvar(livro)
  }

  fun listarLivros(): List<Livro>{
    return db.listarLivros()
  }

  fun atualizar(livro: Livro): Int{
    return db.atualizar(livro)
  }

}