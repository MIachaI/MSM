package app

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet(){
    companion object {
        val simulate by cssclass()
        val simulateWidth = 400.px

    }

    init{
        form and simulate {
            padding = box(16.px)
            fontSize = 13.px
            fontWeight = FontWeight.BOLD
            prefWidth = 600.px
            prefHeight = 800.px
            textField {
                prefWidth = simulateWidth

            }
            field {prefWidth = simulateWidth}
            button {
                prefWidth = simulateWidth

            }
            progressIndicator{
                prefWidth = 13.px
                prefHeight = prefWidth
            }
        }
    }
}