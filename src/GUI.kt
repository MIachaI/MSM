
import javafx.scene.layout.VBox

import tornadofx.*
class MyApp: App(MyView::class)
class MyView: View() {
    override val root = VBox()
 var xSize = "0"
    var ySize = "0"
    var nucleons = "0"
    var injections = "0"
    init {
        with (root) {
            prefWidth = 800.0
            prefHeight = 600.0
        }

        form {
            fieldset("primary info") {
                field("x Size") {
                   textfield(xSize)

                }

                field("y Size") {
                    textfield(ySize)

                }
            }

            fieldset("preferences") {
                field("Nucleons") {
                    textfield(nucleons)

                }

                field("Inclusions") {
                   textfield(injections)

                }
            }

            button("Commit") {
                action { println("Wrote to database!")}
            }
        }


        button("go") {
            action {
                runAsync {
                    var previousStepArray = Array(xSize.toInt(), {Array(ySize.toInt(),{0})})
                    var nextStepArray = Array(xSize.toInt(), {Array(ySize.toInt(),{0})})
                    Utils.setGrainsInArray(nucleons.toInt(),xSize.toInt(),previousStepArray)
                    var colorSet = Drawing.setColors(nucleons.toInt())
                    Utils.grainGrow(xSize.toInt(),ySize.toInt(),previousStepArray,nextStepArray, colorSet)
                    Drawing.drawArray(xSize.toInt(),ySize.toInt(),previousStepArray, colorSet, "0")
                } ui { loadedText ->

                }
            }
        }
        imageview() {
            scaleX = 1.0
            scaleY = 1.0
        }



    }
}