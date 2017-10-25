package controller

import app.User
import app.UserModel
import javafx.scene.image.WritableImage
import tornadofx.Controller
import tornadofx.toProperty

/**
 * Created by MIachaI on 19.10.2017.
 */

class engineController : Controller(){


    companion object {
        val model = UserModel(User())
        fun getModelSize(): Int{
            return model.xSize.value.toInt()
        }
        fun setModelSize(sizesize: Int){
            model.xSize = sizesize.toProperty()
        }

        fun getArray(): Array<Array<Int>>{
            return model.array.value
        }
        fun setArray(arrayModel: Array<Array<Int>>){
            model.array = arrayModel.toProperty()
        }

        fun getModelColorArray(): Array<Int>{
           return model.arrayOfColors.value
        }
        fun setModelColorArray(arrayToSet: Array<Int>){
            model.arrayOfColors = arrayToSet.toProperty()
        }
        fun setModelImage(imageToSet: WritableImage){
            model.image = imageToSet.toProperty()
        }
        fun getModelImage(): WritableImage{
            return model.image.value
        }


        fun runSimulation(xSize: Int, ySize: Int, nucleonsNumber: Int): WritableImage {

            var previousStepArray = Array(xSize, { Array(ySize, { 0 }) })
            var nextStepArray = Array(xSize, { Array(ySize, { 0 }) })

            Utils.setGrainsInArray(nucleonsNumber, xSize, previousStepArray)
            setModelColorArray(Drawing.setColors(nucleonsNumber))
            Utils.grainGrow(xSize, ySize, previousStepArray, nextStepArray, getModelColorArray())
            var dupa = Drawing.drawArray(xSize, ySize, previousStepArray, getModelColorArray(), "0")
            setArray(previousStepArray)
            setModelImage(dupa)
            return dupa

        }
    }
}