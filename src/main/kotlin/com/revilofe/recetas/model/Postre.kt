package com.revilofe.recetas.model

/**
 * Receta de postre.
 */
class Postre(
    nombre: String,
    calorias: Int,
    ingredientes: List<String>,
    esVegana: Boolean,
    val esDulce: Boolean
) : Receta(nombre = nombre, calorias = calorias, ingredientes = ingredientes, esVegana = esVegana) {

    override fun descripcionBreve(): String = "$nombre (${if (esDulce) "dulce" else "salado"})"

    override fun kcalPorRacion(): Int = calorias / 6
}
