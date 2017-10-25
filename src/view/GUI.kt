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

                field("Inclusions") {
                    textfield(model.inclusionsSize).required()
                }
            }


            button("Export") {
                action {
                    runAsync {
                        println(engineController.getArray())
                        Utils.saveToFile(engineController.getModelxSize(),engineController.getModelySize(),engineController.getArray())
                        println("Exported")
                    } ui { loadedText ->

                    }
                }
            }
            button("Import") {
                action {
                    Utils.writeFromFile()
                    println("Imported")
                    engineController.setModelImage(Drawing.drawArray(engineController.getModelxSize(),engineController.getModelySize(), engineController.getArray(), engineController.getModelColorArray(),"0"))
                }
                    runAsync {
                    } ui { loadedText ->


                }
            }
            button("Simulate") {
                action {
                    runAsync {
                        engineController.runSimulation(model.xSize.value.toInt(),model.ySize.value.toInt(), model.nucleonsNumber.value.toInt()).toProperty()
                        println("done")
                    } ui { loadedText ->

                    }
                }
            }
            form {
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