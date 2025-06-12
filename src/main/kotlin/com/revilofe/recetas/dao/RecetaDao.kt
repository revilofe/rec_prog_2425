package com.revilofe.recetas.dao

import com.revilofe.recetas.model.Receta

/**
 * DAO para acceso a recetas.
 */
interface RecetaDao {
    fun crear(receta: Receta): Receta
    fun leer(id: Int): Receta?
    fun leerTodos(): List<Receta>
    fun actualizar(receta: Receta): Boolean
    fun borrar(id: Int): Boolean
}
