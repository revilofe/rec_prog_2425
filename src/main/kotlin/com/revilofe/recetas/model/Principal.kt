package com.revilofe.recetas.model

/**
 * Receta principal para comida o cena.
 */
class Principal(
    nombre: String,
    calorias: Int,
    ingredientes: List<String>,
    esVegana: Boolean,
    val momento: MomentoComida
) : Receta(nombre = nombre, calorias = calorias, ingredientes = ingredientes, esVegana = esVegana) {

    override fun descripcionBreve(): String = "$nombre para $momento"
}

enum class MomentoComida { COMIDA, CENA, AMBOS }
