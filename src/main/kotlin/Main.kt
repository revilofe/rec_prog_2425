import com.revilofe.recetas.service.Recetario
import com.revilofe.recetas.ui.MenuConsola

fun main() {
    val recetario = Recetario()
    MenuConsola(recetario).iniciar()
}
