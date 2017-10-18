package view

import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import tornadofx.*
import tornadofx.Stylesheet.Companion.title

class MainScreen : View() {
    override val root = Form()

    init {
        title = "Grain growth simulator"

        with (root){
            fieldset{
                labelPosition = Orientation.VERTICAL

                field("Username"){
                    textfield{

                    }
                }
                field("Password"){
                    textfield{

                    }
                }
            }
            button("Simulate"){
                setOnAction{

                }
            }
        }
    }
    private fun Button.login(){
        graphic = ProgressIndicator()
    }
}