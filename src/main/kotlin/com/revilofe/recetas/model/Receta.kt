package com.revilofe.recetas.model

/**
 * Representa una receta de cocina.
 * @property id identificador único autogenerado
 * @property nombre nombre de la receta
 * @property calorias calorías totales
 * @property ingredientes lista de ingredientes
 * @property esVegana indica si es vegana
 */
open class Receta(
    val id: Int = generarId(),
    val nombre: String,
    val calorias: Int,
    val ingredientes: List<String>,
    val esVegana: Boolean
) : Calorico {

    open fun descripcionBreve(): String = nombre

    override fun kcalPorRacion(): Int = calorias / 4

    companion object {
        private var contador = 0
        fun generarId(): Int = ++contador
    }
}
