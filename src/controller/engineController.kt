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
        fun getModelxSize(): Int{
            return model.xSize.value.toInt()
        }
        fun setModelxSize(sizesize: Int){
            model.xSize = sizesize.toProperty()
        }

        fun getModelySize(): Int{
            return model.ySize.value.toInt()
        }
        fun setModelySize(sizesize: Int){
            model.ySize = sizesize.toProperty()
        }

        fun getArray(): Array<Array<Int>>{
            return model.array.value
        }
        fun setArray(arrayModel: Array<Array<Int>>){
            model.array = arrayModel.toProperty()
        }
        fun getNucleonsNumber(): Int{
            return model.nucleonsNumber.value.toInt()
        }
        fun setNucleonsNumber(numberToSet: Int){
            model.nucleonsNumber = numberToSet.toProperty()
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
            setModelxSize(xSize)
            setModelySize(ySize)
            setNucleonsNumber(nucleonsNumber)
            var previousStepArray = Array(xSize, { Array(ySize, { 0 }) })
            var nextStepArray = Array(xSize, { Array(ySize, { 0 }) })

            Utils.setGrainsInArray(nucleonsNumber, xSize, previousStepArray)
            setModelColorArray(Drawing.setColors(nucleonsNumber))
            Utils.grainGrow(xSize, ySize, previousStepArray, nextStepArray, getModelColorArray())
            var dupa = Drawing.drawArray(getModelxSize(), getModelySize(), previousStepArray, getModelColorArray(), "0")
            setArray(previousStepArray)
            setModelImage(dupa)
            return dupa

        }
    }
}