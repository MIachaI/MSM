package view
import app.Styles
import app.User
import app.UserModel
import app.Styles.Companion.simulate
import controller.engineController
import tornadofx.*



class MyApp: App(MyView::class)
class MyView: View() {
    object CustomerListRequest : FXEvent(EventBus.RunOn.BackgroundThread)
    override val root = Form().addClass(simulate)
    val model = UserModel(User())

    init {
        model.image = engineController.runSimulation(5,5,1).toProperty()
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

            button("Simulate") {
                action {
                    runAsync {
            println(model.xSize.value.toInt())
            model.image = engineController.runSimulation(model.xSize.value.toInt(),model.ySize.value.toInt(), model.nucleonsNumber.value.toInt()).toProperty()
            println("done")
                        fire(CustomerListRequest)
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
                    //openInternalWindow(NextView::class)

                    println("Imported")
                }
                    runAsync {
                    } ui { loadedText ->


                }
            }
            form {
                runAsync{
                    println(model.image.value)
                    imageview(model.image.value) {
                        scaleX = 1.0
                        scaleY = 1.0
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