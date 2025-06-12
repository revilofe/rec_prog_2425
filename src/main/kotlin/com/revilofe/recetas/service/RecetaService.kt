package com.revilofe.recetas.service

import com.revilofe.recetas.dao.RecetaDao
import com.revilofe.recetas.model.Receta

/**
 * Servicio que delega en un DAO para la persistencia.
 */
class RecetaService(private val dao: RecetaDao) {
    fun crear(receta: Receta): Receta = dao.crear(receta)
    fun obtenerTodos(): List<Receta> = dao.leerTodos()
    fun buscar(nombre: String): List<Receta> =
        dao.leerTodos().filter { it.nombre.contains(nombre, ignoreCase = true) }
    fun actualizar(receta: Receta): Boolean = dao.actualizar(receta)
    fun eliminar(id: Int): Boolean = dao.borrar(id)
}
