package app

import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet(){
    companion object {
        val simulate by cssclass()
        val simulateWidth = 300.px

    }

    init{
        form and simulate {
            padding = box(25.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            prefWidth = simulateWidth
            button {prefWidth = simulateWidth}
            progressIndicator{
                prefWidth = 16.px
                prefHeight = prefWidth
            }
        }
    }
}