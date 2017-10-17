
import javafx.application.Application
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import tornadofx.*
class MyApp: App(MyView::class)
class MyView: View() {
    override val root = VBox()
    init {

        val kittens = listOf("0.jpg") // more items here
                datagrid(kittens) {
            cellCache {
                imageview(it)
            }

        }
    }
}