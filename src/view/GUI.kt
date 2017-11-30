package view
import app.Styles
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import controller.engineController
import javafx.stage.FileChooser
import tornadofx.*
import app.Cell
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections.observableArrayList
import javafx.geometry.Insets



class MyApp: App(MyView::class)
class MyView: View() {
    override val root = Form().addClass(simulate)
    private val model = UserModel(User())
    private val inclusionType = observableArrayList("Circular before", "Circular after", "Square before", "Square after")
    private val selectedInclusionType = SimpleStringProperty()

    private val simulationType = observableArrayList("Simple growth", "Moore")
    private val selectedSimulationType = SimpleStringProperty()

    private val dualPhaseCheckbox = SimpleBooleanProperty()
    private val drawBoundariesCheckbox = SimpleBooleanProperty()

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
                    val chosenDirectory = chooseDirectory("Choose directory")
                    engineController.setFileToReadPath(chosenDirectory.toString())
                    Utils.saveToFile()
                    println("Exported") }
                item("Import").action {
                    val extensions: Array<FileChooser.ExtensionFilter> = Array(1, {FileChooser.ExtensionFilter("txt", "*.txt")})
                    val arrayOfSelectedFiles =chooseFile("Choose a file", extensions, FileChooserMode.Single)
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
                        engineController.setArray(Array(model.xSize.value.toInt(), { Array(model.ySize.value.toInt(), { Cell(0, 0, "empty", "empty", 0, false, false) }) }))
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
                                  engineController.setModelProbability(model.probabilityOfChange.value.toInt())
                                  Utils.mooreGrowth()
                              }
                              println("Simulation done")
                           }
                     }
                    field("Grains to delete") {textfield(model.grainsToKeep)                }
                checkbox("Dual phase mode", dualPhaseCheckbox)
                checkbox("Draw boundaries", drawBoundariesCheckbox)

                button("Reset unselected grains") {
                    action {
                        engineController.setNumberOfSelectedGrains(model.grainsToKeep.value.toInt())
                        Utils.resetArrayWithSelectedGrains()

                        if(dualPhaseCheckbox.value == true){
                            Utils.dualPhase()
                        }
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
                            if (selectedInclusionType.value=="Circular before") Utils.setCircleInclusionsBefore()
                            if (selectedInclusionType.value=="Square before") Utils.setDiagonalInclusionsBefore()
                            if (selectedInclusionType.value=="Circular after") {
                                Utils.cellsAtBoundary()
                                Utils.setCircleInclusionsAfter()
                            }
                            if (selectedInclusionType.value=="Square after") {
                                Utils.cellsAtBoundary()
                                Utils.setDiagonalInclusionsAfter()
                            }
                        }
                    }
                }

            }
        }

        form {
            button("Clear space") {
                action {
                    engineController.setNumberOfSelectedGrains(model.grainsToKeep.value.toInt())
                    Utils.cellsAtBoundary()
                    Utils.clearSpace()

                }
            }
             button("Render image") {
        action {
            Utils.cellsAtBoundary()
            engineController.setBoundaryDrawing(drawBoundariesCheckbox.value)
            println(engineController.getBoundaryDrawing())
            engineController.setModelImage(Drawing.drawArray())
        }
    }
                 button("Show result") {
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
