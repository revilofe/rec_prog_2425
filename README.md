# Recuperación del módulo de programación 2425

---
## Instrucciones de recuperación del módulo de programación

A continuación podras leer las instrucciones para realizar la recuperación del módulo de **programación**:

Existen 8 ejercicios para la recuperación del módulo de programación, en función de los resultados de aprendizaje que no hayas superado en las evaluaciones anteriores, tendrás que realizar unos ejercicios u otros.

La siguientes tabla indica el ejercicio que debes realizar en función del RA que no hayas superado:

| RA   | Ejercicios | Grupo Criterial Asociado  |
|------|------------|---------------------------|
| RA9  | 8          | 11                        |
| RA7  | 7, 4       | 3, 10                     |
| RA6  | 5          | 9                         |
| RA5  | 6          | 7                         |
| RA4  | 2, 7       | 4, 10                     |
| RA3  | 4, 3       | 3, 5, 6                   |
| RA2  | 1, 2       | 1, 4                      |
| RA1  | 1          | 1, 2, 3                   |

Ten en cuenta lo siguiente:

- Lee atentamente todos los ejercicios que te pertenecen realizar, ya que si escoges el orden correcto de desarrollo, podrás reutilizar código y será mucho más rápido completarlos todos, incluyo de una sola vez.

- Lo normal es que si tienes que recuperar varios RA (por ejemplo RA9 y RA3), empieces por el ejercicio de mayor número (8), previamente habiendo revisado la descripción del resto de ejercicios que tienes que hacer (8, 4 y 3). Posiblemente eso te permita realizar la implementación de todo de una sola vez y ahorrar tiempo y esfuerzo. 

- Ten en cuenta que las **respuestas a las preguntas tendrán que realizarse en el README.md y para cada uno de los ejercicios que te correspondan**. Por ejemplo, si tienes que realizar los ejercicios 8, 4 y 3, tendrás que responder a las preguntas de evaluación en el README.md del ejercicio 8, del ejercicio 4 y del ejercicio 3.1 

- Los ejemplos de las respuestas a las preguntas de evaluación son **de otro dominio (gestor de cursos)** y sirven solo para dar una idea, pero se espera **MAYOR DETALLE** en las respuestas.

- Las respuestas a las preguntas de evaluación deben incluir **enlaces directos a los fragmentos de código** relevantes en tu repositorio.

- La no respuesta a las preguntas supondrá no evaluar ese apartado, por lo que es importante que respondas a todas las preguntas de forma completa y detallada y con enlaces directos a los fragmentos de código relevantes.

- Lee las instrucciones de cada ejercicio detenidamente, ya que cada uno tiene sus propias particularidades y requisitos.
    - Requisitos de código
    - Condiciones de entrega
    - Preguntas de evaluación

- Muy importante : 
    - Evidencias de ejecución de las funcionalidades solicitadas (**capturas de pantalla o GIFs**).
    - **Enlaces a fragmentos de código** relevantes en tu repositorio.

## Lista de ejercicios
* [Ejercicio 1](1.md) - Criterio 1: **Evalúa RA1, RA2 y RA3** | Kotlin y IntelliJ IDEA   
* [Ejercicio 2](2.md) - Criterio 4: **Evalúa RA2 y RA4** | Fundamentos de POO usando Kotlin    
* [Ejercicio 3](3.md) - Criterio 5 y 6: **Evalúa RA3** |  Estructuras de Control y Gestión de Errores en Kotlin
* [Ejercicio 4](4.md) - Criterio 3: **Evalúa RA1, RA3 y RA7** | Documentación y Comentarios del Código en Kotlin
* [Ejercicio 5](5.md) - Criterio 9: **Evalúa RA6** | Colecciones avanzadas, Genéricos e Iteradores en Kotlin
* [Ejercicio 6](6.md) - Criterio 7: **Evalúa RA5** | Entrada/Salida por consola y ficheros en Kotlin
* [Ejercicio 7](7.md) - Criterio 10: **Evalúa RA7** | POO Avanzada con Herencia e Interfaces en Kotlin
* [Ejercicio 8](8.md) - Criterio 11: **Evalúa RA9** | Acceso a Datos con DAO usando Kotlin



---
A continuación tienes un ejemplo de cómo debe quedar el README.md de tu proyecto, una vez que hayas completado los ejercicios y respondido a las preguntas de evaluación.

# Resolución del ejercicio de recuperación del módulo de programación

## Identificación del alumno

- **Nombre del alumno**: [Tu Nombre Aquí]    
- **Grupo**: [Tu Grupo Aquí]    
- **Resultados de Aprendizaje no superados**: [RA9, RA3, ...] (indica los RA que no has superado)    
- **Ejercicios a realizar**: [8, 4, 3,...] (indica los ejercicios que vas a realizar)    

---

## Ejercicio 8:

### Criterio 9 — Manipulación de Información y Tipos de Datos Avanzados

**9.1** Enlaza la declaración del código en el que realizas la validación mediante expresiones regulares del numero de recetas por páginas y explica en detalle la lógica de cada caso.

> **Ejemplo de respuesta (tema distinto – Inventario de libros):**
> «En **LibraryCLI** uso `val cmdRegex = Regex("^(l|t|\\d+)$")` ([enlace](https://github.com/.../Menu.kt#L40-L45)). “l” = listado simple; “t” = todo; `\\d+` = tamaño de página para libros.»

---

**9.2** Enlaza la definición de tu clase genérica `Paginador....`, en concreto a  `totalPaginas()` y explica como has implementado este método, y si has usado algúna operaciones agregadas o función de orden superior?

> **Ejemplo (tema distinto – Catálogo de películas):**
> «`class PageHelper<T>`  calcula `ceil(films.size / pageSize.toDouble())` para incluir la página incompleta cuando quedan películas sueltas.»


---

**9.3** Muestra el recorrido de una página con un `for`- in y explica el contexto.

> **Ejemplo (tema distinto – Gestor de tareas):**
>
> ```kotlin
> for (task in page) println(task.title)
> ```
>
> «Imprime las tareas al listar en modo simple» .


---

**9.4** Justifica la elección entre `MutableList` y `MutableMap` en el `Recetario`.

> **Ejemplo (tema distinto – Agenda de contactos):**
> «`contactsList` guarda orden; `contactsIndex` permite búsqueda O(1) por teléfono».»


---

**9.5** Enlaza la función que calcula estadísticas usando `filter`, `map`, `average`, `count` y resume lo que devuelve y el uso de funciones de orden superior o operaciones agregadas.

> **Ejemplo (tema distinto – App fitness):**
> «`calcularResumenSemanal()` usa `steps.map { it.día }.average()` .»


---

## Ejercicio 4:

[...]

---

## Ejercicio 3:

[...]
