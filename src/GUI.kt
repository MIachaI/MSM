package GUI
import javafx.scene.layout.VBox
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import tornadofx.*
class MyApp: App(MyView::class)
class MyView: View() {
    override val root = Form().addClass(simulate)
    val model = UserModel(User())

    init {
        with (root) {
            prefWidth = 800.0
            prefHeight = 600.0
        }

        form {
            fieldset("primary info") {
                field("x Size") {
                   textfield(model.xSize).required()

                }

                field("y Size") {
                    textfield(model.ySize).required()

                }
            }

            fieldset("preferences") {
                field("Nucleons") {
                    textfield(model.nucleonsNumber).required()

                }

                field("Inclusions") {
                    textfield(model.inclusionsSize).required()

                }
            }
            button("go") {
                action {
                    runAsync {
                        var previousStepArray = Array(model.xSize.value.toInt(), {Array(model.ySize.value.toInt(),{0})})
                        var nextStepArray = Array(model.xSize.value.toInt(), {Array(model.ySize.value.toInt(),{0})})
                        Utils.setGrainsInArray(model.nucleonsNumber.value.toInt(),model.xSize.value.toInt(),previousStepArray)
                        var colorSet = Drawing.setColors(model.nucleonsNumber.value.toInt())
                        Utils.grainGrow(model.xSize.value.toInt(),model.ySize.value.toInt(),previousStepArray,nextStepArray, colorSet)
                        Drawing.drawArray(model.xSize.value.toInt(),model.ySize.value.toInt(),previousStepArray, colorSet, "0")
                        println("zrobione")
                    } ui { loadedText ->

                    }
                }
            }

        }



        imageview() {
            scaleX = 1.0
            scaleY = 1.0
        }



    }
}