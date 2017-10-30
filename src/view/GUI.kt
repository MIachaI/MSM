package view
import app.Styles
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import controller.engineController
import javafx.stage.FileChooser
import tornadofx.*
import app.Cell



class MyApp: App(MyView::class)
class MyView: View() {
    override val root = Form().addClass(simulate)
    val model = UserModel(User())

    init {
        engineController.setModelColorArray(Drawing.setColors(1000))
        reloadViewsOnFocus()
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

                field("Inclusions Number") {
                    textfield(model.inclusionsNumber).required()
                }
                field("Inclusions Size") {
                    textfield(model.inclusionsSize).required()
                }
            }

            button("Set Nucleons") {
                action {

                    engineController.setArray(Array(model.xSize.value.toInt(), { Array(model.ySize.value.toInt(), { Cell(0, 0, "empty", "empty", 0, false) }) }))
                    engineController.setModelxSize(model.xSize.value.toInt())
                    engineController.setModelySize(model.ySize.value.toInt())
                    Utils.setGrainsInArray(model.nucleonsNumber.value.toInt(), engineController.getArray())
                    println("Nucelons set")
                }
            }

            button("Set Inclusions") {
                action {

                        engineController.setArray(Array(model.xSize.value.toInt(), { Array(model.ySize.value.toInt(), { Cell(0, 0, "empty", "empty", 0, false) }) }))
                        engineController.setModelxSize(model.xSize.value.toInt())
                        engineController.setModelySize(model.ySize.value.toInt())
                        engineController.setInclusionsNumber(model.inclusionsNumber.value.toInt())
                        engineController.setInclusionsSize(model.inclusionsSize.value.toInt())
                        Utils.setCircleInclusionsInArray()
                        Utils.setGrainsInArray(model.nucleonsNumber.value.toInt(), engineController.getArray())
                        Utils.grainGrow(engineController.getModelxSize(), engineController.getModelySize(), engineController.getArray())

                        runAsync {
                    } ui { loadedText ->

                    }
                }
            }

            button("Export") {
                action {
                    runAsync {
                        println(engineController.getArray())
                        Utils.saveToFile()
                        println("Exported")
                    } ui { loadedText ->

                    }
                }
            }
            button("Import") {
                action {
                    Utils.writeFromFile()
                    println("Imported")
                    engineController.setModelImage(Drawing.drawArray())
                }
                    runAsync {
                    } ui { loadedText ->


                }
            }
            button("Simulate") {
                action {
                    engineController.runSimulation(model.xSize.value.toInt(),model.ySize.value.toInt(), model.nucleonsNumber.value.toInt()).toProperty()
                    println("done")
                    runAsync {

                    } ui { loadedText ->

                    }
                }
            }
            form {
                button("show result"){
                    action {
                        engineController.setModelImage(Drawing.drawArray())
                    }
                }

                button("show result"){
                    action {
                        vbox {
                            imageview(engineController.getModelImage()) {
                                useMaxWidth = true

                                scaleX = 1.0
                                scaleY = 1.0

                            }
                        }
                    }
                }
            }

        }




        }







    }

class NextApp: App(NextView::class)
class NextView: View() {
    override val root = Form().addClass(simulate)
    val model = UserModel(User())
    init {
        importStylesheet(Styles::class)
        with(root) {
            prefWidth = 200.0
            prefHeight = 200.0
            imageview(model.image.value) {

                scaleX = 1.0
                scaleY = 1.0
            }
        }
    }
}