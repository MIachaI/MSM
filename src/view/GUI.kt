package view
import app.Styles
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import controller.engineController
import javafx.stage.FileChooser
import tornadofx.*
import app.Cell
import app.Moore
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections.observableArrayList
import javafx.geometry.Insets
import java.io.File


class MyApp: App(MyView::class)
class MyView: View() {
    override val root = Form().addClass(simulate)
    val model = UserModel(User())
    val inclusionType = observableArrayList("Circular before", "Circular after", "Square before", "Square after")
    val selectedInclusionType = SimpleStringProperty()

    val simulationType = observableArrayList("Simple growth", "Moore")
    val selectedSimulationType = SimpleStringProperty()

    init {
        engineController.setModelColorArray(Drawing.setColors(1000))
        reloadViewsOnFocus()
        importStylesheet(Styles::class)
        with(root) {
            hboxConstraints {
                margin = Insets(5.0)}
            hbox { spacing = 20.0 }
        }
        menubar {
            menu("File") {
                item("Export").action {
                    var chosenDirectory = chooseDirectory("Choose directory")
                    engineController.setFileToReadPath(chosenDirectory.toString())
                    Utils.saveToFile()
                    println("Exported") }
                item("Import").action {
                    var extensions: Array<FileChooser.ExtensionFilter> = Array(1, {FileChooser.ExtensionFilter("txt", "*.txt")})
                    var arrayOfSelectedFiles =chooseFile("Choose a file", extensions, FileChooserMode.Single)
                    engineController.setFileToReadPath(arrayOfSelectedFiles[0].toString())
                    Utils.writeFromFile()
                    println("Imported")
                    engineController.setModelImage(Drawing.drawArray()) }
            }

        }
        hbox {

            fieldset("primary info") {
                field("x Size") {
                    textfield(model.xSize).required()

                }

                field("y Size") {
                    textfield(model.ySize).required()

                }
                button("Create an array") {
                    action {
                        engineController.setArray(Array(model.xSize.value.toInt(), { Array(model.ySize.value.toInt(), { Cell(0, 0, "empty", "empty", 0, false) }) }))
                        engineController.setModelxSize(model.xSize.value.toInt())
                        engineController.setModelySize(model.ySize.value.toInt())

                    }
                }
                    field("Simulation Type"){combobox(selectedSimulationType, values = simulationType)}
                    field("Probability of change") {textfield(model.probabilityOfChange)                }
                     button("Simulate") {
                          action {
                              if(selectedSimulationType.value=="Simple growth"){
                                  Utils.grainGrow()
                              }
                              if(selectedSimulationType.value=="Moore"){
                                  Utils.mooreGrowth()
                              }
                              println("Simulation done")
                           }
                     }
            }
            hbox {
                fieldset("preferences") {
                    field("Nucleons") {
                        textfield(model.nucleonsNumber).required()
                    }

                    field("Inclusions Number") {
                        textfield(model.inclusionsNumber).required()
                    }


                    field("Inclusion type"){combobox(selectedInclusionType, values = inclusionType)}
                    field("Inclusions Size") {
                        textfield(model.inclusionsSize).required()
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
                            if (selectedInclusionType.value.equals("Circular before")) Utils.setCircleInclusionsBefore()
                            if (selectedInclusionType.value.equals("Square before")) Utils.setDiagonalInclusionsBefore()
                            if (selectedInclusionType.value.equals("Circular after")) {
                                Utils.cellsAtBoundary()
                                Utils.setCircleInclusionsAfter()

                            }
                            if (selectedInclusionType.value.equals("Square after")) {
                                Utils.cellsAtBoundary()
                                Utils.setDiagonalInclusionsAfter()
                            }
                        }
                    }
                }

            }
        }
        form {
             button("render image") {
        action {
            engineController.setModelImage(Drawing.drawArray())
        }
    }
                 button("show result") {
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
