package view
import app.Styles
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import controller.engineController
import javafx.stage.FileChooser
import tornadofx.*
import app.Cell
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.FXCollections.observableArrayList


class MyApp: App(MyView::class)
class MyView: View() {
    override val root = Form().addClass(simulate)
    val model = UserModel(User())
    val inclusionType = observableArrayList("Circular before","Circular after","Square before","Square after")
    val selectedInclusionType = SimpleStringProperty()

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
                combobox(selectedInclusionType, values=inclusionType)
                field("Inclusions Size") {
                    textfield(model.inclusionsSize).required()
                }
            }
            button("Create an array") {
                action {
                    engineController.setArray(Array(model.xSize.value.toInt(), { Array(model.ySize.value.toInt(), { Cell(0, 0, "empty", "empty", 0, false) }) }))
                    engineController.setModelxSize(model.xSize.value.toInt())
                    engineController.setModelySize(model.ySize.value.toInt())
                    println(255 shl 24 or (255 shl 16) or (255 shl 8) or 255)
                }
            }
            button("Set Nucleons") {
                action {
                    Utils.setGrainsInArray(model.nucleonsNumber.value.toInt(), engineController.getArray())
                    println("Nucelons set")
                }
            }

            button("Set Inclusions") {
                action {
                        engineController.setInclusionsNumber(model.inclusionsNumber.value.toInt())
                        engineController.setInclusionsSize(model.inclusionsSize.value.toInt())
                        if (selectedInclusionType.value.equals("Circular before"))Utils.setCircleInclusionsBefore()
                        if (selectedInclusionType.value.equals("Square before"))Utils.setDiagonalInclusionsBefore()
                        if (selectedInclusionType.value.equals("Circular after")){
                            Utils.cellsAtBoundary()
                            Utils.setCircleInclusionsAfter()

                        }
                        if (selectedInclusionType.value.equals("Square after")) {
                            Utils.cellsAtBoundary()
                            Utils.setDiagonalInclusionsAfter()
                        }
                        }

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
                    Utils.grainGrow()
                    println("Simulation done")
                    runAsync {

                    } ui { loadedText ->

                    }
                }
            }
            form {
                button("render image"){
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