package com.revilofe.recetas.dao

import com.revilofe.recetas.model.Receta
import java.sql.Connection
import java.sql.DriverManager

/**
 * Implementación JDBC con H2.
 */
class RecetaDaoH2(url: String) : RecetaDao {
    private val conn: Connection = DriverManager.getConnection(url)

    init {
        conn.createStatement().use { st ->
            st.executeUpdate(
                """CREATE TABLE IF NOT EXISTS recetas(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR NOT NULL UNIQUE,
                    calorias INT NOT NULL,
                    ingredientes VARCHAR NOT NULL,
                    esVegana BOOLEAN NOT NULL
                )"""
            )
        }
    }

    override fun crear(receta: Receta): Receta {
        val sql = "INSERT INTO recetas(nombre,calorias,ingredientes,esVegana) VALUES(?,?,?,?)"
        val ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
        ps.setString(1, receta.nombre)
        ps.setInt(2, receta.calorias)
        ps.setString(3, receta.ingredientes.joinToString(","))
        ps.setBoolean(4, receta.esVegana)
        ps.executeUpdate()
        val rs = ps.generatedKeys
        val id = if (rs.next()) rs.getInt(1) else receta.id
        return Receta(
            id = id,
            nombre = receta.nombre,
            calorias = receta.calorias,
            ingredientes = receta.ingredientes,
            esVegana = receta.esVegana
        )
    }

    override fun leer(id: Int): Receta? {
        val ps = conn.prepareStatement("SELECT * FROM recetas WHERE id=?")
        ps.setInt(1, id)
        val rs = ps.executeQuery()
        return if (rs.next())
            Receta(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre"),
                calorias = rs.getInt("calorias"),
                ingredientes = rs.getString("ingredientes").split(','),
                esVegana = rs.getBoolean("esVegana")
            )
        else null
    }

    override fun leerTodos(): List<Receta> {
        val rs = conn.createStatement().executeQuery("SELECT * FROM recetas")
        val lista = mutableListOf<Receta>()
        while (rs.next()) {
            lista += Receta(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre"),
                calorias = rs.getInt("calorias"),
                ingredientes = rs.getString("ingredientes").split(','),
                esVegana = rs.getBoolean("esVegana")
            )
        }
        return lista
    }

    override fun actualizar(receta: Receta): Boolean {
        val ps = conn.prepareStatement("UPDATE recetas SET nombre=?, calorias=?, ingredientes=?, esVegana=? WHERE id=?")
        ps.setString(1, receta.nombre)
        ps.setInt(2, receta.calorias)
        ps.setString(3, receta.ingredientes.joinToString(","))
        ps.setBoolean(4, receta.esVegana)
        ps.setInt(5, receta.id)
        return ps.executeUpdate() > 0
    }

    override fun borrar(id: Int): Boolean {
        val ps = conn.prepareStatement("DELETE FROM recetas WHERE id=?")
        ps.setInt(1, id)
        return ps.executeUpdate() > 0
    }
}
