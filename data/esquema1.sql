### Esquema H2 para la versión **básica** con una única entidad `Receta`

Cuando el dominio aún no distinguía subclases de plato, basta un modelo relacional compacto compuesto por la tabla principal de recetas y una tabla dependiente para los ingredientes.

```sql
------------------------------------------------------------
-- 1. Tabla principal: RECETAS
------------------------------------------------------------
CREATE TABLE RECETAS (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(120)  NOT NULL,
    calorias   INT           NOT NULL CHECK (calorias > 0),
    es_vegana  BOOLEAN       NOT NULL
);

------------------------------------------------------------
-- 2. Tabla dependiente 1-a-N: INGREDIENTES
------------------------------------------------------------
CREATE TABLE INGREDIENTES (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    receta_id   INT          NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    CONSTRAINT FK_ING_RECETA
        FOREIGN KEY (receta_id)
        REFERENCES RECETAS(id)
        ON DELETE CASCADE
);

------------------------------------------------------------
-- 3. Índices de apoyo para consultas frecuentes
------------------------------------------------------------
CREATE INDEX IDX_RECETA_NOMBRE  ON RECETAS (LOWER(nombre));
CREATE INDEX IDX_ING_RECETA_ID  ON INGREDIENTES (receta_id);
```

#### Puntos clave del diseño

| Aspecto                    | Explicación                                                                                                |
| -------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **`RECETAS`**              | Alberga todos los atributos relevantes: id autoincremental, nombre, calorías y flag vegano.                |
| **`INGREDIENTES`**         | Permite un número variable de ingredientes por receta sin duplicar columnas en la tabla principal.         |
| **`ON DELETE CASCADE`**    | Garantiza la consistencia: al eliminar una receta, sus ingredientes asociados se eliminan automáticamente. |
| **Índice `LOWER(nombre)`** | Facilita búsquedas insensibles a mayúsculas cuando se requieren filtros por nombre.                        |

Con este esquema puedes implementar un DAO único (`RecetaDao`) que gestione la tabla `RECETAS` y, dentro de sus operaciones de creación/lectura, inserte o lea los registros correspondientes en `INGREDIENTES` para reconstruir la lista de ingredientes en memoria. De esta forma se cubren todas las operaciones CRUD exigidas por el Grupo 11 sin necesidad de jerarquías adicionales.
