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

    private val simulationType = observableArrayList("Simple growth", "Moore", "Energy counting")
    private val selectedSimulationType = SimpleStringProperty()

    private val dualPhaseCheckbox = SimpleBooleanProperty()
    private val drawBoundariesCheckbox = SimpleBooleanProperty()
    private val workOnEnergy = SimpleBooleanProperty()
    private val energyDistribution = SimpleBooleanProperty()
    private val distributeEnergyOnBoundary = SimpleBooleanProperty()
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
                        engineController.setArray(Array(model.xSize.value.toInt(), { Array(model.ySize.value.toInt(), { Cell(0, 0, "empty", "empty", 0, false, false, 0) }) }))
                        engineController.setModelxSize(model.xSize.value.toInt())
                        engineController.setModelySize(model.ySize.value.toInt())
                        Utils.setCoordinatesInArray()


                    }
                }
                    field("Simulation Type"){combobox(selectedSimulationType, values = simulationType)}
                    field("Probability of change") {textfield(model.probabilityOfChange)                }
                     button("Simulate") {
                          action {
                              engineController.setEnergyLevel(model.energyLevel.value.toInt())
                              if(selectedSimulationType.value=="Simple growth"){
                                  Utils.grainGrow()
                              }
                              if(selectedSimulationType.value=="Moore"){
                                  engineController.setModelProbability(model.probabilityOfChange.value.toInt())
                                  Utils.mooreGrowth()
                              }
                              if(selectedSimulationType.value=="Energy counting"){
                                  engineController.setSimulationsteps(model.simulationSteps.value.toInt())
                                  Utils.energyGrowth()
                              }
                              println("Simulation done")
                           }
                     }
                    field("Grains to delete") {textfield(model.grainsToKeep)                }
                checkbox("Dual phase mode", dualPhaseCheckbox)
                checkbox("Draw boundaries", drawBoundariesCheckbox)
                checkbox("Energy counting mode", workOnEnergy)
                checkbox("Heterogenic energy distribution", energyDistribution)
                checkbox("Distribute energy on boundary", distributeEnergyOnBoundary)

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
                    field("Energy levels"){
                        textfield(model.energyLevel)
                    }
                    field("Simulation steps"){
                        textfield(model.simulationSteps)
                    }

                    button("Set Nucleons") {
                        action {
                            engineController.setNucleonsNumber(model.nucleonsNumber.value.toInt())
                            Utils.setGrainsInArray()
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
                    button("Set energy") {
                        action {
                            engineController.setNucleonsNumber(model.nucleonsNumber.value.toInt())
                            engineController.setEnergyLevel(model.energyLevel.value.toInt())
                            Utils.setEnergyInArray()
                            println("Energy set")
                            println(engineController.getEnergyLevel())
                            Drawing.setEnergyColors()
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
            button("Set some 0-energy nucleons") {
                action {
                    if(distributeEnergyOnBoundary.value){
                        Utils.placeSomeNucleonsWithEnergyOnBoundary()
                    }
                    if(!distributeEnergyOnBoundary.value) {
                        Utils.placeSomeNucleonsWithEnergy()
                    }

                }
            }
             button("Render image") {
                 action {
                     if(!workOnEnergy.value){
                         //Utils.cellsAtBoundary()
                         engineController.setBoundaryDrawing(drawBoundariesCheckbox.value)
                         println(engineController.getBoundaryDrawing())
                         if(energyDistribution.value){
                             Utils.distributeEnergyHeterogeniously()
                         }
                         if(!energyDistribution.value) {
                             Utils.distributeEnergyHomogeniously()
                         }
                         engineController.setModelImage(Drawing.drawArray())}
                     else{
                         Drawing.drawEnergy()

                     }
                 }
             }
            button("Distribute energy") {
                action {
                    if(!workOnEnergy.value){
                        if(energyDistribution.value){
                            Utils.distributeEnergyHeterogeniously()
                        }
                        if(!energyDistribution.value) {
                            Utils.distributeEnergyHomogeniously()
                        }
                        engineController.setModelImage(Drawing.drawArray())}
                    else{
                        engineController.setModelImage(Drawing.drawEnergy())

                    }
                }
            }
                 button("Show result") {
                     action {
                         if(!workOnEnergy.value){
                             Utils.cellsAtBoundary()
                             engineController.setBoundaryDrawing(drawBoundariesCheckbox.value)
                             println(engineController.getBoundaryDrawing())
                             engineController.setModelImage(Drawing.drawArray())}
                         else{
                             println(engineController.getSimulationSteps())
                             Drawing.drawEnergy()

                         }


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
