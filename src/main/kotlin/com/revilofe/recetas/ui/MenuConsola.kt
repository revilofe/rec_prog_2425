package com.revilofe.recetas.ui

import com.revilofe.recetas.model.Receta
import com.revilofe.recetas.service.Recetario
import com.revilofe.recetas.util.Paginador

/**
 * Menú interactivo para gestionar recetas.
 */
class MenuConsola(private val servicio: Recetario) {

    fun iniciar() {
        var opcion: Int
        do {
            mostrarMenu()
            opcion = readln().toIntOrNull() ?: -1
            when (opcion) {
                1 -> crearReceta()
                2 -> listarRecetas()
                3 -> buscarRecetas()
                4 -> eliminarReceta()
                5 -> estadisticas()
            }
        } while (opcion != 0)
    }

    private fun mostrarMenu() {
        println("""
            ┌─ Gestor de Recetas ───────┐
            │ 1) Añadir receta          │
            │ 2) Listar recetas         │
            │ 3) Buscar receta          │
            │ 4) Eliminar por id        │
            │ 5) Estadísticas           │
            │ 0) Salir                  │
            └───────────────────────────┘
        """.trimIndent())
        print("Opción: ")
    }

    private fun crearReceta() {
        print("Nombre: "); val nombre = readln()
        print("Calorías: "); val cal = readln().toInt()
        print("Ingredientes (coma): "); val ing = readln().split(',')
        print("Es vegana (true/false): "); val veg = readln().toBoolean()
        servicio.agregar(Receta(nombre = nombre, calorias = cal, ingredientes = ing, esVegana = veg))
    }

    private fun listarRecetas() {
        val lista = servicio.listar()
        val paginador = Paginador(lista, 5)
        for (pagina in paginador) {
            pagina.forEach { r ->
                println("${r.id} | ${r.nombre.padEnd(20)} | ${r.calorias} | ${if (r.esVegana) "Sí" else "No"}")
            }
            if (paginador.totalPaginas() > 1) {
                println("--Pulsa Enter para continuar--"); readln()
            }
        }
    }

    private fun buscarRecetas() {
        print("Texto a buscar: ")
        val texto = readln()
        servicio.buscar(texto).forEach {
            println("${it.id} - ${it.nombre}")
        }
    }

    private fun eliminarReceta() {
        print("ID a eliminar: ")
        val id = readln().toInt()
        if (servicio.eliminar(id)) println("Eliminada") else println("No encontrada")
    }

    private fun estadisticas() {
        val e = servicio.estadisticas()
        println("Total: ${e.total}, Media calorías: ${e.mediaCalorias}, Veganas: ${e.veganas}")
    }
}
