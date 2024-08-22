package br.senai.sp.jandira.livraria.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.livraria.model.Livro

@Database(entities = [Livro::class], version = 1)
abstract class LivrariaDataBase: RoomDatabase() {

  abstract fun livroDao(): LivroDao

  companion object{

    private lateinit var instancia: LivrariaDataBase

    fun getBancoDados(context: Context): LivrariaDataBase{

      if(!::instancia.isInitialized){
        instancia = Room
          .databaseBuilder(context, LivrariaDataBase::class.java, "livraria_db")
          .allowMainThreadQueries()
          .fallbackToDestructiveMigration()
          .build()
      }
      return instancia
    }

  }



}