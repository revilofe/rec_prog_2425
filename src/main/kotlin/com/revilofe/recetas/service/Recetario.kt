package com.revilofe.recetas.service

import com.revilofe.recetas.model.Receta
import java.io.File

/**
 * Servicio CRUD en memoria para manejar recetas.
 */
class Recetario {
    private val recetas = mutableListOf<Receta>()

    fun agregar(receta: Receta) {
        recetas += receta
    }

    fun listar(): List<Receta> = recetas.toList()

    fun buscar(nombre: String): List<Receta> =
        recetas.filter { it.nombre.contains(nombre, ignoreCase = true) }

    fun eliminar(id: Int): Boolean = recetas.removeIf { it.id == id }

    fun estadisticas(): Estadisticas {
        val total = recetas.size
        val media = recetas.map { it.calorias }.average().toInt()
        val veganas = recetas.count { it.esVegana }
        return Estadisticas(total, media, veganas)
    }

    fun cargarDesdeTsv(ruta: String) {
        File(ruta).useLines { lines ->
            lines.drop(1).forEach { linea ->
                val campos = linea.split("\t")
                if (campos.size >= 5) {
                    agregar(
                        Receta(
                            nombre = campos[1],
                            calorias = campos[2].toInt(),
                            esVegana = campos[3].toBooleanStrictOrNull() ?: false,
                            ingredientes = campos[4].split(',')
                        )
                    )
                }
            }
        }
    }

    fun guardarEnTsv(ruta: String) {
        File(ruta).bufferedWriter().use { out ->
            out.appendLine("id\tnombre\tcalorias\tesVegana\tingredientes(coma_sep)")
            recetas.forEach { r ->
                out.appendLine("${r.id}\t${r.nombre}\t${r.calorias}\t${r.esVegana}\t${r.ingredientes.joinToString(",")}")
            }
        }
    }
}

/**
 * Estadísticas simples del recetario.
 */
data class Estadisticas(val total: Int, val mediaCalorias: Int, val veganas: Int)
