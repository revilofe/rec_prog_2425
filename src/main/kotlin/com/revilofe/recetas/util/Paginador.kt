package com.revilofe.recetas.util

/**
 * Paginador genérico para colecciones.
 */
class Paginador<T>(private val elementos: List<T>, private val tamPagina: Int) : Iterable<List<T>> {
    init {
        require(tamPagina > 0) { "Tamaño de página debe ser > 0" }
    }

    /** Número total de páginas. */
    fun totalPaginas(): Int =
        kotlin.math.ceil(elementos.size / tamPagina.toDouble()).toInt()

    /** Obtiene la página indicada (0..n). */
    fun pagina(num: Int): List<T> {
        val desde = num * tamPagina
        if (desde >= elementos.size) return emptyList()
        val hasta = kotlin.math.min(desde + tamPagina, elementos.size)
        return elementos.subList(desde, hasta)
    }

    override fun iterator(): Iterator<List<T>> =
        (0 until totalPaginas()).asSequence().map { pagina(it) }.iterator()
}
