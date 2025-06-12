package com.revilofe.recetas.model

/**
 * Receta de tipo entrante.
 */
class Entrante(
    nombre: String,
    calorias: Int,
    ingredientes: List<String>,
    esVegana: Boolean,
    val esFrio: Boolean
) : Receta(nombre = nombre, calorias = calorias, ingredientes = ingredientes, esVegana = esVegana) {

    override fun descripcionBreve(): String = "$nombre (entrante ${if (esFrio) "frio" else "caliente"})"
}
