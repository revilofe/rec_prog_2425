A continuación se muestra un **esquema relacional en H2** (DDL) que cubre los requisitos mínimos del “Gestor de Recetas Light”, incluyendo los tres subtipos (`Entrante`, `Principal`, `Postre`) y la lista variable de ingredientes.

```sql
-- 1. Tabla principal: todas las recetas comparten los campos comunes
CREATE TABLE RECETAS (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(120)  NOT NULL,
    calorias      INT           NOT NULL CHECK (calorias > 0),
    es_vegana     BOOLEAN       NOT NULL,
    tipo          VARCHAR(12)   NOT NULL  -- 'ENTRANTE' | 'PRINCIPAL' | 'POSTRE'
);

-- 2. Subtabla para atributos EXTRA de cada subtipo
--    Estrategia “table-per-subtype” con PK = FK a RECETAS.id
CREATE TABLE ENTRANTES (
    id       INT PRIMARY KEY,
    es_frio  BOOLEAN NOT NULL,
    CONSTRAINT FK_ENTRANTE_RECETA FOREIGN KEY (id) REFERENCES RECETAS(id) ON DELETE CASCADE
);

CREATE TABLE PRINCIPALES (
    id        INT PRIMARY KEY,
    momento   VARCHAR(6) NOT NULL,         -- 'COMIDA' | 'CENA' | 'AMBOS'
    CONSTRAINT FK_PRINCIPAL_RECETA FOREIGN KEY (id) REFERENCES RECETAS(id) ON DELETE CASCADE
);

CREATE TABLE POSTRES (
    id        INT PRIMARY KEY,
    es_dulce  BOOLEAN NOT NULL,
    CONSTRAINT FK_POSTRE_RECETA FOREIGN KEY (id) REFERENCES RECETAS(id) ON DELETE CASCADE
);

-- 3. Tabla dependiente N..1 para ingredientes (uno a muchos)
CREATE TABLE INGREDIENTES (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    receta_id  INT          NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    CONSTRAINT FK_ING_RECETA FOREIGN KEY (receta_id) REFERENCES RECETAS(id) ON DELETE CASCADE
);

-- 4. Índices de ayuda para búsquedas habituales
CREATE INDEX IDX_RECETA_NOMBRE    ON RECETAS (LOWER(nombre));
CREATE INDEX IDX_RECETA_TIPO      ON RECETAS (tipo);
CREATE INDEX IDX_ING_RECETA_ID    ON INGREDIENTES (receta_id);
```

### Razones del diseño

| Elemento                                         | Justificación                                                                                                                    |
| ------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------- |
| **Tabla `RECETAS`**                              | Guarda los atributos comunes y el campo `tipo` permite discriminar el subtipo en consultas rápidas.                              |
| **Tablas `ENTRANTES`, `PRINCIPALES`, `POSTRES`** | Cada una contiene **solo** su atributo extra; se usa la estrategia *table-per-subtype* para evitar nulos y respetar la herencia. |
| **Tabla `INGREDIENTES`**                         | Relación 1\:N (una receta → muchos ingredientes). Se borra en cascada al eliminar la receta.                                     |
| **Índices**                                      | Búsqueda case-insensitive por nombre (`LOWER(nombre)`), filtrado por tipo y unión eficiente con `INGREDIENTES`.                  |
| **`ON DELETE CASCADE`**                          | Mantiene la integridad referencial al quitar una receta y sus dependientes.                                                      |

> Con este esquema puedes implementar los DAO (`RecetaDao`, `IngredienteDao…`) usando JDBC o la librería `org.h2.Driver`, y cubrir todas las operaciones CRUD exigidas por el Grupo 11.
