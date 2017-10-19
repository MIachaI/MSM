package view
import app.Styles
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import controller.engineController
import tornadofx.*
class MyApp: App(MyView::class)
class MyView: View() {
    override val root = Form().addClass(simulate)
    val model = UserModel(User())


    init {
        importStylesheet(Styles::class)
        with (root) {
            prefWidth = 400.0
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
            button("Simulate") {
                action {
                    runAsync {
            println(model.xSize.value.toInt())
            var array = engineController.runSimulation(model.xSize.value.toInt(),model.ySize.value.toInt(), model.nucleonsNumber.value.toInt())
            println("done")
                    } ui { loadedText ->

                    }
                }
            }
            button("Export") {
                action {
                    runAsync {
                        //Utils.saveToFile(model.xSize.value.toInt(),model.ySize.value.toInt())

                        println("Exported")
                    } ui { loadedText ->

                    }
                }
            }
            button("Import") {
                action {
                    runAsync {
                        println("Imported")
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